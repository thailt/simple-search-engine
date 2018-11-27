package com.thai.search.impl;

import com.thai.search.SearchEngine;

import java.util.List;

public class KeywordSearch implements SearchEngine {
    @Override
    public void index(String text) {

    }

    @Override
    public void index(List<String> texts) {

    }

    @Override
    public List<String> search(String text) {
        return null;
    }

    @Override
    public List<String> searchWithoutAccent(String text) {
        return null;
    }
}
