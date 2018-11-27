package com.thai;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        new SpringApplication(Main.class).run(args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello");
    }
}
