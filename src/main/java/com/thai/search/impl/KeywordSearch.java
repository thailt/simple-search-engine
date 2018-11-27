package com.thai.search.impl;

import com.thai.search.SearchEngine;
import com.thai.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("keywordSearch")
public class KeywordSearch implements SearchEngine {

    private static final SearchEngine search = new KeywordSearch();

    public static SearchEngine getInstance() {
        return search;
    }

    private Map<String, Set<String>> indexTable;

    private KeywordSearch() {
        indexTable = new ConcurrentHashMap(10);
    }

    @Override
    public void index(String text) {
        if (StringUtil.isNullOrEmpty(text)) {
            return;
        }
        String unAccentText = StringUtil.removeAccent(text);
        String lowerText = unAccentText.toLowerCase();
        String[] words = StringUtil.split(lowerText);
        for (String word : words) {
            Set<String> productsWithWord = null;
            if (indexTable.containsKey(word)) {
                productsWithWord = indexTable.get(word);
            } else {
                productsWithWord = new TreeSet<String>();
            }
            productsWithWord.add(text);
            indexTable.put(word, productsWithWord);
        }

    }

    @Override
    public void index(List<String> texts) {
        for (String text : texts) {
            index(text);
        }
    }

    @Override
    public List<String> search(String text) {
        String unAccentText = StringUtil.removeAccent(text);
        String lowerText = unAccentText.toLowerCase();
        String[] words = StringUtil.split(lowerText, 20);

        if (words.length == 0) {
            return new ArrayList<>();
        }
        Set<String> products = indexTable.get(words[0]);

        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            if (indexTable.containsKey(word)) {
                products.retainAll(indexTable.get(word));
            }
        }
        return new ArrayList<>(products);
    }
}
