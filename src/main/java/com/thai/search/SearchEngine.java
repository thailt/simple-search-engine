package com.thai.search;

import com.thai.util.StringUtil;

import java.util.List;

public interface SearchEngine {

    void index(String text);

    void index(List<String> texts);

    List<String> search(String text);

    List<String> searchWithoutAccent(String text);
}
