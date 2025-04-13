package com.dmytrohont.test.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StringUtil {

    private StringUtil() {
        throw new IllegalStateException("Utility class");
    }


    public static List<String> getStringsList(String listString, String delimiter) {
        if (listString == null) {
            return new ArrayList<>();
        } else {
            return Stream.of(listString.split(delimiter))
                    .map(String::trim)
                    .toList();
        }
    }
}
