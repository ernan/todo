package com.fodala;

import com.fodala.service.DataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    private DataLoader dataLoader;

    public static void main(String[] args) {
        logger.info("Running application.");
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        dataLoader.loadData("data/schema.sql");
        dataLoader.loadData("data/data.sql");
    }

}
