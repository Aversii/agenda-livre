package com.aversi.agendalivre.error;

import java.time.LocalDateTime;

public class CustomError extends Error {

    private int statusCode;
    private String message;
    private LocalDateTime timestamp;

    public CustomError(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CustomError{" +
               "statusCode=" + statusCode +
               ", message='" + message + '\'' +
               ", timestamp=" + timestamp +
               '}';
    }
}
