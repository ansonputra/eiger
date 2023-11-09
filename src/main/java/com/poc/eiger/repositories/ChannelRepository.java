package com.poc.eiger.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.poc.eiger.entities.Channel;

@EnableJpaRepositories
public interface ChannelRepository extends JpaRepository<Channel, String> {

	public List<Channel> findByNameContaining(String name);
	
}
