package com.example.collector_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.example.collector_service.logs",
        mongoTemplateRef = "logsMongoTemplate"
)
public class LogsMongoConfig {
    // This MongoTemplate talks to the "logs_db" database.
    @Bean
    @Primary
    public MongoTemplate logsMongoTemplate() {
        return new MongoTemplate(
                MongoClients.create("mongodb://localhost:27017"),
                "logs_db"
        );
    }
}
