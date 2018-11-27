package com.thai;

import com.thai.search.SearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    @Qualifier("fullTextSearch")
    SearchEngine fullTextSearch;

    @Autowired
    @Qualifier("keywordSearch")
    SearchEngine keywordSearch;

    public static void main(String[] args) {
        new SpringApplication(Main.class).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello");
    }
}
