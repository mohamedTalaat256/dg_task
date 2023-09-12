package com.example.demo.service;

import com.example.demo.DTO.EntityDto;
import com.example.demo.entity.TEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TEntityI {

    String saveEntity(EntityDto entityDto);

    Optional<TEntity> findEntityById(Long id);

    String updateEntity(Long id,EntityDto entityDto);

    Page<EntityDto> findAllEntities(int pageNum, int pageSize);


    void deleteEntityById(Long id);
}
