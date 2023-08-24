package com.example.dg_task.controller;


import com.example.dg_task.entity.TEntity;
import com.example.dg_task.service.EntityService;
import com.example.dg_task.service.PersonService;
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
    public ResponseEntity<Object> save(){
        return new ResponseEntity<> ( personService.all(), HttpStatus.OK);
    }
}