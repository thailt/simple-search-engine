package com.thai.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtil {
    private static final Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        return pattern.matcher(temp).replaceAll("");
    }
}
