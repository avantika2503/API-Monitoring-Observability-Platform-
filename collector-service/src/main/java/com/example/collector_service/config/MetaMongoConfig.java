package com.example.collector_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.example.collector_service.meta",
        mongoTemplateRef = "metaMongoTemplate"
)
public class MetaMongoConfig {
    // This MongoTemplate talks to metadata DB (users, issues, alerts).
    @Bean
    public MongoTemplate metaMongoTemplate() {
        return new MongoTemplate(
                MongoClients.create("mongodb://localhost:27017"),
                "meta_db"
        );
    }
}
