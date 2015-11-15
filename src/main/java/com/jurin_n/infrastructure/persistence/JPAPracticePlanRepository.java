package com.jurin_n.infrastructure.persistence;

import javax.persistence.EntityManager;

import com.jurin_n.domain.model.practice.PracticeMenuId;
import com.jurin_n.domain.model.practice.PracticePlan;
import com.jurin_n.domain.model.practice.PracticePlanId;
import com.jurin_n.domain.model.practice.PracticePlanRepository;

public class JPAPracticePlanRepository implements PracticePlanRepository {

	private EntityManager em;

	@Override
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

	@Override
	public void add(PracticePlan aPracticePlan) {
		em.persist(aPracticePlan);
	}

	@Override
	public void remove(PracticePlan aPracticePlan) {
		// TODO Auto-generated method stub

	}

	@Override
	public PracticePlanId nextIdentity() {
		return new PracticePlanId(
				java.util.UUID.randomUUID().toString().toUpperCase()
				);
	}

}
