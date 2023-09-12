package com.example.demo.controller;


import com.example.demo.DTO.EntityDto;
import com.example.demo.serviceImpl.EntityService;
import com.example.demo.utilis.AppResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")

public class EntityController {

    public EntityController(EntityService entityService) {
        this.entityService = entityService;
    }

    private final EntityService entityService;

    @GetMapping("/entities")
    public ResponseEntity<Object> allEntities( @RequestParam int pageNum, @RequestParam int pageSize){
        return new ResponseEntity<> ( entityService.findAllEntities(pageNum, pageSize), HttpStatus.OK);
    }

    @GetMapping("/entities/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        return AppResponse.generateResponse("entity found", HttpStatus.OK, entityService.findEntityById(id), true);
    }

    @PostMapping("/entities")
    public ResponseEntity<Object> saveEntity(@RequestBody @Valid EntityDto request){
        return AppResponse.generateResponse(entityService.saveEntity(request), HttpStatus.OK, null, true);
    }

    @PostMapping("/entities/delete/{id}")
    public ResponseEntity<Object> deleteEntities(@PathVariable Long id){
        entityService.deleteEntityById(id);
        return  AppResponse.generateResponse( "deleted", HttpStatus.OK, null, true);
    }

    @PutMapping("/entities/{id}")
    public ResponseEntity<Object> updateEntity(@PathVariable Long id, @RequestBody @Valid EntityDto request ){
        return AppResponse.generateResponse(entityService.updateEntity(id, request), HttpStatus.OK, null, true);
    }


}
