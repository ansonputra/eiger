package com.poc.eiger.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.eiger.entities.BusinessUnit;
import com.poc.eiger.entities.Channel;
import com.poc.eiger.repositories.ChannelRepository;

@RestController
@RequestMapping("/api")
public class ChannelController {
	
	@Autowired
    private ChannelRepository channelRepository;

    @GetMapping("/channels")
    public ResponseEntity<List<Channel>> findAll(
            @RequestParam(name = "name", 
                    required = false, 
                    defaultValue = "") String name) {
        try {
            List<Channel> channels;
            if (StringUtils.hasText(name)) {
            	channels = channelRepository.findByNameContaining(name);
            } else {
            	channels = channelRepository.findAll();
            }

            if (channels.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(channels, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("/all/channels")
    public ResponseEntity<BusinessUnit> findAllCh(
            @RequestParam(name = "name", 
                    required = false, 
                    defaultValue = "") String name) {
        try {
            BusinessUnit businessUnitsObj = new BusinessUnit(); 
            List<Channel> channels;
            if (StringUtils.hasText(name)) {
            	//businessUnitsObj = businessUnitRepository.findByNameContaining(name);
            } else {
            	//businessUnitsObj = businessUnitRepository.findAll();
            	channels = channelRepository.findAll();
            	businessUnitsObj.setName("businessUnit");
            	List<Channel> channelList = new ArrayList<>();
            	int sumChannel = channels.size();
       
            	for (int indexhl = 0; indexhl < sumChannel; indexhl++) {
            		Channel channeldto = new Channel();
            		channeldto.setId(channels.get(indexhl).getId());
            		channeldto.setName(channels.get(indexhl).getName());
            		channeldto.setPercentagePY(channels.get(indexhl).getPercentagePY());
            		channeldto.setpYSales(channels.get(indexhl).getpYSales());
            		channeldto.setFcstSales(channels.get(indexhl).getFcstSales());
            		channeldto.setHold(channels.get(indexhl).getHold());
            		channeldto.setOverride(channels.get(indexhl).getOverride());
            		channeldto.setOverrideValue(channels.get(indexhl).getOverrideValue());
            		channeldto.setFinalTarget(channels.get(indexhl).getFinalTarget());
            		channelList.add(channeldto);
            	}
            	
            	businessUnitsObj.setChannels(channelList);
            }
            
            if (businessUnitsObj.equals(null)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(businessUnitsObj, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/channels/{id}")
    public ResponseEntity<Channel> findById(
            @PathVariable("id") String id) {
        
        Optional<Channel> channelData = channelRepository.findById(id);

        if (channelData.isPresent()) {
            return new ResponseEntity<>(channelData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/channels")
    public ResponseEntity<Channel> create(
            @RequestBody Channel channel) {
        try {
            Channel newChannel = new Channel();
            newChannel.setId(UUID.randomUUID().toString());
            newChannel.setName(channel.getName());
            newChannel.setPercentagePY(channel.getPercentagePY());
            newChannel.setpYSales(channel.getpYSales());
            newChannel.setFcstSales(channel.getFcstSales());
            newChannel.setHold(channel.getHold());
            newChannel.setOverride(channel.getOverride());
            newChannel.setOverrideValue(channel.getOverrideValue());
            newChannel.setFinalTarget(channel.getFinalTarget());
            return new ResponseEntity<>(channelRepository.save(newChannel), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/channels/{id}")
    public ResponseEntity<Channel> update(
            @PathVariable("id") String id,
            @RequestBody Channel channel) {

        Optional<Channel> channelData = channelRepository.findById(id);
        Boolean prevHold = channelData.get().getHold();
        Boolean prevOverride = channelData.get().getHold();
        BigDecimal prevOverrideValue = channelData.get().getOverrideValue();
        BigDecimal prevFcstSales = channelData.get().getFcstSales();

        if (channelData.isPresent()) {
        	Channel updatedChannel = channelData.get();
        	updatedChannel.setName(channelData.get().getName());
        	updatedChannel.setPercentagePY(channelData.get().getPercentagePY());
        	updatedChannel.setpYSales(channelData.get().getpYSales());
        	updatedChannel.setFcstSales(channelData.get().getFcstSales());
        	if(channel.getHold() == null) {
        		updatedChannel.setHold(prevHold);
        	}else {
        		updatedChannel.setHold(channel.getHold());
        	}
        	
        	if(channel.getOverride() == null) {
        		updatedChannel.setOverride(prevOverride);
        	}else {
        		updatedChannel.setOverride(channel.getOverride());
        	}
        	
        	if(channel.getOverrideValue() == null) {
        		updatedChannel.setOverrideValue(prevOverrideValue);
        	}else {
        		updatedChannel.setOverrideValue(channel.getOverrideValue());
        	}
        	
        	if(channel.getHold() == true) {
        		updatedChannel.setFinalTarget(prevFcstSales);
        	}else {
        		updatedChannel.setFinalTarget(channel.getOverrideValue());
        	}
        	

            return new ResponseEntity<>(channelRepository.save(updatedChannel), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/channels/all/{id}")
    public ResponseEntity<Channel> updateall(
            @PathVariable("id") String id,
            @RequestBody Channel channel) {

        Optional<Channel> channelData = channelRepository.findById(id);

        if (channelData.isPresent()) {
        	Channel updatedChannel = channelData.get();
        	updatedChannel.setName(channel.getName());
        	updatedChannel.setPercentagePY(channel.getPercentagePY());
        	updatedChannel.setpYSales(channel.getpYSales());
        	updatedChannel.setFcstSales(channel.getFcstSales());
        	updatedChannel.setHold(channel.getHold());
        	updatedChannel.setOverride(channel.getOverride());
        	updatedChannel.setOverrideValue(channel.getOverrideValue());
            updatedChannel.setFinalTarget(channel.getFinalTarget());

            return new ResponseEntity<>(channelRepository.save(updatedChannel), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/channels/{id}")
    public ResponseEntity<HttpStatus> delete(
            @PathVariable("id") String id) {
        try {
        	channelRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/channels")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
        	channelRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
