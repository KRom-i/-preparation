package com.example.homework8frontandback.rest;

public class NotFoundException extends RuntimeException {

    public NotFoundException (String message) {
        super (message);
    }
}