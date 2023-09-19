package com.example.dg_task.serviceImpl;


import com.example.dg_task.DTO.PersonSaveDto;
import com.example.dg_task.DTO.PersonWithEntityDto;
import com.example.dg_task.entity.Person;
import com.example.dg_task.mapping.DtoToEntity;
import com.example.dg_task.mapping.EntityToDto;
import com.example.dg_task.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final DtoToEntity dtoToEntity;
    private final EntityToDto entityToDto;
    public Person save(PersonSaveDto personSaveDto){
        Person newPerson =  dtoToEntity.toPerson(personSaveDto);
        return personRepository.save(newPerson);
    }
    public List<PersonWithEntityDto> all(){
       List<PersonWithEntityDto> list = new ArrayList<>();
        personRepository.findAll().stream().forEach(i->
                list.add(new PersonWithEntityDto(
                        i.getId(),
                        i.getGender(),
                        i.getTitle(),
                        i.getFirstName(),
                        i.getLastName(),
                        i.getSsn(),
                        i.getPassportNumber(),
                        i.getEmail(),
                        entityToDto.getPhonesDto(i.getPhones()),
                        entityToDto.getAddressesDto(i.getAddresses()),
                        entityToDto.toEntity(i.getEntity())
                ))
        );
        return list;
    }
}
