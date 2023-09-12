package com.example.dg_task.enumeration;

public enum CommunicationType {
    F("Fax"),
    L("Landline Phone"),
    M("Mobile Phone"),
    UNKNOWN("UNKNOWN");


    private final String describtion;
    CommunicationType(String describtion){
        this.describtion = describtion;
    }
    public String getDescribtion() {
        return describtion;
    }
}
