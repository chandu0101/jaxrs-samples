package com.chandu0101.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static com.chandu0101.core.util.ClassUtils.getResourceAsStream;

/**
 * Created by chandrasekharkode on 5/24/14.
 */
public class FileUtils {
    /**
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String readFileAsString(String fileName) {
        String result;
        final InputStream resourceAsStream = getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));
        result = reader.lines().collect(Collectors.joining(""));
        return result;
    }
}
