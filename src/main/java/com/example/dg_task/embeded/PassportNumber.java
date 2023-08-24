package com.example.dg_task.embeded;


import com.example.dg_task.enumeration.CountryCode;
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
