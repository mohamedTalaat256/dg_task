package com.example.demo.embeded;


import com.example.demo.enumeration.CountryCode;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PassportNumber {

    private String passportNumber;

    private CountryCode passportCountry;
}
