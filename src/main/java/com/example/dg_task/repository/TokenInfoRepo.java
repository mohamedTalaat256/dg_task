package com.example.dg_task.repository;

import com.example.dg_task.entity.TokenInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TokenInfoRepo extends JpaRepository<TokenInfo, Long> {

    Optional<TokenInfo> findByRefreshToken (String refreshToken);

}