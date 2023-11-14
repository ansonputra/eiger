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
public class Channel implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private float percentagePY; 
    private BigDecimal pYSales;
    private BigDecimal fcstSales;
    private Boolean hold;
    private Boolean override;
    private float overridePercentage;
    private BigDecimal overrideValue;
    private BigDecimal finalTarget;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPercentagePY() {
		return percentagePY;
	}
	public void setPercentagePY(float percentagePY) {
		this.percentagePY = percentagePY;
	}
	public BigDecimal getpYSales() {
		return pYSales;
	}
	public void setpYSales(BigDecimal pYSales) {
		this.pYSales = pYSales;
	}
	public BigDecimal getFcstSales() {
		return fcstSales;
	}
	public void setFcstSales(BigDecimal fcstSales) {
		this.fcstSales = fcstSales;
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
	public float getOverridePercentage() {
		return overridePercentage;
	}
	public void setOverridePercentage(float overridePercentage) {
		this.overridePercentage = overridePercentage;
	}
	public BigDecimal getOverrideValue() {
		return overrideValue;
	}
	public void setOverrideValue(BigDecimal overrideValue) {
		this.overrideValue = overrideValue;
	}
	public BigDecimal getFinalTarget() {
		return finalTarget;
	}
	public void setFinalTarget(BigDecimal finalTarget) {
		this.finalTarget = finalTarget;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", percentagePY=" + percentagePY + ", pYSales=" + pYSales
				+ ", fcstSales=" + fcstSales + ", hold=" + hold + ", override=" + override + ", overridePercentage="
				+ overridePercentage + ", overrideValue=" + overrideValue + ", finalTarget=" + finalTarget + "]";
	}
	
	

}
