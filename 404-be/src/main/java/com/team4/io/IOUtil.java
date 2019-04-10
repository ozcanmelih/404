package com.team4.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOUtil {

    private static final Logger logger = LoggerFactory.getLogger(IOUtil.class);

    public static byte[] readFile(String filePath) {
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            logger.error("Failed to load image: " + e.getMessage());
        }

        return bytes;
    }
}
