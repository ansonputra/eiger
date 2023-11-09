package com.poc.eiger.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.eiger.entities.Region;

public interface RegionRepository extends JpaRepository<Region, String> {
	
	public List<Region> findByNameContaining(String name);
	
}
