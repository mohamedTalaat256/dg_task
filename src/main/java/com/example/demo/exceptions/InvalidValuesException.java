package com.example.demo.exceptions;

public class InvalidValuesException extends RuntimeException{
    public InvalidValuesException() {
    }

    public InvalidValuesException(String message) {
        super(message);
    }


}