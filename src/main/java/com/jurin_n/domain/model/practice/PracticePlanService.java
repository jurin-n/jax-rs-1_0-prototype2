package com.jurin_n.domain.model.practice;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PracticePlanService {
	
	@Inject
	PracticePlanRepository repo;

	public PracticePlanId addPracticePlan(
			 PracticeMenuId aPracticeMenuId
			,PracticeMemberId aMemberId
			,Date aStartDate
			,Date aEndDate
			){
		
		PracticePlan plan = new PracticePlan(
				 repo.nextIdentity() 
				,aPracticeMenuId
				,aMemberId
				,aStartDate
				,aEndDate
				);
		
		repo.add(plan);	
		return plan.getPracticePlanId();
	}

	public List<PracticePlan> getPracticePlanList() {
		return repo.getAll();
	}
}
