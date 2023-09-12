package com.example.demo.DTO;


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
public class EntityInnerDto {

    private Long id;
    private String name;
    private String commercialName;
    private String business;
    private List<PhoneDto> phones = new ArrayList<>();
    private List<AddressDto> addresses = new ArrayList<>();
}
