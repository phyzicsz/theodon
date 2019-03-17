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
import javax.xml.parsers.ParserConfigurationException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author phyzicsz
 */
@Mojo(name = "generate")
public class DisCodeGenMojo extends AbstractMojo {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CodeGenerator.class);
    
    @Parameter
    private File inputPath;

    @Parameter
    private File outputPath;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            new CodeGenerator(
                    inputPath,
                    outputPath)
                    .generate();
        } catch (CodeGenerationConfigurationException ex) {
            LOGGER.error("Configuration Error:", ex);
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            LOGGER.error("Runtime Error:", ex);
        }

    }

}
