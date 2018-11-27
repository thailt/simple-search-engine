package com.thai.util;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StringUtilTest {

    private static final String queryTextFile = "100_query.txt";
    private static final String productNameTextFile = "product_names.txt";


    @Test
    public void testNormalize() {
        String name = "Lê Trí Thái";
        String expectedName = "Le Tri Thai";
        String nameRemoved = StringUtil.removeAccent(name);
        Assert.assertEquals(nameRemoved, expectedName);
    }

    @Test
    public void testGetAbsoluteResourceFilePath() {
        String queryPath = StringUtil.getAbsoluteResourceFilePath(queryTextFile);
        Path currentRelativePath = Paths.get("");

        System.out.println("path is: " + queryPath);
        Assert.assertTrue(queryPath.startsWith(currentRelativePath.toString()));
        Assert.assertTrue(queryPath.endsWith(queryTextFile));
    }

    @Test
    public void testGetResourceAsStream() {
        String input = StringUtil.getAbsoluteResourceFilePath(queryTextFile);
        System.out.println(input);
    }

    @Test
    public void testReadLinesFromPath() {
        Path path = Paths.get(StringUtil.getAbsoluteResourceFilePath(queryTextFile));
        List<String> lines = StringUtil.readLines(path);
        Assert.assertEquals(lines.size(), 100);
    }

    @Test
    public void testReadLinesFromStringPath() {
        String absoluteFilePath = StringUtil.getAbsoluteResourceFilePath(productNameTextFile);
        List<String> lines = StringUtil.readLines(absoluteFilePath);
        Assert.assertEquals(lines.size(), 50000);
    }
}
