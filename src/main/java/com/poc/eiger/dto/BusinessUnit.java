package com.poc.eiger.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.poc.eiger.entities.Channel;


public class BusinessUnit implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String name;
    private float percentagePY; 
    private BigDecimal pYSales;
    private BigDecimal fcstSales;
    private Boolean hold;
    private Boolean override;
    private BigDecimal overrideValue;
    private BigDecimal finalTarget;
    private List<Channel> channels;
    
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
	public List<Channel> getChannels() {
		return channels;
	}
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BusinessUnit [name=" + name + ", percentagePY=" + percentagePY + ", pYSales=" + pYSales + ", fcstSales="
				+ fcstSales + ", hold=" + hold + ", override=" + override + ", overrideValue=" + overrideValue
				+ ", finalTarget=" + finalTarget + ", channels=" + channels + "]";
	}
      

}
