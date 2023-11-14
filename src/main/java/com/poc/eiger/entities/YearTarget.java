package com.poc.eiger.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class YearTarget implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    private String id;
	private float inflation;
	private float inflationMultiplier;
	private float targetGrowthPercentage;
	private BigDecimal targetGrowth;
	private BigDecimal finalTargetGrowth;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getInflation() {
		return inflation;
	}
	public void setInflation(float inflation) {
		this.inflation = inflation;
	}
	public float getInflationMultiplier() {
		return inflationMultiplier;
	}
	public void setInflationMultiplier(float inflationMultiplier) {
		this.inflationMultiplier = inflationMultiplier;
	}
	public float getTargetGrowthPercentage() {
		return targetGrowthPercentage;
	}
	public void setTargetGrowthPercentage(float targetGrowthPercentage) {
		this.targetGrowthPercentage = targetGrowthPercentage;
	}
	public BigDecimal getTargetGrowth() {
		return targetGrowth;
	}
	public void setTargetGrowth(BigDecimal targetGrowth) {
		this.targetGrowth = targetGrowth;
	}
	public BigDecimal getFinalTargetGrowth() {
		return finalTargetGrowth;
	}
	public void setFinalTargetGrowth(BigDecimal finalTargetGrowth) {
		this.finalTargetGrowth = finalTargetGrowth;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
