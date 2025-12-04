package com.example.collector_service.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.collector_service.logs.ApiLog;
import com.example.collector_service.logs.ApiLogRepository;
import com.example.collector_service.alerts.Alert;
import com.example.collector_service.alerts.AlertRepository;

@RestController
@RequestMapping("/api/logs")
public class LogsController {

    private final ApiLogRepository apiLogRepository;
    private final AlertRepository alertRepository;

    public LogsController(ApiLogRepository apiLogRepository,
                          AlertRepository alertRepository) {
        this.apiLogRepository = apiLogRepository;
        this.alertRepository = alertRepository;
    }

    // tracking clients will POST logs here
    @PostMapping
    public ResponseEntity<Void> createLog(@RequestBody ApiLog log) {
        if (log.getTimestamp() == null) {
            log.setTimestamp(Instant.now());
        }

        ApiLog saved = apiLogRepository.save(log);

        // Rule 1: slow request
         // slow request
        if (saved.getLatencyMs() > 500) {
            createAlert(
                    "WARNING",
                    "Slow response (>500ms)",
                    saved
            );
        }

        // Rule 2: server error
        if (saved.getStatus() >= 500 && saved.getStatus() <= 599) {
            createAlert(
                    "CRITICAL",
                    "5xx server error",
                    saved
            );
        }

        return ResponseEntity.ok().build();
    }

    private void createAlert(String level, String description, ApiLog log) {
        String apiName = log.getService() + " " + log.getEndpoint();
        long responseTime = log.getLatencyMs();
        int statusCode = log.getStatus();

        Alert alert = new Alert(
                apiName,
                level,
                description,
                responseTime,
                statusCode
        );

        alertRepository.save(alert);
    }

    @GetMapping
    public List<ApiLog> getLogs(
            @RequestParam(required = false) String service,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false, defaultValue = "false") boolean slowOnly
    ) {
        List<ApiLog> list = apiLogRepository.findAll();
        return list.stream()
                .filter(log -> service == null || service.equals(log.getService()))
                .filter(log -> status == null || status == log.getStatus())
                .filter(log -> !slowOnly || log.getLatencyMs() > 500)
                .toList();
    }
}
