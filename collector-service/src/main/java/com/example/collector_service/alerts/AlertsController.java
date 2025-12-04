package com.example.collector_service.alerts;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/alerts")
public class AlertsController {
    
    private final AlertRepository alertRepository;

    public AlertsController(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    // return all alerts (for now)
    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
}
