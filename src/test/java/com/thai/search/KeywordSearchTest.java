package com.thai.search;

import com.thai.search.impl.KeywordSearch;

public class KeywordSearchTest extends SearchEngineTest<KeywordSearch> {

    @Override
    public SearchEngine initSearchEngine() {
        return KeywordSearch.getInstance();
    }
}
