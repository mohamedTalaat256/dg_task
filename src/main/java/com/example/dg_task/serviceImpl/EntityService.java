package com.example.dg_task.serviceImpl;


import com.example.dg_task.DTO.EntityDto;
import com.example.dg_task.entity.TEntity;
import com.example.dg_task.exceptions.DuplicateRecordException;
import com.example.dg_task.exceptions.RecordNotFoundException;
import com.example.dg_task.mapping.DtoToEntity;
import com.example.dg_task.mapping.EntityToDto;
import com.example.dg_task.repository.EntityRepository;
import com.example.dg_task.service.TEntityI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntityService implements TEntityI {

    private final EntityRepository entityRepository;
    private final DtoToEntity dtoToEntity;
    private final EntityToDto entityToDto;


    @Override
    public Page<EntityDto> findAllEntities(int pageNum, int pageSize) {
        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "id"));

       return entityRepository.findAll( page).map(entity-> new EntityDto(
               entity.getId(),
               entity.getName(),
               entity.getCommercialName(),
               entity.getBusiness(),
               entityToDto.getPhonesDto(entity.getPhones()),
               entityToDto.getAddressesDto(entity.getAddresses()),
               entityToDto.getDirectors(entity.getDirectors())
       ));
    }

    @Override
    public String saveEntity(EntityDto entityDto) {

        Optional<TEntity> entity = entityRepository.findByName(entityDto.getName());

        if(entity.isPresent()){
            throw new DuplicateRecordException("The Name Of The Entity Is Already Exist.");
        }else{
            TEntity newEntity = TEntity.builder()
                    .name(entityDto.getName())
                    .business(entityDto.getBusiness())
                    .commercialName(entityDto.getCommercialName())
                    .addresses(dtoToEntity.getAddresses(entityDto.getAddresses()))
                    .phones(dtoToEntity.getPhones(entityDto.getPhones()))
                    .directors(dtoToEntity.getDirectors(entityDto.getDirectors()))
                    .build();

            entityRepository.save(newEntity);
            return "saved success";
        }
    }

    @Override
    public String updateEntity(Long id,EntityDto entityDto) {

        Optional<TEntity> entity = findEntityById(id);

        if(!entity.isPresent()){
            throw new RecordNotFoundException("No Entity Found To Update");
        }else{
            TEntity currentEntity = entity.get();

            currentEntity.setName(entityDto.getName());
            currentEntity.setCommercialName(entityDto.getCommercialName());
            currentEntity.setBusiness(entityDto.getBusiness());


            entityRepository.save(currentEntity);
            return "updated success";
        }
    }

    @Override
    public Optional<TEntity> findEntityById(Long id) {
        Optional<TEntity> entity = entityRepository.findById(id);

        if(!entity.isPresent()){
            throw new RecordNotFoundException("Entity Not Found");
        }else{
            return entity;
        }
    }


    @Override
    public List<EntityDto> findEntityByNameContains(String name) {
        List<TEntity> tEntityList = entityRepository.findByNameContains(name);

        return  entityToDto.getEntities(tEntityList);
    }


    @Override
    public void deleteEntityById(Long id){
        entityRepository.deleteById(id);
    }



}
