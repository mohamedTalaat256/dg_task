package com.example.demo.serviceImpl;


import com.example.demo.DTO.EntityDto;
import com.example.demo.entity.TEntity;
import com.example.demo.exceptions.DuplicateRecordException;
import com.example.demo.exceptions.RecordNotFoundException;
import com.example.demo.mapping.DtoToEntity;
import com.example.demo.mapping.EntityToDto;
import com.example.demo.repository.EntityRepository;
import com.example.demo.service.TEntityI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntityService implements TEntityI {

    private final EntityRepository entityRepository;
    private final DtoToEntity dtoToEntity;


    @Override
    public Page<EntityDto> findAllEntities(int pageNum, int pageSize) {
        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "id"));

        List<EntityDto> entityDtos = EntityToDto.getEntities(entityRepository.findAll( page).getContent());
        int size = entityDtos.size();
        return new PageImpl<>(entityDtos ,page,size);
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
    public void deleteEntityById(Long id){
        entityRepository.deleteById(id);
    }



}
