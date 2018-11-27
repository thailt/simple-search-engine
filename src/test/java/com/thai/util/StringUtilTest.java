package com.thai.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {

    @Test
    public void testNormalize() {
        String name = "Lê Trí Thái";
        String expectedName = "Le Tri Thai";
        String nameRemoved = StringUtil.removeAccent(name);
        Assert.assertEquals(nameRemoved, expectedName);
    }
}
