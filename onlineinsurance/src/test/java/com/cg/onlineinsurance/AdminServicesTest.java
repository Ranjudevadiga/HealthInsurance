package com.cg.onlineinsurance;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.exception.CustomerNotFoundException;
import com.cg.onlineinsurance.exception.PolicyNotFoundException;
import com.cg.onlineinsurance.repository.ICustomerRepository;
import com.cg.onlineinsurance.repository.IPolicyRepository;
import com.cg.onlineinsurance.service.AdminServiceImplementation;


@RunWith(MockitoJUnitRunner.class)
public class AdminServicesTest {

	@InjectMocks
	AdminServiceImplementation adminService;
	
	
	@Mock
	ICustomerRepository customerRepository;
	
	@Mock
	IPolicyRepository policyRepository;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	 void init() {
		System.out.println("* before method **");
		MockitoAnnotations.initMocks(this); 
	}
	
	@Test
	public void  testAddPolicy()  //done
	{
		Policy policy=new Policy();
		policy.setPolicyId(1);
		policy.setPolicyName("policy-sample");
		policy.setPolicyTerm(4);
		policy.setAgeGroup(60);
		policy.setPolicyCover(100000);
		policy.setBaseAmount(1000);
		
		adminService.addPolicy(policy);
		Assert.assertNotNull(policy.getPolicyId());
		Mockito.verify(policyRepository,Mockito.times(1)).save(policy);
	}
	
	@Test//(expected = PolicyNotFoundException.class)
	public void testRemovePolicy()throws PolicyNotFoundException {
	
		Policy policy=new Policy();
		adminService.removePolicy(2);
		Assertions.assertNotNull(policy.getPolicyId());
		Mockito.verify(policyRepository, Mockito.times(1)).deleteById(2);
		
		
	}
	
	
	
	@Test
	public void testViewAllPolicies() {       
		List<Policy>  policyList=new ArrayList<Policy>();
		policyList.add(new Policy(1,"Policy-type0000", 30, 2,3000,200000));
		policyList.add(new Policy(2,"Policy-type0001", 40, 3,4000,500000));
		policyList.add(new Policy(3,"Policy-type0002", 50, 4,5000,300000));
		
		Mockito.when(policyRepository.findAll()).thenReturn(policyList);
		List<Policy> list=adminService.viewAllPolicies();
		assertEquals(3,list.size());
		Mockito.verify(policyRepository,Mockito.times(1)).findAll();
		
	}
	
	
	@Test
	public void testviewAllCustomer()
	{
		List<Customer> customerList=new ArrayList<Customer>();
		customerList.add(new Customer(1,"ranjith","kumar","ranjith@123","r123","admin"));
		customerList.add(new Customer(2,"rao ajith","kumar","rao@123","rao123","user"));
		
		Mockito.when(customerRepository.findAll()).thenReturn(customerList);
		List<Customer> cust=adminService.viewAllCustomer();
		assertEquals(2,cust.size());
		Mockito.verify(customerRepository,Mockito.times(1)).findAll();
	}

	@Test//(expected = CustomerNotFoundException.class)
	public void testGetCustomerById()throws CustomerNotFoundException {
		
		Mockito.when(customerRepository.getCustomerById(1)).thenReturn(new Customer(1,"pooja","naik","tina@gmail.com","pmdhfe","admin"));
		Customer cus=adminService.getCustomerById(1);
		System.out.println(cus.getLastName());
		assertEquals("tina@gmail.com",cus.getEmailId());
		assertEquals("pooja",cus.getFirstName());
		assertEquals("naik",cus.getLastName());
		
		assertEquals("pmdhfe",cus.getPassword());
		assertEquals("admin",cus.getRole());
		Mockito.verify(customerRepository, Mockito.times(1)).getCustomerById(1);
		
	}
	
	@Test//(expected = PolicyNotFoundException.class)
	public void testGetPolicyById() {
		Mockito.when(policyRepository.getPolicyById(1)).thenReturn(new Policy(1,"Policy-type0000", 30, 2,3000,200000));
		Policy pol=adminService.getPolicyById(1);
		assertEquals("Policy-type0000",pol.getPolicyName());
		assertEquals(30,pol.getAgeGroup());
		assertEquals(2,pol.getPolicyTerm());
		assertEquals(3000,pol.getBaseAmount(),0.001);
		assertEquals(200000,pol.getPolicyCover(),0.001);
		Mockito.verify(policyRepository, Mockito.times(1)).getPolicyById(1);
	    }
	
}


