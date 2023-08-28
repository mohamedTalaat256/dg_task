package com.example.dg_task.mapping;

import com.example.dg_task.DTO.AddressDto;
import com.example.dg_task.DTO.PersonDto;
import com.example.dg_task.DTO.PhoneDto;
import com.example.dg_task.DTO.RegisterRequestDto;
import com.example.dg_task.entity.Address;
import com.example.dg_task.entity.AppUser;
import com.example.dg_task.entity.Person;
import com.example.dg_task.entity.Phone;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

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






    public static AppUser getUser(RegisterRequestDto requestDto){

        AppUser user = new AppUser();
        user.setEmail(requestDto.getEmail());
        user.setUsername(requestDto.getUsername());
        user.setFullName(requestDto.getFullName());
        user.setPassword(requestDto.getPassword());

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        return user;
    }

}
