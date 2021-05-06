package com.cg.onlineinsurance.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class CustomerDetailsDTO {
	
	private int customerDetailId; 
	private int customerId;
	@NotBlank(message="Is Diabetic field cannot be empty")
	@Pattern(regexp ="(yes)|(Yes)|(No)|(no)", message = "Please enter a yes or no")
	private String isDiabetic;
	@NotBlank(message="is smoker field cannot be empty")
	@Pattern(regexp ="(yes)|(Yes)|(No)|(no)", message = "Please enter a yes or no")
	private String isSmoker;
	@Pattern(regexp ="(yes)|(Yes)|(No)|(no)", message = "Please enter a yes or no")
	@NotBlank(message="Is alcoholic field cannot be empty")
	private String isAlcoholic;
	@Range(min = 1, message= "Body Mass Index cannot be null")
	private double bodyMassIndex;
	@Range(min = 1, message= "Age cannot be null")
	
	private int age;
	@Range(min = 1, message= "salary bracket cannot be null")
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
