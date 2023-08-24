package com.example.dg_task.DTO;

import com.example.dg_task.enumeration.CommunicationType;
import com.example.dg_task.enumeration.ContactType;
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
