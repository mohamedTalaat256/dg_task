package com.example.dg_task.service;


import com.example.dg_task.DTO.EntityDto;
import com.example.dg_task.entity.TEntity;
import com.example.dg_task.exceptions.DuplicateRecordException;
import com.example.dg_task.exceptions.RecordNotFoundException;
import com.example.dg_task.mapping.DtoToEntity;
import com.example.dg_task.repository.EntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntityService {

    private final EntityRepository entityRepository;



    public String saveEntities(EntityDto entityDto) {

        Optional<TEntity> entity = entityRepository.findByName(entityDto.getName());

        if(entity.isPresent()){
            throw new DuplicateRecordException("The Name Of The Entity Is Already Exist.");
        }else{
            TEntity newEntity = TEntity.builder()
                    .name(entityDto.getName())
                    .business(entityDto.getBusiness())
                    .commercialName(entityDto.getCommercialName())
                    .addresses(DtoToEntity.getAddresses(entityDto.getAddresses()))
                    .phones(DtoToEntity.getPhones(entityDto.getPhones()))
                    .directors(DtoToEntity.getDirectors(entityDto.getDirectors()))
                    .build();

            entityRepository.save(newEntity);
            return "saved success";
        }
    }

    public String updateEntity(Long id,EntityDto entityDto) {

        Optional<TEntity> entity = findById(id);

        if(!entity.isPresent()){
            throw new RecordNotFoundException("No Entity Found To Update");
        }else{
           TEntity currentEntity = entity.get();

            currentEntity.setName(entityDto.getName());
                    /*.business(entityDto.getBusiness())
                    .commercialName(entityDto.getCommercialName())
                    .addresses(DtoToEntity.getAddresses(entityDto.getAddresses()))
                    .phones(DtoToEntity.getPhones(entityDto.getPhones()))
                    .directors(DtoToEntity.getDirectors(entityDto.getDirectors()))*/
                    //.build();

            entityRepository.save(currentEntity);
            return "updated success";
        }
    }




    public List<TEntity> allEntities(){

        return entityRepository.findAll();
    }

    public Optional<TEntity> findById(Long id){

        Optional<TEntity> entity = entityRepository.findById(id);

        if(!entity.isPresent()){
            throw new RecordNotFoundException("Entity Not Found");
        }else{
            return entity;
        }
    }


    public void delete(Long id){
        entityRepository.deleteById(id);
    }
}
