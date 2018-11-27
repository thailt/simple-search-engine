package com.thai.search;

import com.thai.search.impl.FullTextSearch;

public class FulltextSearchTest extends SearchEngineTest<FullTextSearch> {

    @Override
    public SearchEngine initSearchEngine() {
        return FullTextSearch.getInstance();
    }
}
