package com.chandu0101.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static com.chandu0101.core.util.ClassUtils.getResourceAsStream;

/**
 * Created by chandrasekharkode on 5/24/14.
 */
public class FileUtils {

    public static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;

    /**
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String readFileAsString(String fileName) {
        return streamToString(getResourceAsStream(fileName));
    }

    public static String streamToString(InputStream inputStream) {
        String result;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, DEFAULT_ENCODING));
        result = reader.lines().collect(Collectors.joining(""));
        return result;
    }
}
