package net.guides.springboot2.springboot2jpacrudexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    private static final Long SERIALVERSIONUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
