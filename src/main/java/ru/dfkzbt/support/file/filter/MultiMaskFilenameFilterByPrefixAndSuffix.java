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

import java.io.File;
import java.io.FilenameFilter;

/**
 * implement custom FilenameFilter that check both prefix and suffix with a set of masks
 * <p>
 * it is possible to use more than one mask
 * if masks provided as one String - masks are splitted by SEPARATOR, in example: .png;.jpg
 * if masks provided as String[] array - they are separate entries
 * <p>
 * masks and filenames compared ignoring case
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.2-SNAPSHOT
 * Created on 17.05.2018.
 */
public class MultiMaskFilenameFilterByPrefixAndSuffix implements FilenameFilter {
    public final static String SEPARATOR = ";";

    private String[] suffixes;
    private String[] prefixes;

    public MultiMaskFilenameFilterByPrefixAndSuffix(String prefixes, String suffixes) {
        this.prefixes = prefixes.toLowerCase().split(SEPARATOR);
        this.suffixes = suffixes.toLowerCase().split(SEPARATOR);
    }

    public MultiMaskFilenameFilterByPrefixAndSuffix(String[] prefixes, String[] suffixes) {
        this.prefixes = prefixes;
        this.suffixes = suffixes;

        for (int i = 0; i < this.prefixes.length; i++) {
            this.prefixes[i] = this.prefixes[i].toLowerCase();
        }

        for (int i = 0; i < this.suffixes.length; i++) {
            this.suffixes[i] = this.suffixes[i].toLowerCase();
        }
    }

    @Override
    public boolean accept(File dir, String name) {
        for (String suffix : suffixes) {
            if (!name.toLowerCase().endsWith(suffix)) continue;

            for (String prefix : prefixes) {
                if (name.toLowerCase().startsWith(prefix)) return true;
            }
        }

        return false;
    }
}
