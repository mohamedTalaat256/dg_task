package com.example.dg_task.service;

import com.example.dg_task.DTO.EntityDto;
import com.example.dg_task.DTO.EntityWithNameDto;
import com.example.dg_task.entity.TEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TEntityI {

    String saveEntity(EntityDto entityDto);

    Optional<TEntity> findEntityById(Long id);
    List<EntityWithNameDto> selectNameFromEntities();

    String updateEntity(Long id,EntityDto entityDto);

    Page<EntityDto> findAllEntities(int pageNum, int pageSize);
    List<EntityDto> findEntityByNameContainsOrCommercialNameContains(String name,String commercialName);


    void deleteEntityById(Long id);
}
