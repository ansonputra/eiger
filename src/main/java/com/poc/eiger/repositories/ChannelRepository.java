package com.poc.eiger.repositories;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.poc.eiger.entities.Channel;

@EnableJpaRepositories
public interface ChannelRepository extends JpaRepository<Channel, String> {

	public List<Channel> findByNameContaining(String name);
	
	@Query(value = "SELECT SUM(c.percentagePY) FROM channel c", nativeQuery = true)
	public float totalpercentagePY();
	
	@Query(value = "SELECT SUM(c.pYSales) FROM channel c", nativeQuery = true)
	public BigDecimal totalpYSales();
	
}
