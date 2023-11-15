package com.poc.eiger.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.eiger.entities.YearTarget;
import com.poc.eiger.repositories.OverviewRepository;
import com.poc.eiger.repositories.YearTargetRepository;

@RestController
@RequestMapping("/api")
public class YearTargetController {

	@Autowired
	private YearTargetRepository yearTargetRepository;

	@Autowired
	private OverviewRepository overviewRepository;

	@PostMapping("/yeartarget")
	public ResponseEntity<YearTarget> create(@RequestBody YearTarget yearTarget) {
		
		YearTarget newyearTargetCalulation = new YearTarget();
		
		try {
			
			List<YearTarget> yearTargetsData = yearTargetRepository.findAll();
			float f1 = yearTarget.getInflation();
			float f2 = yearTarget.getInflationMultiplier();
			float fresult = f1 * f2;
			BigDecimal total = overviewRepository.grandTotal();
			BigDecimal totalPercentage = new BigDecimal(Float.toString(fresult / 100));
			BigDecimal valuePercentage = total.multiply(totalPercentage);
			BigDecimal totalGrowthValue = total.add(valuePercentage);
			
			if (yearTargetsData.size() > 0) {
				newyearTargetCalulation = yearTargetsData.get(0);
				newyearTargetCalulation.setInflation(yearTarget.getInflation());
				newyearTargetCalulation.setInflationMultiplier(yearTarget.getInflationMultiplier());
				newyearTargetCalulation.setTargetGrowthPercentage(fresult);
				newyearTargetCalulation.setTargetGrowth(totalGrowthValue);
				newyearTargetCalulation.setFinalTargetGrowth(yearTarget.getFinalTargetGrowth());
	        	
			} else if (yearTargetsData.isEmpty()) {
				
				
				newyearTargetCalulation.setId(UUID.randomUUID().toString());
				newyearTargetCalulation.setInflation(yearTarget.getInflation());
				newyearTargetCalulation.setInflationMultiplier(yearTarget.getInflationMultiplier());
				newyearTargetCalulation.setTargetGrowthPercentage(fresult);


				newyearTargetCalulation.setTargetGrowth(totalGrowthValue);
				newyearTargetCalulation.setFinalTargetGrowth(yearTarget.getFinalTargetGrowth());
				
				
			} else {
				new ResponseEntity<>("Year Target Already Created", HttpStatus.BAD_REQUEST);
			}
		
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(yearTargetRepository.save(newyearTargetCalulation), HttpStatus.CREATED);
	}

}
