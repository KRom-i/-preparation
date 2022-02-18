package com.example.homework8frontandback.rest;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDto {

    private LocalDateTime timestamp;

    private String message;

    public ErrorDto (String message) {
        this.timestamp = LocalDateTime.now ();
        this.message = message;
    }
}
