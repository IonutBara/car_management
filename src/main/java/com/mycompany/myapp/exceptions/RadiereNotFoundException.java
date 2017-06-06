package com.mycompany.myapp.exceptions;

/**
 * RadiereNotFoundException
 */
public class RadiereNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public RadiereNotFoundException(String message) {
        super(message);
    }

    public RadiereNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
