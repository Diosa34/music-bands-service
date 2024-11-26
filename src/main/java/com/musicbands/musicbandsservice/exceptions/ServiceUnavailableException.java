package com.musicbands.musicbandsservice.exceptions;

import lombok.Getter;

@Getter
public class ServiceUnavailableException extends Error {
    private final String serviceName;

    public ServiceUnavailableException(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMessage() {
        return String.format("Service %s is not available", serviceName);
    }
}
