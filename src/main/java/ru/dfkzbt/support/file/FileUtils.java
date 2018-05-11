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

package ru.dfkzbt.support.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Generic description
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.2-SNAPSHOT
 * Created on 05.12.2017.
 */
public class FileUtils {
    private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static boolean pathIsFolderAndExist(String path) {
        return Files.isDirectory(Paths.get(path));
    }

    public static boolean pathIsRegularFileAndExist(String path) {
        return Files.isRegularFile(Paths.get(path));
    }

    public static boolean pathCreateIfNotExist(String path) {
        String methodWeAreIn = new Throwable().getStackTrace()[0].getMethodName();

        // check is folder exist, and create it if not
        if (pathIsFolderAndExist(path)) {
            logger.debug("{} folder path exist: {}", methodWeAreIn, path);
        } else {
            logger.debug("{} folder path not exist, create new one: {}", methodWeAreIn, path);

            boolean folderCreationStatus = false;
            File reportsFile = new File(path);

            folderCreationStatus = reportsFile.mkdirs();

            if (folderCreationStatus) {
                logger.debug("{} Folder created sucessfully. {}", methodWeAreIn, path);
            } else {
                logger.error("{} Failed to create folder. {}", methodWeAreIn, path);
                return false;
            }
        }

        return true;
    }

    public static boolean isResourceExistStatic(String resourcePath, String resourceName) throws IllegalArgumentException {
        String methodWeAreIn = new Throwable().getStackTrace()[0].getMethodName();

        if (resourcePath == null || resourceName == null) {
            logger.error("{} Arguments cant be NULL: {} {}", methodWeAreIn, resourcePath, resourceName);
            throw new IllegalArgumentException("Arguments cant be null");
        }

        if (resourcePath.isEmpty() || resourceName.isEmpty()) {
            logger.error("{} Arguments cant be empty: {} {}", methodWeAreIn, resourcePath, resourceName);
            throw new IllegalArgumentException("Arguments cant be empty");
        }

        //URL urlXSDSchema = getClass().getResource(xsdFilename); // do not work in static
        URL urlResource = null;
        try {
            urlResource = Class.forName(new Throwable().getStackTrace()[0].getClassName()).getResource(resourcePath + resourceName);
        } catch (ClassNotFoundException e) {
            logger.error("Got exeption while locating resource: {}", e.getMessage());
            logger.trace("Stacktrace: {}", e);
            return false;
        }

        if (urlResource == null) {
            logger.error("{} URL Failed to locate Resource: {}", methodWeAreIn, (resourcePath + resourceName));
            return false;
        }

        logger.debug("{} URL Path to Resource: {}", methodWeAreIn, urlResource.getPath());

        return true;
    }

    public static InputStream getResourceInputStreamStatic(String resourcePath, String resourceName) throws IllegalArgumentException, ClassNotFoundException {
        String methodWeAreIn = new Throwable().getStackTrace()[0].getMethodName();

        if (resourcePath == null || resourceName == null) {
            logger.error("{} Arguments cant be NULL: {} {}", methodWeAreIn, resourcePath, resourceName);
            throw new IllegalArgumentException("Arguments cant be null");
        }

        if (resourcePath.isEmpty() || resourceName.isEmpty()) {
            logger.error("{} Arguments cant be empty: {} {}", methodWeAreIn, resourcePath, resourceName);
            throw new IllegalArgumentException("Arguments cant be empty");
        }

        if (!isResourceExistStatic(resourcePath, resourceName)) {
            logger.error("{} Cant locate resource: {} {}", methodWeAreIn, resourcePath, resourceName);
            throw new IllegalArgumentException("Cant locate resource");
        }

        //URL urlXSDSchema = getClass().getResource(xsdFilename); // do not work in static
        InputStream inputStream = null;
        try {
            inputStream = Class.forName(new Throwable().getStackTrace()[0].getClassName()).getResourceAsStream((resourcePath + resourceName));
        } catch (ClassNotFoundException e) {
            logger.error("Got exeption while locating resource: {}", e.getMessage());
            logger.trace("Stacktrace: {}", e);
            throw e;
        }

        if (inputStream == null) {
            logger.error("{} URL Failed to locate Resource: {}", methodWeAreIn, (resourcePath + resourceName));
            throw new IllegalArgumentException("Cant create resource InputStream");
        }

        return inputStream;
    }

}
