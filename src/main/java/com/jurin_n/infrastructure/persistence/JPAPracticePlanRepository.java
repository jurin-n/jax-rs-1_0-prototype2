package com.jurin_n.infrastructure.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.jurin_n.domain.model.practice.menu.PracticeMenu;
import com.jurin_n.domain.model.practice.plan.PracticePlan;
import com.jurin_n.domain.model.practice.plan.PracticePlanId;
import com.jurin_n.domain.model.practice.plan.PracticePlanRepository;

@Stateless
public class JPAPracticePlanRepository implements PracticePlanRepository {
	@PersistenceContext
	private EntityManager em;

	public void setEntityManager(EntityManager em){
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

	@Override
	public List<PracticePlan> getAll() {
		TypedQuery<PracticePlan> query
		    = em.createNamedQuery("PracticePlan.FIND_ALL",PracticePlan.class);
		return query.getResultList();
	}

	@Override
	public PracticePlan getPracticePlanById(String id) {
		return em.find(PracticePlan.class,new PracticePlanId(id));
	}

	@Override
	public PracticePlan getPracticePlanById(PracticePlanId practicePlanId) {
		return em.find(PracticePlan.class,practicePlanId);
	}
}
