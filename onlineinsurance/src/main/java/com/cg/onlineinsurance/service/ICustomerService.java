package com.cg.onlineinsurance.service;

import java.util.List;

import com.cg.onlineinsurance.dto.CustomerDTO;
import com.cg.onlineinsurance.dto.CustomerDetailsDTO;
import com.cg.onlineinsurance.dto.PolicyDetailsDTO;
import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.entity.PolicyDetails;
import com.cg.onlineinsurance.exception.CustomerNotFoundException;
import com.cg.onlineinsurance.exception.DuplicateCustomerDetailException;
import com.cg.onlineinsurance.exception.DuplicateCustomerException;
import com.cg.onlineinsurance.exception.DuplicateCustomerPolicyException;
import com.cg.onlineinsurance.exception.PolicyActiveException;
import com.cg.onlineinsurance.exception.PolicyListEmptyException;
import com.cg.onlineinsurance.exception.PolicyNotFoundException;


public interface  ICustomerService {
	
	Customer registerCustomer(Customer customer);
	String  addCustomerDetails(CustomerDetailsDTO customerDetails);
	List<Policy> viewAllPolicies();
	String renewPolicy(PolicyDetailsDTO policydetailsDTO );
	String buyPolicy(PolicyDetailsDTO policydetailsDto); 
    Customer getCustomerById(int id);
    Policy viewPolicyById(int id);
    List<PolicyDetails> getpolicyDetailsById(int id);
	int validate(String emailId, String password);
	double calculatePremium(int age, String isDiabetic, String isSmoker, String isAlcoholic, Policy policy);
	int removeCustomerPolicy(int policyId, int customerId) throws PolicyNotFoundException;
}