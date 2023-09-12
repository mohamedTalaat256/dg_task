package com.example.dg_task.serviceImpl;


import com.example.dg_task.DTO.PersonSaveDto;
import com.example.dg_task.entity.Person;
import com.example.dg_task.mapping.DtoToEntity;
import com.example.dg_task.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final DtoToEntity dtoToEntity;
   public Person save(PersonSaveDto personSaveDto){
        Person newPerson =  dtoToEntity.toPerson(personSaveDto);
        return personRepository.save(newPerson);
    }

    public List<Person> all(){

        return personRepository.findAll();
    }
}
