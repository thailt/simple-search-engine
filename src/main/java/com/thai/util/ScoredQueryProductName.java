package com.thai.util;

import com.thai.search.SearchEngine;

public class ScoredQueryProductName implements Comparable<ScoredQueryProductName> {

    private ScoredProductName scoredProductName;
    private double score;
    private String[] words;
    private String query;

    @Override
    public int compareTo(ScoredQueryProductName o) {
        return Double.compare(o.score, this.score);
    }

    public ScoredQueryProductName(String query, String[] words, ScoredProductName productName) {
        this.scoredProductName = productName;
        this.query = query;
        this.words = words;
    }

    public String[] getWords() {
        return words;
    }

    public void updateScoreByWord(double score) {
        this.score = score;
    }

    public String getLowerAndUnaccentProductName() {
        return this.scoredProductName.getLowerAndUnaccentProductName();
    }

    public String getOriginProductName() {
        return this.scoredProductName.getOriginProductName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScoredQueryProductName that = (ScoredQueryProductName) o;
        return this.scoredProductName.equals(that.scoredProductName) && this.query.equals(that.query);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + this.query.hashCode();
        result = result * prime + this.scoredProductName.hashCode();
        return result;
    }
}
