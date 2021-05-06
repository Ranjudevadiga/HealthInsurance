package com.cg.onlineinsurance.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="Customer")
public class Customer {
	
	@Id
	@GeneratedValue
	@Column(name="customer_id")
	int customerId;
	
	@Column(name="first_name")
	@NotBlank(message="First name cannot be empty")
	@Size(max=30,message="First name should not be more than 30 characters")
	private String firstName;
	
	@Column(name="last_name")
	@NotBlank(message="Last name cannot be empty")
	@Size(max=30,message="Last name should not be than 30 characters")
	private String lastName;
	
	@Column(name="email_id")
	@NotBlank(message="Email id cannot be empty")
	@Size(max=30,message="Email Id should not be more than 30 characters")
	@Pattern(regexp ="^[a-zA-Z1-9]+@[a-zA-Z]+.[a-zA-Z]+$", message = "Please enter a valid email id format")
	private String emailId;
	
	@Column(name="password")
    @Size(min=6,max=12,message="password size must lie within 6 and 12")
	
	@NotBlank(message="password cannot be empty")
	private String password;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<PolicyDetails> policyDetails;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public void setPolicyDetails(List<PolicyDetails> policyDetails) {
		this.policyDetails = policyDetails;
	}

	public Customer(int customerId, String firstName, String lastName, String emailId, String password) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;	
	}

	public Customer() {
		super();
	}
}
