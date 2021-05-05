package com.cg.onlineinsurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.entity.PolicyDetails;


@Repository
public interface IPolicyDetailsRepository extends JpaRepository<PolicyDetails, Integer> {
   
	@Query(value = "from Customer cust where cust.customerId=?1")
	public Customer getCustomerById(int id);

	@Query(value="from Policy pol where pol.policyId=?1")
	public Policy getPolicyById(int id);
	
	@Query(value="from PolicyDetails policyDetails where policyDetails.customer.customerId=?1")
	public List<PolicyDetails> getPolicyDetailsById(int id);

	@Query(value="from PolicyDetails policyDetails where policyDetails.customer.customerId=?1 and policyDetails.policy.policyId=?2")
	public PolicyDetails getpolicydetail(int customerId,int policyId);
}