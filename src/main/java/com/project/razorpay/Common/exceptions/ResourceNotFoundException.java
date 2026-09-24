package com.project.razorpay.Common.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName;
    private final Object identifier;

    public ResourceNotFoundException(String resourceName, Object identifier) {
        super(resourceName + "  not found " + identifier);//this is the message
        this.resourceName = resourceName;
        this.identifier = identifier;
    }
}
