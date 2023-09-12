package com.example.dg_task.enumeration;

public enum ContactType {
    AID("As per ID"),B("Business"),P("Private"),UNKNOWN("UNKNOWN");

    private final String describtion;
    ContactType(String describtion){
        this.describtion = describtion;
    }
    public String getDescribtion() {
        return describtion;
    }
}
