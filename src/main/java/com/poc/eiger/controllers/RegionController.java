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

import com.poc.eiger.entities.Province;
import com.poc.eiger.entities.Region;
import com.poc.eiger.repositories.RegionRepository;

@RestController
@RequestMapping("/api")
public class RegionController {

	@Autowired
	private RegionRepository regionRepository;

	@GetMapping("/regions")
	public ResponseEntity<List<Region>> findAll(
			@RequestParam(name = "name", required = false, defaultValue = "") String name) {
		try {
			List<Region> regions;
			if (StringUtils.hasText(name)) {
				regions = regionRepository.findByNameContaining(name);
			} else {
				regions = regionRepository.findAll();
			}

			if (regions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(regions, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/all/regions")
	public ResponseEntity<Province> findAllRg(
			@RequestParam(name = "name", required = false, defaultValue = "") String name) {
		try {
			Province provObj = new Province();
			List<Region> regions;
			if (StringUtils.hasText(name)) {
				// regionObj = businessUnitRepository.findByNameContaining(name);
			} else {
				// businessUnitsObj = businessUnitRepository.findAll();
				regions = regionRepository.findAll();
				provObj.setName("jawa");
				List<Region> regionList = new ArrayList<>();
				int sumRegion = regions.size();

				for (int indexhl = 0; indexhl < sumRegion; indexhl++) {
					Region regiondto = new Region();
					regiondto.setId(regions.get(indexhl).getId());
					regiondto.setName(regions.get(indexhl).getName());
					regiondto.setpYSales(regions.get(indexhl).getpYSales());
					regiondto.setPercentageGrowth(regions.get(indexhl).getPercentageGrowth());
					regiondto.setInitialTarget(regions.get(indexhl).getInitialTarget());
					regiondto.setHold(regions.get(indexhl).getHold());
					regiondto.setOverride(regions.get(indexhl).getOverride());
					regiondto.setPercentageOverride(regions.get(indexhl).getPercentageOverride());
					regiondto.setRisk(regions.get(indexhl).getRisk());
					regiondto.setFinalTarget(regions.get(indexhl).getFinalTarget());
					regionList.add(regiondto);
				}

				provObj.setRegions(regionList);
			}

			if (provObj.equals(null)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(provObj, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/regions/{id}")
	public ResponseEntity<Region> findById(@PathVariable("id") String id) {

		Optional<Region> regionData = regionRepository.findById(id);

		if (regionData.isPresent()) {
			return new ResponseEntity<>(regionData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/regions")
	public ResponseEntity<Region> create(@RequestBody Region region) {

		try {
			Region newRegion = new Region();
			newRegion.setId(UUID.randomUUID().toString());
			newRegion.setName(region.getName());
			newRegion.setpYSales(region.getpYSales());
			newRegion.setPercentageGrowth(region.getPercentageGrowth());
			newRegion.setInitialTarget(region.getInitialTarget());
			newRegion.setHold(region.getHold());
			newRegion.setOverride(region.getOverride());
			newRegion.setPercentageOverride(region.getPercentageOverride());
			newRegion.setRisk(region.getRisk());
			newRegion.setFinalTarget(region.getFinalTarget());
			return new ResponseEntity<>(regionRepository.save(newRegion), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/regions/{id}")
	public ResponseEntity<Region> update(@PathVariable("id") String id, @RequestBody Region region) {

		Optional<Region> regionData = regionRepository.findById(id);
		Boolean prevHold = regionData.get().getHold();
		Boolean prevOverride = regionData.get().getHold();
		Float prevOverrideValue = regionData.get().getPercentageOverride();
		BigDecimal prevInitialTarget = regionData.get().getFinalTarget();

		if (regionData.isPresent()) {
			Region updatedRegion = regionData.get();
			updatedRegion.setName(regionData.get().getName());
			updatedRegion.setpYSales(regionData.get().getpYSales());
			updatedRegion.setPercentageGrowth(regionData.get().getPercentageGrowth());
			updatedRegion.setInitialTarget(regionData.get().getInitialTarget());
			updatedRegion.setRisk(regionData.get().getRisk());
			if (region.getHold() == null) {
				updatedRegion.setHold(prevHold);
			} else {
				updatedRegion.setHold(region.getHold());
			}

			if (region.getOverride() == null) {
				updatedRegion.setOverride(prevOverride);
			} else {
				updatedRegion.setOverride(region.getOverride());
			}

			if (region.getPercentageOverride() == 0) {
				updatedRegion.setPercentageOverride(prevOverrideValue);
			} else {
				updatedRegion.setPercentageOverride(region.getPercentageOverride());
			}

			if (region.getHold() == true || region.getOverride() == true) {
				updatedRegion.setFinalTarget(prevInitialTarget);
			} else {
				updatedRegion.setFinalTarget(region.getFinalTarget());
			}

			return new ResponseEntity<>(regionRepository.save(updatedRegion), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/regions/all/{id}")
	public ResponseEntity<Region> updateall(@PathVariable("id") String id, @RequestBody Region region) {

		Optional<Region> regionData = regionRepository.findById(id);

		if (regionData.isPresent()) {
			Region updatedRegion = regionData.get();
			updatedRegion.setName(region.getName());
			updatedRegion.setpYSales(region.getpYSales());
			updatedRegion.setPercentageGrowth(region.getPercentageGrowth());
			updatedRegion.setInitialTarget(region.getInitialTarget());
			updatedRegion.setHold(region.getHold());
			updatedRegion.setOverride(region.getOverride());
			updatedRegion.setPercentageOverride(region.getPercentageGrowth());
			updatedRegion.setRisk(region.getRisk());
			updatedRegion.setFinalTarget(region.getFinalTarget());

			return new ResponseEntity<>(regionRepository.save(updatedRegion), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/regions/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
		try {
			regionRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/regions")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			regionRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
