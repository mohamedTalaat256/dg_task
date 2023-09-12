package com.example.demo.serviceImpl;


import com.example.demo.DTO.PersonDto;
import com.example.demo.DTO.PersonSaveDto;
import com.example.demo.entity.Person;
import com.example.demo.mapping.DtoToEntity;
import com.example.demo.repository.EntityRepository;
import com.example.demo.repository.PersonRepository;
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
