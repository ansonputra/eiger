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

import com.poc.eiger.dto.TotalOverview;
import com.poc.eiger.entities.Overview;
import com.poc.eiger.repositories.OverviewRepository;

@RestController
@RequestMapping("/api")
public class OverviewController {
	
	@Autowired
    private OverviewRepository overviewRepository;
	
    @GetMapping("/overviews")
    public ResponseEntity<List<Overview>> findAll(
            @RequestParam(name = "name", 
                    required = false, 
                    defaultValue = "") String year) {
        try {
            List<Overview> overviews;
            if (StringUtils.hasText(year)) {
            	overviews = overviewRepository.findByYearContaining(year);
            } else {
            	overviews = overviewRepository.findAll();
            }

            if (overviews.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(overviews, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("/all/overviews")
    public ResponseEntity<TotalOverview> findAllOv(
            @RequestParam(name = "year", 
                    required = false, 
                    defaultValue = "") String year) {
        try {
            TotalOverview totalOverviewObj = new TotalOverview(); 
            List<Overview> overviews;
            if (StringUtils.hasText(year)) {
            	//businessUnitsObj = businessUnitRepository.findByNameContaining(name);
            } else {
            	//businessUnitsObj = businessUnitRepository.findAll();
            	overviews = overviewRepository.findAll();
            	BigDecimal total = overviewRepository.grandTotal();
            	totalOverviewObj.setGrandTotal(total);
            	List<Overview> overviewList = new ArrayList<>();
            	int sumOverview = overviews.size();
       
            	for (int indexhl = 0; indexhl < sumOverview; indexhl++) {
            		Overview overviewdto = new Overview();
            		overviewdto.setId(overviews.get(indexhl).getId());
            		overviewdto.setYear(overviews.get(indexhl).getYear());
            		overviewdto.setMonth(overviews.get(indexhl).getMonth());
            		overviewdto.setAmount(overviews.get(indexhl).getAmount());
            		overviewList.add(overviewdto);
            	}
            	
            	totalOverviewObj.setOverviews(overviewList);
            }
            
            if (totalOverviewObj.equals(null)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(totalOverviewObj, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("/overview/{id}")
    public ResponseEntity<Overview> findById(
            @PathVariable("id") String id) {
        
        Optional<Overview> overviewlData = overviewRepository.findById(id);

        if (overviewlData.isPresent()) {
            return new ResponseEntity<>(overviewlData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/overviews")
    public ResponseEntity<Overview> create(
            @RequestBody Overview overview) {
        try {
            Overview newOverview = new Overview();
            newOverview.setId(UUID.randomUUID().toString());
            newOverview.setYear(overview.getYear());
            newOverview.setMonth(overview.getMonth());
            newOverview.setAmount(overview.getAmount());
            

            return new ResponseEntity<>(overviewRepository.save(newOverview), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/overviews/all/{id}")
    public ResponseEntity<Overview> updateall(
            @PathVariable("id") String id,
            @RequestBody Overview overview) {

        Optional<Overview> overviewData = overviewRepository.findById(id);

        if (overviewData.isPresent()) {
        	Overview updatedOverview = overviewData.get();
        	updatedOverview.setYear(overview.getYear());
        	updatedOverview.setMonth(overview.getMonth());
        	updatedOverview.setAmount(overview.getAmount());

            return new ResponseEntity<>(overviewRepository.save(updatedOverview), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/overviews/{id}")
    public ResponseEntity<HttpStatus> delete(
            @PathVariable("id") String id) {
        try {
        	overviewRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/overviews")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
        	overviewRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
