package com.poc.eiger.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.poc.eiger.entities.Overview;

@EnableJpaRepositories
public interface OverviewRepository extends JpaRepository<Overview, String> {

	public List<Overview> findByYearContaining(String year);
	
	@Query(value = "SELECT SUM(o.amount) FROM overview o", nativeQuery = true)
	public BigDecimal grandTotal();
	
}
