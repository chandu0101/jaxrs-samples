package com.chandu0101.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static com.chandu0101.core.util.FileUtils.streamToString;
import static com.chandu0101.core.util.StringUtils.isNotEmpty;
import static java.util.Arrays.asList;

/**
 * For an orbitary key value pairs
 * Example :  {username->test,password->pass}
 * <p>
 * Created by chandrasekharkode on 5/30/14.
 */
public class KeyValueArrowMarshaller {
    public static final String ARROW = "->";
    public static final String START = "{";
    public static final String END = "}";
    public static final String SEPERATOR = ",";

    public static byte[] marshall(Object object) throws IOException {
        StringBuilder result = new StringBuilder(START);
        if (object != null) {
            String value = asList(object.getClass().getDeclaredFields()).stream()
                    .map(f -> {
                                try {
                                    f.setAccessible(true);
                                    return f.getName().concat(ARROW).concat(f.get(object).toString());
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }
                    ).collect(Collectors.joining(SEPERATOR));
            result.append(value).append(END);
        }
        return result.toString().getBytes(StandardCharsets.UTF_8);
    }


    public static <T> T unmarshall(InputStream inputStream,Class<T> classType) throws Exception {
        T result = null;
        String data = streamToString(inputStream);
        if (isValidData(data)) {
            result = classType.getConstructor().newInstance();
            for (String s : data.substring(1, data.length() - 1).split(SEPERATOR)) {
                String[] keyValue = s.split(ARROW);
                for (Field field : result.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.getName().equalsIgnoreCase(keyValue[0])) {
                        field.set(result, keyValue[1]);
                    }
                }
            }
        }
        return result;
    }

    private static boolean isValidData(String data) {
        boolean result = false;
        if (isNotEmpty(data)) {
            result = data.contains(START) && data.contains(END) && data.contains(ARROW);
        }
        return result;
    }
}
