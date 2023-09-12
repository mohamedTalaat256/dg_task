package com.example.demo.exceptions;

public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException() {
    }

    public DuplicateRecordException(String message) {
        super(message);
    }


}