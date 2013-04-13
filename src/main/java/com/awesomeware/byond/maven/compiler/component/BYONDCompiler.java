package com.awesomeware.byond.maven.compiler.component;

import java.io.File;

import org.codehaus.plexus.compiler.AbstractCompiler;
import org.codehaus.plexus.compiler.Compiler;
import org.codehaus.plexus.compiler.CompilerConfiguration;
import org.codehaus.plexus.compiler.CompilerException;
import org.codehaus.plexus.compiler.CompilerOutputStyle;
import org.codehaus.plexus.component.annotations.Component;

@Component(role = Compiler.class, hint = "byond")
public class BYONDCompiler extends AbstractCompiler {

	protected BYONDCompiler() {
		super(CompilerOutputStyle.ONE_OUTPUT_FILE_FOR_ALL_INPUT_FILES, null, null, null);
	}

	public String[] createCommandLine(CompilerConfiguration arg0) throws CompilerException {
		File dme = writeDME(arg0);
		return null;
	}
	
	public File writeDME(CompilerConfiguration config) {
		return null;
	}
}
