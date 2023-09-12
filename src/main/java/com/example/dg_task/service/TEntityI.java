package com.example.dg_task.service;

import com.example.dg_task.DTO.EntityDto;
import com.example.dg_task.entity.TEntity;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface TEntityI {

    String saveEntity(EntityDto entityDto);

    Optional<TEntity> findEntityById(Long id);

    String updateEntity(Long id,EntityDto entityDto);

    Page<EntityDto> findAllEntities(int pageNum, int pageSize);


    void deleteEntityById(Long id);
}
