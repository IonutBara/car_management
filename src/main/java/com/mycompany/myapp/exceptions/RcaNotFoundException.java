package com.mycompany.myapp.exceptions;

/**
 * RcaNotFoundException
 */
public class RcaNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public RcaNotFoundException(String message) {
        super(message);
    }

    public RcaNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
