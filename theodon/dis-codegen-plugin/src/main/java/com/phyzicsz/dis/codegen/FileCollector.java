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

import java.io.IOException;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author phyzicsz
 */
public class FileCollector extends SimpleFileVisitor<Path> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenerator.class);
    private List<Path> files = new ArrayList<>();

    public List<Path> getFiles() {
        return files;
    }

    
    
    @Override
    public FileVisitResult visitFile(Path file,BasicFileAttributes attr) {
        if (attr.isSymbolicLink()) {
            LOGGER.debug("Symbolic link: {} ", file);
        } else if (attr.isRegularFile()) {
            LOGGER.debug("Regular file: {} ", file);
        } else {
            LOGGER.debug("Other: {} ", file);
        }
        files.add(file);
        LOGGER.debug("(" + attr.size() + "bytes)");
        return CONTINUE;
    }

    // Print each directory visited.
    @Override
    public FileVisitResult postVisitDirectory(Path dir,IOException exc) {
        LOGGER.debug("Directory: {}", dir);
        return CONTINUE;
    }

    // If there is some error accessing
    // the file, let the user know.
    // If you don't override this method
    // and an error occurs, an IOException 
    // is thrown.
    @Override
    public FileVisitResult visitFileFailed(Path file,IOException exc) {
        LOGGER.error(exc.getMessage(),exc);
        return CONTINUE;
    }
}

