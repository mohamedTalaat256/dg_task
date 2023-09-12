package com.example.dg_task.enumeration;

public enum Gender {
    M("Male"),F("Female");
    private final String describtion;
    Gender(String describtion){
        this.describtion = describtion;
    }
    public String getDescribtion() {
        return describtion;
    }
}
