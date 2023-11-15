package com.poc.eiger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.poc.eiger.entities.YearTarget;

@EnableJpaRepositories
public interface YearTargetRepository extends JpaRepository<YearTarget, String> {

}
