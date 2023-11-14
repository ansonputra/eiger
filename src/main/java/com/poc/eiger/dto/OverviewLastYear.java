package com.poc.eiger.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class OverviewLastYear implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String name;
    private BigDecimal pYSales;
    private float percentagePY;
    
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
	public float getPercentagePY() {
		return percentagePY;
	}
	public void setPercentagePY(float percentagePY) {
		this.percentagePY = percentagePY;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "OverviewLastYear [name=" + name + ", pYSales=" + pYSales + ", percentagePY=" + percentagePY + "]";
	} 
    
    
    
}
