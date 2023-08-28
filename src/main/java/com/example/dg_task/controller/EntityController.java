package com.example.dg_task.controller;


import com.example.dg_task.DTO.EntityDto;
import com.example.dg_task.serviceImpl.EntityService;
import com.example.dg_task.utilis.AppResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
//@RequiredArgsConstructor
public class EntityController {

    public EntityController(EntityService entityService) {
        this.entityService = entityService;
    }

    private final EntityService entityService;

    @GetMapping("/entities")
    public ResponseEntity<Object> allEntities(){
        return new ResponseEntity<> ( entityService.findAllEntities(), HttpStatus.OK);
    }

    @GetMapping("/entities/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        return new ResponseEntity<> ( entityService.findEntityById(id), HttpStatus.OK);
    }

    @PostMapping("/entities")
    public ResponseEntity<Object> saveEntity(@RequestBody @Valid EntityDto request){
        return AppResponse.generateResponse(entityService.saveEntity(request), HttpStatus.OK, null, true);
    }

    @PostMapping("/entities/delete/{id}")
    public ResponseEntity<Object> deleteEntities(@PathVariable Long id){
        entityService.deleteEntityById(id);
        return new ResponseEntity<> ( "deleted", HttpStatus.OK);
    }

    @PutMapping("/entities/{id}")
    public ResponseEntity<Object> updateEntity(@PathVariable Long id, @RequestBody @Valid EntityDto request ){
        return AppResponse.generateResponse(entityService.updateEntity(id, request), HttpStatus.OK, null, true);
    }


}
