package com.cg.onlineinsurance.dto;

import java.time.LocalDate;


public class PolicyDetailsDTO {

	
	private int policyDetailsId;
	
	private int  customerId;
	   
	private int policyId;

	private LocalDate startDate;
	
	private LocalDate expiryDate;
	
	private double premiumAmounts;
	
	private boolean status;

	public int getPolicyDetailsId() {
		return policyDetailsId;
	}

	public void setPolicyDetailsId(int policyDetailsId) {
		this.policyDetailsId = policyDetailsId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate todaysDate) {
		this.startDate = todaysDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate localDate) {
		this.expiryDate = localDate;
	}

	public double getPremiumAmounts() {
		return premiumAmounts;
	}

	public void setPremiumAmounts(double premiumAmounts) {
		this.premiumAmounts = premiumAmounts;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public PolicyDetailsDTO(int policyDetailsId, int customerId, int policyId, LocalDate startDate, LocalDate expiryDate,
			double premiumAmounts, boolean status) {
		super();
		this.policyDetailsId = policyDetailsId;
		this.customerId = customerId;
		this.policyId = policyId;
		this.startDate = startDate;
		this.expiryDate = expiryDate;
		this.premiumAmounts = premiumAmounts;
		this.status = status;
	}

	public PolicyDetailsDTO() {
		super();
	}
	
	
}
