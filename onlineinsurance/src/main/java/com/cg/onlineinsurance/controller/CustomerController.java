package com.cg.onlineinsurance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineinsurance.dto.CustomerDetailsDTO;
import com.cg.onlineinsurance.dto.PolicyDetailsDTO;
import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.CustomerDetails;
import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.entity.PolicyDetails;
import com.cg.onlineinsurance.exception.CustomerDetailsEmptyException;
import com.cg.onlineinsurance.exception.CustomerNotFoundException;
import com.cg.onlineinsurance.exception.CustomerPolicyListEmptyException;
import com.cg.onlineinsurance.exception.DuplicateCustomerDetailException;
import com.cg.onlineinsurance.exception.DuplicateCustomerException;
import com.cg.onlineinsurance.exception.DuplicateCustomerPolicyException;
import com.cg.onlineinsurance.exception.InvalidEmailIdException;
import com.cg.onlineinsurance.exception.InvalidPasswordException;
import com.cg.onlineinsurance.exception.InvalidUserException;
import com.cg.onlineinsurance.exception.PolicyActiveException;
import com.cg.onlineinsurance.exception.PolicyListEmptyException;
import com.cg.onlineinsurance.exception.PolicyNotFoundException;
import com.cg.onlineinsurance.repository.ICustomerDetailsRepository;
import com.cg.onlineinsurance.repository.ICustomerRepository;
import com.cg.onlineinsurance.repository.IPolicyDetailsRepository;
import com.cg.onlineinsurance.repository.IPolicyRepository;
import com.cg.onlineinsurance.service.ICustomerService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/customer")
//@Validated
public class CustomerController {

	@Autowired 
	ICustomerService customerService;
	
	@Autowired
	IPolicyDetailsRepository policyDetailsRepository;
	
	@Autowired 
	ICustomerRepository customerRepository;
	
	@Autowired 
	ICustomerDetailsRepository customerDetailsRepository;
	
	@Autowired
	IPolicyRepository policyRepository;
	
	@Autowired
	IPolicyDetailsRepository policyDetailRepository;
	
	@PostMapping("/registerCustomer")
	public ResponseEntity<Integer> registerCustomer(@RequestBody Customer customer)throws DuplicateCustomerException
	{
		for(Customer customerdetail:customerRepository.findAll())
		{
			if(customerdetail.getEmailId().equals(customer.getEmailId()))
			{
				throw new DuplicateCustomerException();
			}
		}
	
		Customer cust=customerService.registerCustomer(customer);
		return new ResponseEntity<>(cust.getCustomerId(), HttpStatus.OK);
	}
	
	@PostMapping("/addCustomerDetail")
	public ResponseEntity<String> addCustomerDetail(@RequestBody CustomerDetailsDTO customerDetailsDTO)throws CustomerNotFoundException,DuplicateCustomerDetailException
	{
		/*if(!customerRepository.existsById(customerDetailsDTO.getCustomerId()))
		{
			throw new CustomerNotFoundException();
		}*/
		for(CustomerDetails customerDetail: customerDetailsRepository.findAll())
		{
			if(customerDetail.getCustomer().getCustomerId()==customerDetailsDTO.getCustomerId())
			{
				throw new DuplicateCustomerDetailException();
			}
		}
		customerService.addCustomerDetails(customerDetailsDTO);
		return new ResponseEntity<>("Successfully added customer details", HttpStatus.OK);
	}
	
	
	@GetMapping("/viewAllPolicies")
	public ResponseEntity<List<Policy>> viewAll() throws PolicyListEmptyException
	{   
		if(policyRepository.findAll().isEmpty())throw new PolicyListEmptyException();
		List<Policy> policyList= customerService.viewAllPolicies();
	    return new ResponseEntity<>(policyList,HttpStatus.OK) ;   
	}

	@PutMapping("/renewPolicy")
	public ResponseEntity<PolicyDetailsDTO> renewPolicy(@RequestBody PolicyDetailsDTO policydetailsDTO)throws PolicyActiveException
	{
		
		int id =policydetailsDTO.getPolicyDetailsId();
		int policyId=policydetailsDTO.getPolicyId();
		int customerId=policydetailsDTO.getCustomerId();
		PolicyDetails policyDetails=policyDetailsRepository.findById(id).get();	
		if(policyDetailRepository.getPolicyById(policyId)==null)
		{
			throw new PolicyNotFoundException();
		}
		if(policyDetailRepository.getCustomerById(customerId)==null)
		{
			throw new CustomerNotFoundException();
		}
		//if(policyDetails.isStatus()) {
		//	throw new PolicyActiveException();
		//}
		customerService.renewPolicy(policydetailsDTO);
		return new ResponseEntity<>(policydetailsDTO,HttpStatus.OK) ;
	}
	
	@PostMapping("/buyPolicy")
	public ResponseEntity<PolicyDetailsDTO> buyPolicy(@RequestBody PolicyDetailsDTO policyDetailsDto,Errors error )throws CustomerDetailsEmptyException,DuplicateCustomerPolicyException,CustomerNotFoundException
	{   
		int custId=policyDetailsDto.getCustomerId();
		 int policyId=policyDetailsDto.getPolicyId();
		 CustomerDetails customerDetail=customerDetailsRepository.getDetailsByCustId(custId);
		 List<PolicyDetails> policydetailList=policyDetailsRepository.findAll(); 
		 
		 for(PolicyDetails policydetail:policydetailList)
		 {
			 if(policydetail.getCustomer().getCustomerId()==custId && policydetail.getPolicy().getPolicyId()==policyId)
	    	{
	    		throw new DuplicateCustomerPolicyException();
	    	}
		 }
		 if( customerRepository.getCustomerById(custId)==null)
		 {
	    		throw new CustomerNotFoundException();
		 }
		 if(customerDetail==null)
		 {
			 throw new CustomerDetailsEmptyException();
		 }
		 if( policyRepository.getPolicyById(policyId)==null)
		 {
	    		throw new PolicyNotFoundException();
		 }	
		customerService.buyPolicy(policyDetailsDto);
	    return new ResponseEntity<>(policyDetailsDto,HttpStatus.OK) ;
	}
	
	@DeleteMapping("/removePolicyDetails")
	public ResponseEntity<String> removePolicyDetails(@RequestBody PolicyDetailsDTO policydetailsDTO)throws PolicyNotFoundException
	{	
	int flag=customerService.removeCustomerPolicy(policydetailsDTO.getCustomerId(),policydetailsDTO.getPolicyId());
		if(flag==0) {
			 throw new PolicyNotFoundException();	
		}
		return new ResponseEntity<>("policy successfully dropped",HttpStatus.OK) ;
	}
	
	
	@GetMapping("/getPolicyById/{code}")
	public ResponseEntity<Policy> viewCustomerPolicyById(@PathVariable int code)throws PolicyNotFoundException {
		if(!policyRepository.findById(code).isPresent())
		{
			throw new PolicyNotFoundException();
		}
		Policy policy=customerService.viewPolicyById(code);
		return new ResponseEntity<>(policy, HttpStatus.OK);
	}
	
	@GetMapping("/getAllPolicydetails/{id}")
	public ResponseEntity<List<PolicyDetails>> getid(@PathVariable int id)throws CustomerPolicyListEmptyException{
		List<PolicyDetails> policyList= customerService.getpolicyDetailsById(id);
		if(customerRepository.getCustomerById(id)==null) throw new CustomerNotFoundException();
	    if(policyList.size()<=0) throw new CustomerPolicyListEmptyException();
	     
	    return new ResponseEntity<>(policyList,HttpStatus.OK);
	}
	
	@PostMapping("/validate")
	public ResponseEntity<Customer> validate(@RequestBody Customer login)throws InvalidUserException
	{   
		/*if(login.getEmailId()==null && login.getPassword()==null) 
	   { 
		return new ResponseEntity<>("EmailId cannot be empty\nPassword cannot be empty",HttpStatus.OK);
       }  
		if(login.getEmailId()==null) 
		{ 
			return new ResponseEntity<>("EmailId cannot be empty",HttpStatus.OK);
        }
        if(login.getPassword()==null) 
        {
        	return new ResponseEntity<>("Password cannot be empty",HttpStatus.OK);
        }
		if(customerRepository.validate(login.getEmailId(),login.getPassword())==null && customerRepository.getCustomerByEmailId(login.getEmailId())==null)
		{
			throw new InvalidUserException();
		}
		if(customerRepository.getCustomerByEmailId(login.getEmailId())==null)
		{
			throw new InvalidEmailIdException();
		}*/
	   
		Customer customer=customerService.validate(login.getEmailId(),login.getPassword());
		/*if(value==0) {
			throw new InvalidPasswordException();
			xwjxb
		}*/
		System.out.println("hello");
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
}