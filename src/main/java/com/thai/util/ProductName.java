package com.thai.util;

import java.util.HashMap;
import java.util.Map;

public class ProductName {

    private String originProductName;
    private String lowerAndUnaccentProductName;
    private Map<String, Integer> wordFrequency;
    private Integer numberWords;
    private String[] words;

    public String getLowerAndUnaccentProductName() {
        return this.lowerAndUnaccentProductName;
    }

    public ProductName(String productName) {
        this.originProductName = productName;
        lowerAndUnaccentProductName = StringUtil.removeAccent(this.originProductName).toLowerCase();
        words = StringUtil.split(this.lowerAndUnaccentProductName);
        numberWords = words.length;
        wordFrequency = computeWordPrequency(this.words);
    }

    public String getOriginProductName() {
        return originProductName;
    }

    private Map<String, Integer> computeWordPrequency(String[] words) {
        Map<String, Integer> result = new HashMap<>(words.length);
        for (String word : words) {
            if (result.containsKey(word)) {
                Integer count = result.get(word);
                count++;
                result.put(word, count);
            } else {
                result.put(word, 1);
            }
        }
        return result;
    }

    public Integer getWordFrequency(String singleWord) {
        if (wordFrequency.containsKey(singleWord)) {
            return wordFrequency.get(singleWord);
        } else {
            return 0;
        }
    }

    public Integer numberOfWord() {
        return this.numberWords;
    }

    public String[] getWords() {
        return this.words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductName that = (ProductName) o;
        return originProductName.equals(that.originProductName);
    }

    @Override
    public int hashCode() {
        return originProductName.hashCode();
    }
}
