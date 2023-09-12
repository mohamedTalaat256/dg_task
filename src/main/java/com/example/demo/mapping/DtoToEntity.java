package com.example.demo.mapping;

import com.example.demo.DTO.*;
import com.example.demo.entity.*;
import com.example.demo.exceptions.RecordNotFoundException;
import com.example.demo.repository.EntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@RequiredArgsConstructor
@Service
public class DtoToEntity {
    private final EntityRepository entityRepository;
    private final PasswordEncoder encoder;
    public List<Phone> getPhones(List<PhoneDto> phoneDtoList){
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
    public List<Address> getAddresses(List<AddressDto> addressDtoList){
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
    public List<Person> getDirectors(List<PersonDto> personDtoList ){
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



    public Person toPerson(PersonSaveDto personSaveDto) {
        TEntity entity = entityRepository.findById(personSaveDto.getEntityId()).orElseThrow(() -> new RecordNotFoundException("Entity not found!"));
        // mapping
        Person person = new Person(
                personSaveDto.getGender(),
                personSaveDto.getTitle(),
                personSaveDto.getFirstName(),
                personSaveDto.getLastName(),
                personSaveDto.getSsn(),
                personSaveDto.getPassportNumber(),
                personSaveDto.getEmail(),
                getPhones(personSaveDto.getPhones()),
                getAddresses(personSaveDto.getAddresses())
        );
        entity.addDirector(person);
        return person;
    }

    public AppUser getUser(RegisterRequestDto requestDto, Set<Role> roles){
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
