package com.awesomeware.byond.maven.compiler;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;

/**
 * Compiles BYOND files and resources into a DMB and optionally RSC file.
 * 
 * @author Stephen Badger [stephen.badger@gmail.com]
 */
@Mojo(name = "compile",
	  defaultPhase = LifecyclePhase.COMPILE,
	  requiresDependencyResolution = ResolutionScope.COMPILE,
	  requiresProject = true,
	  threadSafe = true)
public class CompileMojo extends AbstractMojo {
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		// TODO Auto-generated method stub
		
	}
}
