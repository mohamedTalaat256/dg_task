package com.example.dg_task.service;

import com.example.dg_task.DTO.EntityDto;
import com.example.dg_task.entity.TEntity;

import java.util.List;
import java.util.Optional;

public interface TEntityI {

    String saveEntity(EntityDto entityDto);

    Optional<TEntity> findEntityById(Long id);

    String updateEntity(Long id,EntityDto entityDto);

    List<EntityDto> findAllEntities();


    void deleteEntityById(Long id);
}
