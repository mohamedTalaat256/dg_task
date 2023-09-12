package com.example.demo.controller;


import com.example.demo.DTO.EntityDto;
import com.example.demo.DTO.PersonDto;
import com.example.demo.DTO.PersonSaveDto;
import com.example.demo.serviceImpl.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<Object> all(){
        return new ResponseEntity<> ( personService.all(), HttpStatus.OK);
    }

    @PostMapping("/persons")
    public ResponseEntity<Object> save(@RequestBody @Valid PersonSaveDto personSaveDto){
        return new ResponseEntity<> ( personService.save(personSaveDto), HttpStatus.OK);
    }
}
