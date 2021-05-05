package com.cg.onlineinsurance.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.exception.CustomerListEmptyException;
import com.cg.onlineinsurance.exception.CustomerNotFoundException;
import com.cg.onlineinsurance.exception.DuplicatePolicyException;
import com.cg.onlineinsurance.exception.PolicyListEmptyException;
import com.cg.onlineinsurance.exception.PolicyNotFoundException;
import com.cg.onlineinsurance.repository.ICustomerRepository;
import com.cg.onlineinsurance.repository.IPolicyRepository;
import com.cg.onlineinsurance.service.IAdminService;

@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {

	@Autowired
	IAdminService adminService;
	
	@Autowired
	IPolicyRepository policyRepository;
	
	@Autowired
	ICustomerRepository customerRepository;

	@PostMapping("/addPolicy")
	public ResponseEntity<String> addPolicy(@Valid @RequestBody Policy policy) throws DuplicatePolicyException{
		if(policy!=null)
		{
			String policyName=policy.getPolicyName();
			for(Policy policyEntry:policyRepository.findAll())
			{
				if(policyEntry.getPolicyName().equals(policyName))
				{
					throw new DuplicatePolicyException();
				}
			}
		}
		adminService.addPolicy(policy);
		return new ResponseEntity<String>("Policy added", HttpStatus.OK);
	}

	@PutMapping("/updatePolicy")
	public ResponseEntity<String> updatePolicy(@Valid @RequestBody Policy policy) throws PolicyNotFoundException{
		if(!policyRepository.findById(policy.getPolicyId()).isPresent()) throw new PolicyNotFoundException();
		adminService.updatePolicy(policy);
		return new ResponseEntity<>("Policy Updated", HttpStatus.OK);
	}

	@GetMapping("/selectAll")
	public ResponseEntity<List<Policy>> displayPolicies() throws PolicyListEmptyException{
		if(policyRepository.findAll().isEmpty())throw new PolicyListEmptyException();
		List<Policy> policyList = adminService.viewAllPolicies();
		return new ResponseEntity<>(policyList, HttpStatus.OK);
	}

	@DeleteMapping("/deletePolicy/{id}")
	public ResponseEntity<String> deletePolicy(@PathVariable int id) throws PolicyNotFoundException{
		if(!policyRepository.findById(id).isPresent()) throw new PolicyNotFoundException();
		adminService.removePolicy(id);
		return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
	}

	@GetMapping("/getPolicyById/{code}")
	public ResponseEntity <Policy> getPolicyById(@PathVariable int code) throws PolicyNotFoundException{
		if(!policyRepository.findById(code).isPresent()) 
		{
			throw new PolicyNotFoundException();
		} 
		Policy policy=adminService.getPolicyById(code);
		return new ResponseEntity<>(policy, HttpStatus.OK);
	}
	
	@GetMapping("/viewAllCustomer")
	public ResponseEntity<List<Customer>> viewAllCustomer() throws CustomerListEmptyException {
		if(customerRepository.findAll().isEmpty())throw new CustomerListEmptyException();
		List<Customer> policyList = adminService.viewAllCustomer();
		return new ResponseEntity<>(policyList, HttpStatus.OK);
	}
	
	@GetMapping("/viewCustomerById/{code}")
	public ResponseEntity <Customer> getCustomerById(@PathVariable int code) throws CustomerNotFoundException{
		if(customerRepository.getCustomerById(code)==null)
	    {
	    	throw new CustomerNotFoundException();
	    }
		Customer policy=adminService.getCustomerById(code);
		return new ResponseEntity<>(policy, HttpStatus.OK);
	
	}
}
