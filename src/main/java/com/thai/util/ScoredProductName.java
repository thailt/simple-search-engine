package com.thai.util;

public class ScoredProductName implements Comparable<ScoredProductName> {

    private ProductName productName;
    private double score;
    private String word;

    @Override
    public int compareTo(ScoredProductName o) {
        return Double.compare(o.score, this.score);
    }

    public ScoredProductName(String word, ProductName productName) {
        this.word = word;
        this.productName = productName;
    }

    public void updateScoreByWord(String word, double score) {
        this.word = word;
        this.score = score;
    }

    public String getLowerAndUnaccentProductName() {
        return this.productName.getLowerAndUnaccentProductName();
    }

    public String getOriginProductName() {
        return this.productName.getOriginProductName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScoredProductName that = (ScoredProductName) o;
        return this.productName.equals(that.productName) && this.word.equals(that.word);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + this.word.hashCode();
        result = result * prime + this.productName.hashCode();
        return result;
    }
}
