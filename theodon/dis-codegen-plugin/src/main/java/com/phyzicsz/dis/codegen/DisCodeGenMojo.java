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
import org.slf4j.LoggerFactory;

/**
 *
 * @author phyzicsz
 * 
 * @goal idl-protocol
 * @requiresDependencyResolution runtime
 * @phase generate-sources
 * @threadSafe
 */
public class DisCodeGenMojo extends AbstractMojo {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CodeGenerator.class);

    /**
     * The source directory of DIS IDL files. This directory is added to the
     * classpath at schema compiling time. All files can therefore be referenced
     * as classpath resources following the directory structure under the source
     * directory.
     *
     * @parameter property="sourceDirectory"
     * default-value="${basedir}/src/main/dis"
     */
    private File sourceDirectory;

    /**
     * @parameter property="outputDirectory"
     * default-value="${project.build.directory}/generated-sources/dis"
     */
    private File outputDirectory;
    
    /**
     * @parameter property="testOutputDirectory"
     * default-value="${project.directory}/src/test/java"
     */
    private File testOutputDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            new CodeGenerator(
                    sourceDirectory,
                    outputDirectory,
                    testOutputDirectory)
                    .generate();
        } catch (CodeGenerationConfigurationException ex) {
            LOGGER.error("Configuration Error:", ex);
        } catch (IOException  ex) {
            LOGGER.error("Runtime Error:", ex);
        }

    }

}
