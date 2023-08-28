package com.example.dg_task.mapping;

import com.example.dg_task.DTO.*;
import com.example.dg_task.entity.*;
import com.example.dg_task.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DtoToEntity {


    @Autowired
    private static PasswordEncoder encoder;
    public static List<Phone> getPhones(List<PhoneDto> phoneDtoList){
        List<Phone> phones = new ArrayList<>();

        for (PhoneDto item: phoneDtoList) {
            phones.add(new Phone(
                    null,
                    item.getTphContactType(),
                    item.getTphCommunicationType(),
                    item.getTphCountryPrefix(),
                    item.getTphNumber(),
                    item.getTphExtension(),
                    item.getComments()
            ));
        }

        return phones;
    }
    public static List<Address> getAddresses(List<AddressDto> addressDtoList){
        List<Address> addresses = new ArrayList<>();

        for (AddressDto item: addressDtoList) {
            addresses.add(new Address(
                    null,
                    item.getAddressType(),
                    item.getAddress(),
                    item.getTown(),
                    item.getCity(),
                    item.getZip(),
                    item.getCountryCode(),
                    item.getState(),
                    item.getComments()
            ));
        }

        return addresses;
    }
    public static List<Person> getDirectors(List<PersonDto> personDtoList ){
        List<Person> persons = new ArrayList<>();


        for (PersonDto item: personDtoList) {
            persons.add(new Person(
                    item.getGender(),
                    item.getTitle(),
                    item.getFirstName(),
                    item.getLastName(),
                    item.getSsn(),
                    item.getPassportNumber(),
                    item.getEmail(),
                    getPhones(item.getPhones()),
                    getAddresses(item.getAddresses())
            ));
        }

        return persons;
    }






    public static AppUser getUser(RegisterRequestDto requestDto, Set<Role> roles){
        AppUser user = new AppUser();




        user.setEmail(requestDto.getEmail());
        user.setUsername(requestDto.getUsername());
        user.setFullName(requestDto.getFullName());
        user.setPassword(requestDto.getPassword());

        user.setRoles(roles);

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        return user;
    }



}
