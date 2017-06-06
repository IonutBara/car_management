package com.mycompany.myapp.exceptions;

/**
 * CascoNotFoundException
 */
public class CascoNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CascoNotFoundException(String message) {
        super(message);
    }

    public CascoNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}

