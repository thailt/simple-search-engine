package com.thai.search.impl;

import com.thai.search.SearchEngine;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Qualifier("fullTextSearch")
public class FullTextSearch implements SearchEngine {

    private static final SearchEngine search = new FullTextSearch();

    public static SearchEngine getInstance() {
        return search;
    }

    private FullTextSearch() {
    }

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

}
