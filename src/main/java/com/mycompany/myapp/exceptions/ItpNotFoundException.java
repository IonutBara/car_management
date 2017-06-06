package com.mycompany.myapp.exceptions;

/**
 * ItpNotFoundException
 */
public class ItpNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ItpNotFoundException(String message) {
        super(message);
    }

    public ItpNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
