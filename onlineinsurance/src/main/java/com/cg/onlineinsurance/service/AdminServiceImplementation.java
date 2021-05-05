package com.cg.onlineinsurance.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.exception.CustomerListEmptyException;
import com.cg.onlineinsurance.exception.CustomerNotFoundException;
import com.cg.onlineinsurance.exception.DuplicatePolicyException;
import com.cg.onlineinsurance.exception.PolicyListEmptyException;
import com.cg.onlineinsurance.exception.PolicyNotFoundException;
import com.cg.onlineinsurance.repository.ICustomerDetailsRepository;
import com.cg.onlineinsurance.repository.ICustomerRepository;
import com.cg.onlineinsurance.repository.IPolicyRepository;

@Service
public class AdminServiceImplementation implements IAdminService {

	@Autowired
	IPolicyRepository policyRepository;
	
	@Autowired
	ICustomerDetailsRepository customerDetailsRepository;
	
	
    @Autowired
    ICustomerRepository customerRepository;
	
    // a method for admin to add a new policy 
	@Override
	public String  addPolicy(Policy policy) {
		policyRepository.save(policy);
		return null;
	}

    // provision for the admin to modify an existing policy
	@Override
	public Policy updatePolicy(Policy policy) {
		return policyRepository.save(policy);
	}
	
    // provision for the admin to view  all the policies
	@Override
	public List<Policy> viewAllPolicies(){
		return policyRepository.findAll();
	}
	
	// provision for admin to delete an existing policy
	@Override
	public List<Policy> removePolicy(int id) {
		policyRepository.deleteById(id);
		return viewAllPolicies();
	}

   // provision for admin to view a specific policy
	@Override
	public Policy getPolicyById(int id){
	    return policyRepository.getPolicyById(id);
	}
	
	// provision for admin to view the customers (but not modify)
	@Override
	public List<Customer> viewAllCustomer() {
		return customerRepository.findAll();	
	}
	
	// provision for admin to view a specific customer
	@Override
	public Customer getCustomerById(int id) {
		return customerRepository.getCustomerById(id);
	}
}
