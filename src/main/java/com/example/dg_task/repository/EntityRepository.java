package com.example.dg_task.repository;

import com.example.dg_task.entity.TEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EntityRepository extends JpaRepository<TEntity, Long> {

    public Optional<TEntity> findByName(String name);
}
