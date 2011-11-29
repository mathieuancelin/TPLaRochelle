package com.acme.facemash.controllers;

import javax.enterprise.inject.Model;

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
