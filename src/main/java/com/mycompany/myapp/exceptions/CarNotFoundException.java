package com.mycompany.myapp.exceptions;

/**
 * Created by ibara on 12/6/2016.
 */
public class CarNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CarNotFoundException(String message) {
        super(message);
    }

    public CarNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
