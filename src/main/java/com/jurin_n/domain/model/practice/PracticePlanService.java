package com.jurin_n.domain.model.practice;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PracticePlanService {
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	PracticePlanRepository repo;
	
	public void setEntityManager(EntityManager em) {
		if(this.em != null){
			Class<?> c = this.getClass();
			throw new IllegalStateException(
					"EntityManager instance 'em' is not null."
				  + "This Method "+ c.getName() + ".setEntityManager"
				  +" is for Using Unit Test.");
		}
		this.em = em;
	}
	
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
		
		repo.setEntityManager(em);
		repo.add(plan);
		
		return plan.getPracticePlanId();
	}

	public List<PracticePlan> getPracticePlanList() {
		repo.setEntityManager(em);
		return repo.getAll();
	}
}
