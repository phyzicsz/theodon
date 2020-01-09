/*
 * Copyright 2019 phyzicsz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phyzicsz.dis.codegen;

import com.phyzicsz.dis.codegen.exceptions.CodeGenerationConfigurationException;
import java.io.File;
import java.io.IOException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author phyzicsz
 *
 * @goal dis-protocol
 * @requiresDependencyResolution runtime
 * @phase generate-sources
 * @threadSafe
 */

@Mojo( name = "dis-generator",
        requiresDependencyResolution = ResolutionScope.RUNTIME,
        defaultPhase = LifecyclePhase.GENERATE_SOURCES,
        threadSafe = true
)
@Execute( goal = "dis-protocol")
public class DisCodeGenMojo extends AbstractMojo {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisCodeGenMojo.class);
    /**
     * @parameter property="outputDirectory"
     * default-value="${project.build.directory}/generated-sources/dis"
     */
    @Parameter(name = "outputDirectory",
            defaultValue = "${project.build.directory}/generated-sources/dis",
            readonly = false,
            required = false)
    private File outputDirectory;

    /**
     * @parameter property="testOutputDirectory"
     * default-value="${project.directory}/src/test/java"
     */
     @Parameter(name = "testOutputDirectory",
            defaultValue = "${project.directory}/src/test/java",
            readonly = false,
            required = false)
    private File testOutputDirectory;

    /**
     * @parameter property="javaPackage" default-value="com.phyzics.dis7"
     */
    @Parameter(name = "javaPackage",
            defaultValue = "com.phyzics.dis7",
            readonly = false,
            required = false) 
    private String javaPackage;
    
    
    @Parameter(name = "generateTestSources",
            defaultValue = "false",
            readonly = false,
            required = false) 
    private Boolean generateTestSources;
    

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            new CodeGenerator(
                    javaPackage,
                    outputDirectory,
                    generateTestSources,
                    testOutputDirectory)
                    .generate();
        } catch (CodeGenerationConfigurationException | IOException | ClassNotFoundException ex) {
            LOGGER.error("Error generating code", ex);
        }

    }

}
