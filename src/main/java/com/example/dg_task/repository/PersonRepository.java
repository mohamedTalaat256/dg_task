package com.example.dg_task.repository;

import com.example.dg_task.entity.Person;
import com.example.dg_task.entity.TEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
