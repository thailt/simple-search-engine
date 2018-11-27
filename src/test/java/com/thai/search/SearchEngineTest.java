package com.thai.search;

import com.thai.util.Constants;
import com.thai.util.StringUtil;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class SearchEngineTest<T extends SearchEngine> {


    private SearchEngine searchEngine;

    public abstract SearchEngine initSearchEngine();

    @Before
    public void setup() {
        Long startAt = System.nanoTime();
        String absoluteFilePath = StringUtil
            .getAbsoluteResourceFilePath(Constants.productNameTextFile);
        List<String> texts = StringUtil.readLines(absoluteFilePath);
        searchEngine = initSearchEngine();
        searchEngine.index(texts);
        Long endAt = System.nanoTime();
        Long duration = endAt - startAt;

//        Assert.assertTrue(duration / 1000000.0 < 1000);
        System.out
            .println(String.format("duration for %s is %f ms ", "setup", duration / 1000000.0));
    }

    @Test
    public void testSingleNoResultSearch() {
        String searchText = "nửa đầu";
        String[] words = StringUtil
            .split(StringUtil.removeAccent(searchText).toLowerCase(), 20);

        List<String> result = searchEngine.search(searchText);
        Assert.assertTrue(result != null);
        for (String product : result) {
            for (String word : words) {
                Assert.assertTrue(StringUtil.removeAccent(product).toLowerCase()
                    .contains(word));
            }
        }
    }


    @Test
    public void testBenchmarkSearch() {
        String absoluteFilePath = StringUtil.getAbsoluteResourceFilePath(Constants.queryTextFile);
        List<String> texts = StringUtil.readLines(absoluteFilePath);
        Long startAt = System.nanoTime();
        for (String text : texts) {
            List<String> results = searchEngine.search(text);
        }
        Long endAt = System.nanoTime();
        Long duration = endAt - startAt;

        Assert.assertTrue(duration / 1000000.0 < 100);
        System.out.println(String
            .format("duration for %s is %f ms ", "testBenchmarkSearch", duration / 1000000.0));
    }

    @After
    public void teardown() {
        //Suppose searchEngine interface support unindex function
    }
}
