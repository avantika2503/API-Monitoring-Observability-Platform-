package com.example.collector_service.logs;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "api_logs")
public class ApiLog {
    @Id
    private String id;

    private String service;
    private String endpoint;
    private String method;
    private int status;
    private long latencyMs;
    private long requestSize;
    private long responseSize;
    private Instant timestamp;

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getService() {
        return service;
    }
    public void setService(String service) {
        this.service = service;
    }
    public String getEndpoint() {
        return endpoint;
    }
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public long getLatencyMs() {
        return latencyMs;
    }
    public void setLatencyMs(long latencyMs) {
        this.latencyMs = latencyMs;
    }
    public long getRequestSize() {
        return requestSize;
    }
    public void setRequestSize(long requestSize) {
        this.requestSize = requestSize;
    }
    public long getResponseSize() {
        return responseSize;
    }
    public void setResponseSize(long responseSize) {
        this.responseSize = responseSize;
    }
    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    
}
