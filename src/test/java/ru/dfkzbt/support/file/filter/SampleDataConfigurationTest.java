/*
 *    Copyright 2018 Konstantin Fedorov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package ru.dfkzbt.support.file.filter;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.nio.file.Files;

/**
 * Generic description
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.2-SNAPSHOT
 * Created on 17.05.2018.
 */
public abstract class SampleDataConfigurationTest {
    protected final static File workingFolder = new File(".");

    protected final static String middle = ".unique";
    protected final static String suffix1 = middle + ".file1";
    protected final static String suffix2 = middle + ".file2";

    protected final static String prefix1 = "sample1";
    protected final static String sampleFileName1 = prefix1 + suffix1;
    protected File sampleFile1 = new File(sampleFileName1);

    protected final static String prefix2 = "sample2";
    protected final static String sampleFileName2 = prefix2 + suffix1;
    protected File sampleFile2 = new File(sampleFileName2);

    protected final static String sampleFileName3 = prefix1 + suffix2;
    protected File sampleFile3 = new File(sampleFileName3);

    protected final static String sampleFileName4 = prefix2 + suffix2;
    protected File sampleFile4 = new File(sampleFileName4);

    @Before
    public void setUp() throws Exception {
        org.apache.commons.io.FileUtils.touch(sampleFile1);
        org.apache.commons.io.FileUtils.touch(sampleFile2);
        org.apache.commons.io.FileUtils.touch(sampleFile3);
        FileUtils.touch(sampleFile4);
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(sampleFile1.toPath());
        Files.deleteIfExists(sampleFile2.toPath());
        Files.deleteIfExists(sampleFile3.toPath());
        Files.deleteIfExists(sampleFile4.toPath());
    }
}
