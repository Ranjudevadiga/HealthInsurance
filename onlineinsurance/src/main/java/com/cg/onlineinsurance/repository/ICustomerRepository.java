package com.cg.onlineinsurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.onlineinsurance.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
   
	@Query(value = "from Customer cust where cust.customerId=?1")
	public Customer getCustomerById(int id);
	
	@Query(value="from Customer login where login.emailId=?1 and login.password=?2")
	public Customer validate(String emailId,String password);

	@Query(value = "from Customer cust where cust.emailId=?1")
	public Customer getCustomerByEmailId(String emailId);

	
}