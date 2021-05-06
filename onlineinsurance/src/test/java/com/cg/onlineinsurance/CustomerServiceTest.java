package com.cg.onlineinsurance;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.repository.ICustomerDetailsRepository;
import com.cg.onlineinsurance.repository.ICustomerRepository;
import com.cg.onlineinsurance.repository.IPolicyDetailsRepository;
import com.cg.onlineinsurance.repository.IPolicyRepository;
import com.cg.onlineinsurance.service.CustomerServiceImplementation;

@RunWith(MockitoJUnitRunner.class)
class CustomerServiceTest {

	
	@InjectMocks
	CustomerServiceImplementation customerService;
	@Mock
	IPolicyDetailsRepository policyDetailsRepository;
	
	@Mock
	ICustomerRepository customerRepository;
	@Mock
	IPolicyRepository policyRepository;
	@Mock
	ICustomerDetailsRepository customerDetailsRepository;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	 void init() {
		System.out.println("* before method **");
		MockitoAnnotations.initMocks(this); 
	}

	@Test
    void testviewAllPolicies() {
		List<Policy> policyList = new ArrayList<Policy>();
		policyList.add(new Policy(1,"Policy-type8080",4000,100000,25,3));
		policyList.add(new Policy(2,"Policy-type0000",3000,200000,20,2));
		policyList.add(new Policy(3,"Policy-type1245",9000,400000,45,1));
		Mockito.when(policyRepository.findAll()).thenReturn(policyList);
		List<Policy> list =customerService.viewAllPolicies();
		Assertions.assertEquals(3, list.size()); 
		Mockito.verify(policyRepository,Mockito.times(1)).findAll();
	}

	@Test
	void testregisterCustomer()
	{
		Customer customer=new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("Disha");
		customer.setLastName("B");
		customer.setEmailId("dishaBhat@gmail.com");
		customer.setPassword("abcd");
		customerService.registerCustomer(customer);
		Assert.assertNotNull(customer.getCustomerId()); 
		Mockito.verify(customerRepository, Mockito.times(1)).save(customer);
	
	}
	

	
}