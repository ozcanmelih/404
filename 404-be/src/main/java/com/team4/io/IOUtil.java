package com.team4.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class IOUtil {

    private static final Logger logger = LoggerFactory.getLogger(IOUtil.class);

    public static byte[] readFile(String filePath) {
        byte[] bytes = null;
        try {
            bytes = FileUtils.readFileToByteArray(new File(filePath));
        } catch (IOException e) {
            logger.error("Failed to load image: " + e.getMessage());
        }

        return bytes;
    }

    public static byte[] readImage(String url){
        byte[] bytes = null;
        try {
            bytes = IOUtils.toByteArray(new URL(url));
        } catch (IOException e) {
            logger.error("Failed to load image: " + e.getMessage());
        }

        return bytes;
    }
}
