package com.thai.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

public class StringUtil {
    private static final Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        return pattern.matcher(temp).replaceAll("");
    }

    public static List<String> readLines(String path) {
        return readLines(Paths.get(path));
    }

    public static List<String> readLines(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAbsoluteResourceFilePath(String resourcePath) {
        ClassLoader classLoader = StringUtil.class.getClassLoader();
        File file = new File(classLoader.getResource(resourcePath).getFile());
        return file.getAbsolutePath();
    }
}
