package com.thai;

import com.thai.search.SearchEngine;
import com.thai.util.Constants;
import com.thai.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    @Qualifier("fullTextSearch")
    SearchEngine fullTextSearch;

    @Autowired
    @Qualifier("keywordSearch")
    SearchEngine keywordSearch;

    public static void main(String[] args) {
        new SpringApplication(Main.class).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        String absoluteFilePath = StringUtil
                .getAbsoluteResourceFilePath(Constants.productNameTextFile);
        List<String> texts = StringUtil.readLines(absoluteFilePath);
        indexBench(fullTextSearch, texts);
        indexBench(keywordSearch, texts);

        String queryTextFileAbsoluteFilePath = StringUtil
                .getAbsoluteResourceFilePath(Constants.queryTextFile);
        List<String> queryTexts = StringUtil.readLines(queryTextFileAbsoluteFilePath);

        searchBench(fullTextSearch, queryTexts);
        searchBench(keywordSearch, queryTexts);

    }

    private void indexBench(SearchEngine searchEngine, List<String> texts) {
        Long startAt = System.nanoTime();
        searchEngine.index(texts);

        Long endAt = System.nanoTime();
        Long duration = endAt - startAt;

        System.out.println(String
                .format("duration for class %s, functions %s is %f ms ",
                        searchEngine.getClass().getCanonicalName(), "indexBench", duration / 1000000.0));

    }

    private void searchBench(SearchEngine engine, List<String> texts) {
        Long startAt = System.nanoTime();

        for (String text : texts) {
            List<String> results = engine.search(text);
        }

        Long endAt = System.nanoTime();
        Long duration = endAt - startAt;

        System.out.println(String
                .format("duration for class %s, functions %s is %f ms ",
                        engine.getClass().getCanonicalName(), "searchBench", duration / 1000000.0));
    }
}
