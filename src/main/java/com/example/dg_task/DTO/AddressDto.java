package com.example.dg_task.DTO;

import com.example.dg_task.enumeration.CommunicationType;
import com.example.dg_task.enumeration.CountryCode;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {


    @NotNull
    private CommunicationType addressType;
    @NotNull
    private String address;
    private String town;

    @NotNull
    private String city;
    private String zip;

    @NotNull
    private CountryCode countryCode;

    @NotNull
    private String state;
    private String  comments;
}
