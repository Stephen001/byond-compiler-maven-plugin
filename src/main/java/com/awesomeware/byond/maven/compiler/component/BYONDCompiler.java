package com.awesomeware.byond.maven.compiler.component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.compiler.AbstractCompiler;
import org.codehaus.plexus.compiler.Compiler;
import org.codehaus.plexus.compiler.CompilerConfiguration;
import org.codehaus.plexus.compiler.CompilerException;
import org.codehaus.plexus.compiler.CompilerMessage;
import org.codehaus.plexus.compiler.CompilerMessage.Kind;
import org.codehaus.plexus.compiler.CompilerOutputStyle;
import org.codehaus.plexus.compiler.CompilerResult;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.util.Os;

@Component(role = Compiler.class, hint = "byond")
public class BYONDCompiler extends AbstractCompiler {

	protected BYONDCompiler() {
		super(CompilerOutputStyle.ONE_OUTPUT_FILE_FOR_ALL_INPUT_FILES, null, null, null);
	}
	
	@Override
	public CompilerResult performCompile(CompilerConfiguration configuration) {
		try {
			createCommandLine(configuration);
		} catch (CompilerException e) {
			CompilerMessage msg = new CompilerMessage("Failed to compile the DM command to run.", Kind.ERROR);
			List<CompilerMessage> l = new ArrayList<CompilerMessage>();
			l.add(msg);
			return new CompilerResult(false, l);
		}
		
		return null;
	}

	public String[] createCommandLine(CompilerConfiguration arg0) throws CompilerException {
		File compiler = getDMExecutable(arg0);
		File dme = writeDME(arg0);
		if (!compiler.exists() || dme == null) {
			throw new CompilerException("Could not construct command line.");
		}
		String[] args = new String[2];
		args[0] = compiler.getAbsolutePath();
		args[1] = dme.getAbsolutePath();
		return args;
	}
	
	private File writeDME(CompilerConfiguration config) {
		File dme = null;
		FileWriter writer = null;
		try {
			String name = config.getOutputFileName() + ".dme";
			dme = new File(config.getBuildDirectory(), name);
			if (dme.exists()) {
				dme.delete();
				dme.createNewFile();
			} else {
				dme.createNewFile();
			}
			writer = new FileWriter(dme);
			for (String sourceLoc : config.getSourceLocations()) {
				writer.write("#include \"" + sourceLoc + "\"");
			}
			} catch (IOException e) {
				if (dme != null && dme.exists()) {
					dme.delete();
					dme = null;
				}
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {}
				}
			}
		return dme;
	}
	
	private File getDMExecutable(CompilerConfiguration config) {
		File path = new File(config.getExecutable() + "/bin");
		return Os.isFamily("Windows") ? new File(path, "dm") : new File(path, "DreamMaker");
	}
}
