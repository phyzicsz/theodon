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
package com.phyzicsz.disclassgen7;

import com.phyzicsz.dis.datamodel.api.DisClasses;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author phyzicsz
 */
public class DisGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisGenerator.class);

    private String disFile;
    private String outputDir;
    private String packageName;

    public DisGenerator init() {
        Config conf = ConfigFactory.load();

        disFile = conf.getString("disxml.disFile");
        outputDir = conf.getString("disxml.outputDir");
        packageName = conf.getString("disxml.package");

        Class<?>[] allowedClasses = new Class[] { DisClasses.class};
        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(DisClasses.class);
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(allowedClasses);

        ClassLoader classLoader = getClass().getClassLoader();
        File xmlfile = new File(classLoader.getResource(disFile).getFile());
        
        LOGGER.info("parsing DIS XML File: {}", disFile);
        DisClasses classes = (DisClasses)xstream.fromXML(xmlfile);
        LOGGER.info("parsed {} classes: ", classes.getClasses().size());

        return this;
    }

    public DisGenerator convert() {

        return this;
    }
}
