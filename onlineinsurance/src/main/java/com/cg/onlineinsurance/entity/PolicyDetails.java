package com.cg.onlineinsurance.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "policy_details")
public class PolicyDetails   {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int policyDetailsId;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer  customer;
	   
	 
	@ManyToOne
	@JoinColumn(name="policyId")
	private Policy  policy;
	
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate expiryDate;
	
	@Column(name="premium_amount")
	private double premiumAmounts;
	
	@Column(name="status")
	private boolean status;

	public int getPolicyDetailsId() {
		return policyDetailsId;
	}

	public void setPolicyDetailsId(int policyDetailsId) {
		this.policyDetailsId = policyDetailsId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate localDate) {
		this.startDate = localDate;
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

	public PolicyDetails() {
		super();
	}

	public PolicyDetails(int policyDetailsId, Customer customer, Policy policy, LocalDate startDate, LocalDate expiryDate,
			double premiumAmounts, boolean status) {
		super();
		this.policyDetailsId = policyDetailsId;
		this.customer = customer;
		this.policy = policy;
		this.startDate = startDate;
		this.expiryDate = expiryDate;
		this.premiumAmounts = premiumAmounts;
		this.status = status;
	}


}
