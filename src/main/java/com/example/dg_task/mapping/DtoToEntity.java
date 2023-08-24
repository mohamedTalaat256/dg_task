package com.example.dg_task.mapping;

import com.example.dg_task.DTO.AddressDto;
import com.example.dg_task.DTO.PersonDto;
import com.example.dg_task.DTO.PhoneDto;
import com.example.dg_task.entity.Address;
import com.example.dg_task.entity.Person;
import com.example.dg_task.entity.Phone;

import java.util.ArrayList;
import java.util.List;

public class DtoToEntity {
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

}
