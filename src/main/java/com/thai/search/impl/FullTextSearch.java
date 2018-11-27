package com.thai.search.impl;

import com.thai.search.SearchEngine;

import java.util.List;

public class FullTextSearch implements SearchEngine {
    @Override
    public void index(String text) {
        //TODO: index for new String text
    }

    @Override
    public void index(List<String> texts) {
        //TODO: index for new List<String> text
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
