package com.cg.onlineinsurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.onlineinsurance.entity.CustomerDetails;


@Repository
public interface ICustomerDetailsRepository extends JpaRepository<CustomerDetails, Integer> {
	
	@Query(value="from CustomerDetails customerdetail where customerdetail.customer.customerId=?1")
	public CustomerDetails getDetailsByCustId(int id);

}