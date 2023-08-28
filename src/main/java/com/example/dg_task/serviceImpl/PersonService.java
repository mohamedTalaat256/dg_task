package com.example.dg_task.serviceImpl;


import com.example.dg_task.entity.Person;
import com.example.dg_task.repository.EntityRepository;
import com.example.dg_task.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final EntityRepository entityRepository;

   public Person save(Person person){


       Person newPerson = Person.builder()
               .email(person.getEmail())
               .ssn(person.getSsn())
               .firstName(person.getFirstName())
               .lastName(person.getLastName())
               .title(person.getTitle())
               .passportNumber(person.getPassportNumber())
               .addresses(person.getAddresses())
               .phones(person.getPhones())
               .entity(person.getEntity())
               .build();



        return personRepository.save(newPerson);
    }

    public List<Person> all(){

        return personRepository.findAll();
    }
}
