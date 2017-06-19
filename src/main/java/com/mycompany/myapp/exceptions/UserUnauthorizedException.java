package com.mycompany.myapp.exceptions;

/**
 * UserUnauthorizedException.
 */
public class UserUnauthorizedException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserUnauthorizedException(String message) {
        super(message);
    }

    public UserUnauthorizedException(String message, Throwable t) {
        super(message, t);
    }
}
