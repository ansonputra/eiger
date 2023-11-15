package com.poc.eiger.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class TotalOverviewLastYear implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String year;
	private BigDecimal totalSales;
	private float totalPercentage;
	private List<OverviewLastYear> overviewLastYears;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public BigDecimal getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}
	public float getTotalPercentage() {
		return totalPercentage;
	}
	public void setTotalPercentage(float totalPercentage) {
		this.totalPercentage = totalPercentage;
	}
	public List<OverviewLastYear> getOverviewLastYears() {
		return overviewLastYears;
	}
	public void setOverviewLastYears(List<OverviewLastYear> overviewLastYears) {
		this.overviewLastYears = overviewLastYears;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "TotalOverviewLastYear [year=" + year + ", totalSales=" + totalSales + ", totalPercentage="
				+ totalPercentage + ", overviewLastYears=" + overviewLastYears + "]";
	}

	
}
