package com.acme.facemash.controllers;

import javax.enterprise.inject.Model;

/**
 * This model is used to pass error messages to error view.
 * 
 * @author mathieuancelin
 */
@Model
public class ErrorModel {
    
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
