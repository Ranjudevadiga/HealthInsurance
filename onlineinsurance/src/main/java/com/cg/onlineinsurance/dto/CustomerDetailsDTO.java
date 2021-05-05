package com.cg.onlineinsurance.dto;

public class CustomerDetailsDTO {
	
	private int customerDetailId; 
	private int customerId;
	private String isDiabetic;
	private String isSmoker;
	private String isAlcoholic;
	private double bodyMassIndex;
	private int age;
	private double salaryBracket;
	public int getCustomerDetailId() {
		return customerDetailId;
	}
	public void setCustomerDetailId(int customerDetailId) {
		this.customerDetailId = customerDetailId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getIsDiabetic() {
		return isDiabetic;
	}
	public void setIsDiabetic(String isDiabetic) {
		this.isDiabetic = isDiabetic;
	}
	public String getIsSmoker() {
		return isSmoker;
	}
	public void setIsSmoker(String isSmoker) {
		this.isSmoker = isSmoker;
	}
	public String getIsAlcoholic() {
		return isAlcoholic;
	}
	public void setIsAlcoholic(String isAlcoholic) {
		this.isAlcoholic = isAlcoholic;
	}
	public double getBodyMassIndex() {
		return bodyMassIndex;
	}
	public void setBodyMassIndex(double bodyMassIndex) {
		this.bodyMassIndex = bodyMassIndex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSalaryBracket() {
		return salaryBracket;
	}
	public void setSalaryBracket(double salaryBracket) {
		this.salaryBracket = salaryBracket;
	}
	public CustomerDetailsDTO() {
		super();
	}
	public CustomerDetailsDTO(int customerDetailId, int customerId, String isDiabetic, String isSmoker,
			String isAlcoholic, double bodyMassIndex, int age, double salaryBracket) {
		super();
		this.customerDetailId = customerDetailId;
		this.customerId = customerId;
		this.isDiabetic = isDiabetic;
		this.isSmoker = isSmoker;
		this.isAlcoholic = isAlcoholic;
		this.bodyMassIndex = bodyMassIndex;
		this.age = age;
		this.salaryBracket = salaryBracket;
	}
	
}
