package com.cg.onlineinsurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cg.onlineinsurance.entity.Policy;



@Repository
public interface IPolicyRepository extends JpaRepository<Policy, Integer> {
   
	@Query(value="from Policy pol where pol.policyId=?1")
	public Policy getPolicyById(int id);
}