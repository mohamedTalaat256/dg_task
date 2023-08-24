package com.example.dg_task.exceptions;

public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException() {
    }

    public DuplicateRecordException(String message) {
        super(message);
    }


}