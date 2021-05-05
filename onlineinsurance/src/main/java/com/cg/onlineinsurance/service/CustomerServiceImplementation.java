package com.cg.onlineinsurance.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineinsurance.dto.CustomerDTO;
import com.cg.onlineinsurance.dto.CustomerDetailsDTO;
import com.cg.onlineinsurance.dto.PolicyDetailsDTO;
import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.CustomerDetails;
import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.entity.PolicyDetails;
import com.cg.onlineinsurance.exception.CustomerNotFoundException;
import com.cg.onlineinsurance.exception.DuplicateCustomerDetailException;
import com.cg.onlineinsurance.exception.DuplicateCustomerException;
import com.cg.onlineinsurance.exception.DuplicateCustomerPolicyException;
import com.cg.onlineinsurance.exception.PolicyActiveException;
import com.cg.onlineinsurance.exception.PolicyListEmptyException;
import com.cg.onlineinsurance.exception.PolicyNotFoundException;

import com.cg.onlineinsurance.repository.ICustomerDetailsRepository;
import com.cg.onlineinsurance.repository.ICustomerRepository;
import com.cg.onlineinsurance.repository.IPolicyDetailsRepository;
import com.cg.onlineinsurance.repository.IPolicyRepository;



@Service

public class CustomerServiceImplementation implements ICustomerService {

	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	ICustomerDetailsRepository customerDetailsRepository;
	
	@Autowired
	IPolicyRepository policyRepository;
	
	@Autowired
	IPolicyDetailsRepository policyDetailsRepository;
	
	@Autowired
	ICustomerService customerservice;

   // A provision for the customer to register and avail different policies
	@Override
	public Customer registerCustomer(Customer customer)  {
		return customerRepository.save(customer);	
	}

   // A method to add customer's personal  details
	@Override
	public String addCustomerDetails(CustomerDetailsDTO customerDetailsDTO){
		
			CustomerDetails customerDetails=new CustomerDetails();
			customerDetails.setCustomerDetailId(customerDetailsDTO.getCustomerDetailId());//
			customerDetails.setIsAlcoholic(customerDetailsDTO.getIsAlcoholic());
			customerDetails.setBodyMassIndex(customerDetailsDTO.getBodyMassIndex());
			customerDetails.setIsDiabetic(customerDetailsDTO.getIsDiabetic());
			customerDetails.setIsSmoker(customerDetailsDTO.getIsSmoker());
			customerDetails.setSalaryBracket(customerDetailsDTO.getSalaryBracket());
			customerDetails.setAge(customerDetailsDTO.getAge());
			Customer customer=customerRepository.getCustomerById(customerDetailsDTO.getCustomerId());
			customerDetails.setCustomer(customer);
			customerDetailsRepository.save(customerDetails);
		   return null;
	}
	
   // A list of available policies for the customer to choose from
	@Override
	public List<Policy> viewAllPolicies()  {
		return policyRepository.findAll();
	}

  // A provision which allows a customer to purchase a policy
	@Override
	public String buyPolicy(PolicyDetailsDTO policydetailsDto) {
		if(policydetailsDto!=null) {
			
			Policy policy=policyRepository.getPolicyById(policydetailsDto.getPolicyId());
			PolicyDetails policyDetails=new PolicyDetails();
			Customer customer=customerRepository.getCustomerById(policydetailsDto.getCustomerId());
			LocalDate todaysDate = LocalDate.now();
			int term=policy.getPolicyTerm();
			int code=policydetailsDto.getCustomerId();
			int age=0;
			String isDiabetic="";
			String isSmoker="";
			String isAlcoholic="";
			List<CustomerDetails> detailList=customerDetailsRepository.findAll();
			for(CustomerDetails details:detailList)
			{
				if(details.getCustomer().getCustomerId()==code)
				{
					age=details.getAge();
					isDiabetic=details.getIsDiabetic();
					isSmoker=details.getIsSmoker();
					isAlcoholic=details.getIsAlcoholic();
					break;
				}
			}
			policyDetails.setPolicyDetailsId(policydetailsDto.getPolicyDetailsId());
			policyDetails.setStartDate(todaysDate);
			policyDetails.setExpiryDate(todaysDate.plusYears(term));
			policyDetails.setPremiumAmounts(calculatePremium(age,isDiabetic,isSmoker,isAlcoholic,policy));
			policyDetails.setStatus(false);
			policyDetails.setPolicy(policy);
			policyDetails.setCustomer(customer);
			policyDetailsRepository.save(policyDetails);
		}
		return null;
	}

    // method which gives a customer a provision to remove one of his policies
	
	@Override
	public int removeCustomerPolicy(int customerId,int policyId) {
			int flag=0;
			PolicyDetails policyDetail=policyDetailsRepository.getPolicyDetail(customerId, policyId);
			if(policyDetail!=null) {
				
			
			int id=policyDetail.getPolicyDetailsId();
			policyDetailsRepository.deleteById(id);
			flag=1;
			return flag;
			}
			 return flag;
	}
	

	// renew a policy past its expiration date
	@Override
	public String renewPolicy(PolicyDetailsDTO policydetailsDTO){
		Policy policy=policyRepository.getPolicyById(policydetailsDTO.getPolicyId());
		int id =policydetailsDTO.getPolicyDetailsId();
		PolicyDetails policyDetails=policyDetailsRepository.findById(id).get();
		Customer customer=customerRepository.getCustomerById(policydetailsDTO.getCustomerId());
		LocalDate todaysDate = LocalDate.now();
		int code=policydetailsDTO.getCustomerId();
		int age=0;
		String isDiabetic="";
		String isSmoker="";
		String isAlcoholic="";
		List<CustomerDetails> customerDetailList=customerDetailsRepository.findAll();
		for(CustomerDetails details:customerDetailList)
		{
			if(details.getCustomer().getCustomerId()==code)
			{
				age=details.getAge();	
				isDiabetic=details.getIsDiabetic();
				isSmoker=details.getIsSmoker();
				isAlcoholic=details.getIsAlcoholic();
				break;
			}
		}
		int term=policy.getPolicyTerm();
		policyDetails.setPolicyDetailsId(policydetailsDTO.getPolicyDetailsId());
		policyDetails.setStartDate(todaysDate);
		policyDetails.setExpiryDate(todaysDate.plusYears(term));
		policyDetails.setStatus(true);
		policyDetails.setPremiumAmounts(calculatePremium(age,isDiabetic,isSmoker,isAlcoholic,policy));
		policyDetails.setCustomer(customer);
		policyDetails.setPolicy(policy);
		policyDetailsRepository.save(policyDetails);
		return null;
	}

   // A utility method used in addCustomerDetails method
	@Override
	public Customer getCustomerById(int id) {
		if(customerRepository.getCustomerById(id)==null)
		{
			throw new CustomerNotFoundException();
		}
		return customerRepository.getCustomerById(id);
	}
	
	// view a specific  policy out of list of policies
	@Override
	public Policy viewPolicyById(int id)  {	
		return policyRepository.getPolicyById(id);	
	}

	// details regarding purchases of policies by a particular customer
	@Override
	public List<PolicyDetails>  getpolicyDetailsById(int id) {
		return policyDetailsRepository.getPolicyDetailsById(id);
	}
	
	//A utility method used in buyPolicy
	@Override
	public double calculatePremium(int age,String isDiabetic,String isSmoker,String isAlcoholic,Policy policy)
	{	
		double amount=policy.getBaseAmount();
		if(age<=25 && age>=1) {
			if(isDiabetic.equals("yes") && isSmoker.equals("yes") && isAlcoholic.equals("yes")) {
				amount=amount*4;
			}
			else if(isDiabetic.equals("yes") || isSmoker.equals("yes") || isAlcoholic.equals("yes")) {
				amount=amount*3;	
			}
			else
			{
				amount=amount*2;
			}
		}
		else if(age>25 && age<=35)
		{
			if(isDiabetic.equals("yes") && isSmoker.equals("yes") && isAlcoholic.equals("yes")) {
				amount=amount*7;
			}
			else if(isDiabetic.equals("yes") || isSmoker.equals("yes") || isAlcoholic.equals("yes")) {
				amount=amount*6;	
			}
			else
			{
				amount=amount*5;
			}
		}
		else if(age>35 &&age<=45)
		{
			if(isDiabetic.equals("yes") && isSmoker.equals("yes") && isAlcoholic.equals("yes")) {
				amount=amount*10;
			}
			else if(isDiabetic.equals("yes") || isSmoker.equals("yes") || isAlcoholic.equals("yes")) {
				amount=amount*9;	
			}
			else
			{
				amount=amount*8;
			}
		}
		else if(age>45 &&age<100)
		{
			if(isDiabetic.equals("yes") && isSmoker.equals("yes") && isAlcoholic.equals("yes")) {
				amount=amount*13;
			}
			else if(isDiabetic.equals("yes") || isSmoker.equals("yes") || isAlcoholic.equals("yes")) {
				amount=amount*12;	
			}
			else
			{
				amount=amount*11;
			}
		}
		return amount;
	}

	@Override
	public int validate(String emailId, String password) {
		int flag=1;
		Customer login=customerRepository.validate(emailId,password);
		if(login==null) {
			flag=0;
			return flag;
		}
		return flag;
	}
}


	


	
	


