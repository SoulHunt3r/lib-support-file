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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Generic description
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.2-SNAPSHOT
 * Created on 17.05.2018.
 */
public class MultiMaskFilenameFilterBySuffixTest extends SampleDataConfigurationTest {
    private final static Logger logger = LoggerFactory.getLogger(MultiMaskFilenameFilterBySuffixTest.class);

    @Test
    public void filesNotNullForExistingSuffix() {
        File[] files = workingFolder.listFiles(new MultiMaskFilenameFilterBySuffix(sampleFileName1));
        logger.debug("files: {}", Arrays.toString(files));

        assertNotNull(files);
        assertNotEquals(0, files.length);
    }

    @Test
    public void filesNotNullForNotExistingSuffix() {
        File[] files = workingFolder.listFiles(new MultiMaskFilenameFilterBySuffix("file.should.not.exist"));
        logger.debug("files: {}", Arrays.toString(files));

        assertNotNull(files);
        assertEquals(0, files.length);
    }

    @Test
    public void filesSizeForUniqueSuffix() {
        File[] files = workingFolder.listFiles(new MultiMaskFilenameFilterBySuffix(sampleFileName1));
        logger.debug("files: {}", Arrays.toString(files));

        assertEquals(1, files.length);
    }

    @Test
    public void filesSizeForSuffix() {
        File[] files = workingFolder.listFiles(new MultiMaskFilenameFilterBySuffix(suffix1));
        logger.debug("files: {}", Arrays.toString(files));

        assertEquals(2, files.length);
    }

    @Test
    public void filesSizeForMultuSuffixAsArray() {
        String[] suffixes = new String[]{
                suffix1,
                suffix2
        };

        File[] files = workingFolder.listFiles(new MultiMaskFilenameFilterBySuffix(suffixes));
        logger.debug("files: {}", Arrays.toString(files));

        assertEquals(4, files.length);
    }

    @Test
    public void filesSizeForMultuSuffixAsStringWithSeparator() {
        String suffixes = suffix1 + MultiMaskFilenameFilterBySuffix.SEPARATOR + suffix2;

        File[] files = workingFolder.listFiles(new MultiMaskFilenameFilterBySuffix(suffixes));
        logger.debug("files: {}", Arrays.toString(files));

        assertEquals(4, files.length);
    }
}