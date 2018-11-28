package com.thai.search.impl;

import com.thai.search.SearchEngine;
import com.thai.util.ProductName;
import com.thai.util.ScoredProductName;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Qualifier("fullTextSearch")
public class FullTextSearch implements SearchEngine {

    private static final SearchEngine search = new FullTextSearch();
    private static final double MIN_IDF = 0.0000000001;

    private double totalDocumentLength = 0;
    private double avgDocumentLength = 1;
    private double numberOfDocument = 0;
    private double b = 0.75;
    private double k1 = 2.0;

    private Map<String, Set<ScoredProductName>> indexTable;
    private Map<String, ProductName> productNameMap;

    public static SearchEngine getInstance() {
        return search;
    }

    private FullTextSearch() {
        indexTable = new ConcurrentHashMap<>(10);
        productNameMap = new ConcurrentHashMap<>(10);
        b = 0.75;
        k1 = 2.0;
        numberOfDocument = 0;
        totalDocumentLength = 0;
        avgDocumentLength = 1;
    }

    @Override
    public void index(String text) {
        ProductName productName = new ProductName(text);
        if (productNameMap.containsKey(productName.getLowerAndUnaccentProductName())) {
            return;
        }
        productNameMap.put(productName.getLowerAndUnaccentProductName(), productName);

        indexingTable(productName);

        numberOfDocument++;
        totalDocumentLength += productName.numberOfWord();
        avgDocumentLength = totalDocumentLength / numberOfDocument;
    }

    private void indexingTable(ProductName productName) {
        String[] words = productName.getWords();
        for (String word : words) {
            Set<ScoredProductName> productsWithWord = null;
            if (indexTable.containsKey(word)) {
                productsWithWord = indexTable.get(word);
            } else {
                productsWithWord = new HashSet<ScoredProductName>();
            }
            productsWithWord.add(new ScoredProductName(productName));
            indexTable.put(word, productsWithWord);
        }
    }

    @Override
    public void index(List<String> texts) {
        for (String text : texts) {
            index(text);
        }
        scoringKeyword();
    }

    public void scoringKeyword() {
        for (Entry<String, Set<ScoredProductName>> entry : indexTable.entrySet()) {
            String word = entry.getKey();
            Set<ScoredProductName> scoredProductNames = entry.getValue();
            for (ScoredProductName scoredProductName : scoredProductNames) {
                double scoreByWord = scoring(word,
                    scoredProductName.getLowerAndUnaccentProductName());
                scoredProductName.updateScoreByWord(word, scoreByWord);
            }
        }
    }


    private double frequency(String word, String product) {
        if (this.productNameMap.containsKey(product)) {
            ProductName productName = this.productNameMap
                .get(product);
            return productName.getWordFrequency(word);
        } else {
            return 0.0;
        }
    }

    private double idf(String singleWord) {
        Set products = indexTable.get(singleWord);
        double numberOfContainKeyword = products.size();
        double rawIdf = Math.log(
            (this.numberOfDocument - numberOfContainKeyword + 0.5) / (numberOfContainKeyword
                + 0.5));
        if (rawIdf < 0) {
            return MIN_IDF;
        } else {
            return rawIdf;
        }
    }

    private double scoring(String word, String productName) {
        double frequency = frequency(word, productName);
        return idf(word) * frequency * (this.k1 + 1) / (frequency + this.k1 * (1 - this.b
            + this.b * numberOfWord(productName) / this.avgDocumentLength));
    }

    private double scoring(String[] words, String productName) {
        double score = 0.0;
        for (String word : words) {
            score += scoring(word, productName);
        }
        return score;
    }

    private double numberOfWord(String productName) {
        if (this.productNameMap.containsKey(productName)) {
            return this.productNameMap.get(productName).numberOfWord();
        } else {
            return (double) SearchEngine.tokenToSearchWords(productName).length;
        }
    }

    @Override
    public List<String> search(String text) {
        String[] words = SearchEngine.tokenToSearchWords(text);

        if (words.length == 0) {
            return new ArrayList<>();
        }
        Set<ScoredProductName> products = indexTable.get(words[0]);

        if (products == null) {
            return new ArrayList<>();
        }

        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            if (indexTable.containsKey(word)) {
                products.retainAll(indexTable.get(word));
            }
        }

        Set<ScoredProductName> result = new TreeSet();
        result.addAll(products);
        List<String> productSearch = result.stream()
            .map(scoredProductName -> scoredProductName.getOriginProductName())
            .collect(Collectors
                .toList());

        return productSearch;
    }

}
