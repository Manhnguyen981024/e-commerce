package com.springboots.ecommerce.exceptions;

public class APIException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;

    public APIException() {
        super();
    }

    public APIException(String message) {
        super(message);
        this.message = message;
    }
}
