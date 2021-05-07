package com.cg.onlineinsurance.service;

import java.util.List;
import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.Policy;

public interface IAdminService {


	String   addPolicy(Policy policy);
	List<Policy> removePolicy(int id);
	Policy updatePolicy(Policy policy);
	List<Policy> viewAllPolicies();  
	Policy getPolicyById(int id);			
	List<Customer> viewAllCustomer();
	Customer getCustomerById(int code);
	
}
