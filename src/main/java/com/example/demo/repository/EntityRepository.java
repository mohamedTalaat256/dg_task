package com.example.demo.repository;

import com.example.demo.entity.TEntity;
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
     Optional<TEntity> findByName(String name);
}
