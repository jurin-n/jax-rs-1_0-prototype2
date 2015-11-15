package com.jurin_n.domain.model.practice;

import javax.persistence.EntityManager;

public interface PracticePlanRepository {
	public void setEntityManager(EntityManager em);
	public void add(PracticePlan aPracticePlan);
	public void remove(PracticePlan aPracticePlan);
	public PracticePlanId nextIdentity();
}
