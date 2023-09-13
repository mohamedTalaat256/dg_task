package com.example.dg_task.repository;

import com.example.dg_task.entity.TEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EntityRepository extends JpaRepository<TEntity, Long> {



     List<TEntity> findAll();

     Page<TEntity> findAll(Pageable page);
     List<TEntity> findByNameContains(String name);
     Optional<TEntity> findByName(String name);
}
