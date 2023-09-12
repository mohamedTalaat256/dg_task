package com.example.demo.DTO;

import com.example.demo.embeded.Email;
import com.example.demo.embeded.PassportNumber;
import com.example.demo.entity.TEntity;
import com.example.demo.enumeration.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonSaveDto {

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
    private Long entityId;
}
