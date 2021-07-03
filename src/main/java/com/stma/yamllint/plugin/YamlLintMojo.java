package com.stma.yamllint.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;

@Mojo( name = "yamllint", defaultPhase = LifecyclePhase.VALIDATE )
public class YamlLintMojo extends AbstractMojo {

    @Parameter( defaultValue = "${basedir}", property = "baseDir", required = true )
    private File projectBaseDir;

    @Parameter( defaultValue = "*.yaml")
    private String pattern;

    public void execute() throws MojoExecutionException {
        try {
            Files.walkFileTree(projectBaseDir.toPath(), new YamlFilesChecker(this, pattern));
        } catch (IOException e) {
            getLog().error(e);
        }
    }
}
