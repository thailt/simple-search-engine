package com.thai.util;

public class ScoredProductName implements Comparable<ScoredProductName> {

    private ProductName productName;
    private double score;
    private String word;

    @Override
    public int compareTo(ScoredProductName o) {
        return Double.compare(o.score, this.score);
    }

    public ScoredProductName(ProductName productName) {
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
        return productName.equals(that.productName);
    }

    @Override
    public int hashCode() {
        return productName.hashCode();
    }
}
