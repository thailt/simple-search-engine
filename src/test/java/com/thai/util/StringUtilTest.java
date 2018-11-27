package com.thai.util;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StringUtilTest {


    @Test
    public void testRemoveAccent() {
        String name = "Lê Trí Thái";
        String expectedName = "Le Tri Thai";
        String nameRemoved = StringUtil.removeAccent(name);
        Assert.assertEquals(nameRemoved, expectedName);
    }

    @Test
    public void testRemoveAccentForEmptyString() {
        String name = "   ";
        String expectedName = "   ";
        String nameRemoved = StringUtil.removeAccent(name);
        Assert.assertEquals(nameRemoved, expectedName);
    }
    @Test
    public void testRemoveAccentForNullString() {
        String name = null;
        String expectedName = null;
        String nameRemoved = StringUtil.removeAccent(name);
        Assert.assertEquals(nameRemoved, expectedName);
    }

    @Test
    public void testGetAbsoluteResourceFilePath() {
        String queryPath = StringUtil.getAbsoluteResourceFilePath(Constants.queryTextFile);
        Path currentRelativePath = Paths.get("");

        System.out.println("path is: " + queryPath);
        Assert.assertTrue(queryPath.startsWith(currentRelativePath.toString()));
        Assert.assertTrue(queryPath.endsWith(Constants.queryTextFile));
    }

    @Test
    public void testGetResourceAsStream() {
        String input = StringUtil.getAbsoluteResourceFilePath(Constants.queryTextFile);
        System.out.println(input);
    }

    @Test
    public void testReadLinesFromPath() {
        Path path = Paths.get(StringUtil.getAbsoluteResourceFilePath(Constants.queryTextFile));
        List<String> lines = StringUtil.readLines(path);
        Assert.assertEquals(lines.size(), 100);
    }

    @Test
    public void testReadLinesFromStringPath() {
        String absoluteFilePath = StringUtil.getAbsoluteResourceFilePath(Constants.queryTextFile);
        List<String> lines = StringUtil.readLines(absoluteFilePath);
        Assert.assertEquals(lines.size(), 100);
    }

    @Test
    public void testSplitNormalString(){
        String text = "le tri   hai";
        String[] words = StringUtil.split(text);
        Assert.assertEquals(3, words.length);
    }
    @Test
    public void testSplitWhiteSpacesString(){
        String text = "       ";
        String[] words = StringUtil.split(text);
        Assert.assertEquals(0, words.length);
    }
}
