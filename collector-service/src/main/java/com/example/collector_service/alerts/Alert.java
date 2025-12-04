package com.example.collector_service.alerts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "alerts")
public class Alert {

    @Id
    private String id;

    private String apiName;           // which API triggered alert
    private String level;             // INFO / WARNING / ERROR / CRITICAL
    private String description;       // human readable message
    private LocalDateTime timestamp;  // when alert was generated
    private long responseTime;        // optional — for latency based alerts
    private int statusCode;           // optional — for failure alerts
    private boolean resolved = false; // later UI will mark resolved

    public Alert(String apiName, String level, String description,
                 long responseTime, int statusCode) {
        this.apiName = apiName;
        this.level = level;
        this.description = description;
        this.responseTime = responseTime;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
    }

    // Getters + Setters
    public String getId() { return id; }
    public String getApiName() { return apiName; }
    public void setApiName(String apiName) { this.apiName = apiName; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getTimestamp() { return timestamp; }

    public long getResponseTime() { return responseTime; }
    public void setResponseTime(long responseTime) { this.responseTime = responseTime; }

    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
}
