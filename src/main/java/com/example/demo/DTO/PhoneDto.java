package com.example.demo.DTO;

import com.example.demo.enumeration.CommunicationType;
import com.example.demo.enumeration.ContactType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {

    @NotNull
    private ContactType tphContactType;

    @NotNull
    private CommunicationType tphCommunicationType;
    private String tphCountryPrefix;

    @NotNull
    private String tphNumber;
    private String tphExtension;
    private String comments;
}
