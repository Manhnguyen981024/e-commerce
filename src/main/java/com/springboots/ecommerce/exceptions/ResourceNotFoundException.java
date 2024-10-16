package com.springboots.ecommerce.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String name;
    private long codeId;
    private String codeName;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String name, long codeId, String codeName) {
        super(String.format("%s not found with %s have id: %d ", name, codeName, codeId));
        this.codeId = codeId;
        this.name = name;
        this.codeName = codeName;
    }
}
