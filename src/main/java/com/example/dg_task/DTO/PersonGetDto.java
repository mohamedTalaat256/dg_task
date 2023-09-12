package com.example.dg_task.DTO;

import com.example.dg_task.embeded.Email;
import com.example.dg_task.embeded.PassportNumber;
import com.example.dg_task.enumeration.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonGetDto {

    private Long id;
    @NotNull
    private Gender gender;
    private String title;

    @NotNull
    @Length(min = 1, max = 100)
    private String firstName;

    @NotNull
    @Length(min = 1, max = 100)
    private String lastName;

    @NotNull
    private String ssn;
    private PassportNumber passportNumber;
    private Email email;
    private List<PhoneDto> phones = new ArrayList<>();
    private List<AddressDto> addresses = new ArrayList<>();
    private EntityDto tEntityDto;
}
