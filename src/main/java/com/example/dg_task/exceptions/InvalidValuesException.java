package com.example.dg_task.exceptions;

public class InvalidValuesException extends RuntimeException{
    public InvalidValuesException() {
    }

    public InvalidValuesException(String message) {
        super(message);
    }


}