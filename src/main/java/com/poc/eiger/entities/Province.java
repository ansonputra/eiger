package com.poc.eiger.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Province implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum Risk { LOW, MEDIUM, HIGH };

	private String name;
	private BigDecimal pYSales;
	private float percentageGrowth;
	private BigDecimal initialTarget;
	private Boolean hold;
	private Boolean override;
	private float percentageOverride;
	@Enumerated(EnumType.STRING)
	private Risk risk;
	private BigDecimal finalTarget;
	private List<Region> regions;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getpYSales() {
		return pYSales;
	}
	public void setpYSales(BigDecimal pYSales) {
		this.pYSales = pYSales;
	}
	public float getPercentageGrowth() {
		return percentageGrowth;
	}
	public void setPercentageGrowth(float percentageGrowth) {
		this.percentageGrowth = percentageGrowth;
	}
	public BigDecimal getInitialTarget() {
		return initialTarget;
	}
	public void setInitialTarget(BigDecimal initialTarget) {
		this.initialTarget = initialTarget;
	}
	public Boolean getHold() {
		return hold;
	}
	public void setHold(Boolean hold) {
		this.hold = hold;
	}
	public Boolean getOverride() {
		return override;
	}
	public void setOverride(Boolean override) {
		this.override = override;
	}
	public float getPercentageOverride() {
		return percentageOverride;
	}
	public void setPercentageOverride(float percentageOverride) {
		this.percentageOverride = percentageOverride;
	}
	public Risk getRisk() {
		return risk;
	}
	public void setRisk(Risk risk) {
		this.risk = risk;
	}
	public BigDecimal getFinalTarget() {
		return finalTarget;
	}
	public void setFinalTarget(BigDecimal finalTarget) {
		this.finalTarget = finalTarget;
	}
	public List<Region> getRegions() {
		return regions;
	}
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Province [name=" + name + ", pYSales=" + pYSales + ", percentageGrowth=" + percentageGrowth
				+ ", initialTarget=" + initialTarget + ", hold=" + hold + ", override=" + override
				+ ", percentageOverride=" + percentageOverride + ", risk=" + risk + ", finalTarget=" + finalTarget
				+ ", regions=" + regions + "]";
	}
	
	
	

}
