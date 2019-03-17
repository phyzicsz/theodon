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

import java.io.File;
import org.apache.maven.plugin.testing.MojoRule;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author phyzicsz
 */
public class DisCodeGenMojoTest {

    public DisCodeGenMojoTest() {
    }

    @Rule
    public MojoRule rule = new MojoRule();

    /**
     * Test of execute method, of class DisCodeGenMojo.
     */
    @Test
    public void testExecute() throws Exception {
       
        File pom = new File("src/test/resources/pom.xml");
        assertNotNull( pom );
        assertTrue( pom.exists() );
        
        DisCodeGenMojo myMojo = (DisCodeGenMojo) rule.lookupMojo("generate", pom);
        assertNotNull(myMojo);
        myMojo.execute();
    }

}
