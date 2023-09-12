package com.example.dg_task.enumeration;

public enum CountryCode {
    AFN("Afghan afghani") ,
    DZD ("Algerian Dinar"),
    AOA ("Angolan kwanza"),
    EG ("Egypt"),
    CAD("Canadian Dollar");


    private final String describtion;
    CountryCode(String describtion){
        this.describtion = describtion;
    }
    public String getDescribtion() {
        return describtion;
    }
}
