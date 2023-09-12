package com.example.dg_task.mapping;

import com.example.dg_task.DTO.AddressDto;
import com.example.dg_task.DTO.EntityDto;
import com.example.dg_task.DTO.PersonDto;
import com.example.dg_task.DTO.PhoneDto;
import com.example.dg_task.entity.Address;
import com.example.dg_task.entity.Person;
import com.example.dg_task.entity.Phone;
import com.example.dg_task.entity.TEntity;

import java.util.ArrayList;
import java.util.List;

public class EntityToDto {

    public static List<PhoneDto> getPhonesDto(List<Phone> phones){
        List<PhoneDto> phonesDtoList = new ArrayList<>();

        for (Phone item: phones) {
            phonesDtoList.add(new PhoneDto(
                    item.getTphContactType(),
                    item.getTphCommunicationType(),
                    item.getTphCountryPrefix(),
                    item.getTphNumber(),
                    item.getTphExtension(),
                    item.getComments()
            ));
        }

        return phonesDtoList;
    }
    public static List<AddressDto> getAddressesDto(List<Address> addressList){
        List<AddressDto> addressesDtoList = new ArrayList<>();

        for (Address item: addressList) {
            addressesDtoList.add(new AddressDto(
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

        return addressesDtoList;
    }
    public static List<PersonDto> getDirectors(List<Person> personList ){
        List<PersonDto> personDtoList = new ArrayList<>();

        for (Person item: personList) {


            personDtoList.add((new PersonDto(
                    item.getId(),
                    item.getGender(),
                    item.getTitle(),
                    item.getFirstName(),
                    item.getLastName(),
                    item.getSsn(),
                    item.getPassportNumber(),
                    item.getEmail(),
                    getPhonesDto(item.getPhones()),
                    getAddressesDto(item.getAddresses()),
                   null
                   // item.getEntity()
            )));
        }
        return personDtoList;
    }


    public static List<EntityDto> getEntities(List<TEntity> tEntityList){
        List<EntityDto> entityDtoList = new ArrayList<>();

        for (TEntity item : tEntityList){
            entityDtoList.add(new EntityDto(
                    item.getId(),
                    item.getName(),
                    item.getCommercialName(),
                    item.getBusiness(),
                    getPhonesDto(item.getPhones()),
                    getAddressesDto(item.getAddresses()),
                    getDirectors(item.getDirectors())
            ));
        }
        return entityDtoList;
    }


}
