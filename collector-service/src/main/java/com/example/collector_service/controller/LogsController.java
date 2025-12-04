package com.example.collector_service.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping
    public Iterable<ApiLog> getLogs(
            @RequestParam(required = false) String service,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false, defaultValue = "false") boolean slowOnly
    ) {
        Iterable<ApiLog> all = apiLogRepository.findAll();

        // simple in-memory filtering – enough for v1
        List<ApiLog> list = apiLogRepository.findAll();
        return list.stream()
                .filter(log -> service == null || service.equals(log.getService()))
                .filter(log -> status == null || status == log.getStatus())
                .filter(log -> !slowOnly || log.getLatencyMs() > 500)
                .toList();
    }
}
