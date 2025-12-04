package com.example.collector_service.controller;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.collector_service.logs.ApiLog;
import com.example.collector_service.logs.ApiLogRepository;

@RestController
@RequestMapping("/api/logs")

public class LogsController {
    private final ApiLogRepository apiLogRepository;

    public LogsController(ApiLogRepository apiLogRepository) {
        this.apiLogRepository = apiLogRepository;
    }

    // Endpoint that the tracking clients will call
    @PostMapping
    public ResponseEntity<Void> createLog(@RequestBody ApiLog log) {
        if (log.getTimestamp() == null) {
            log.setTimestamp(Instant.now());
        }
        apiLogRepository.save(log);
        return ResponseEntity.ok().build();
    }
}
