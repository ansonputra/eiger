package com.poc.eiger.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.poc.eiger.entities.Overview;

public class TotalOverview implements Serializable {

	private static final long serialVersionUID = 1L;

	private BigDecimal grandTotal;
	private List<Overview> overviews;
	
	public BigDecimal getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
	public List<Overview> getOverviews() {
		return overviews;
	}
	public void setOverviews(List<Overview> overviews) {
		this.overviews = overviews;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "TotalOverview [grandTotal=" + grandTotal + ", overviews=" + overviews + "]";
	}
	
	

}
