package com.chandu0101.core.util;

/**
 * Created by chandrasekharkode on 5/30/14.
 */
public class StringUtils {

    private StringUtils() {

    }
    public static boolean isNotEmpty(String str) {
        return  str!=null && !str.trim().isEmpty();
    }
}
