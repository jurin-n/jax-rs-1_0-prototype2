package com.jurin_n.domain.model.practice.plan;

import java.util.List;

public interface PracticePlanRepository {
	public void add(PracticePlan aPracticePlan);
	public void remove(PracticePlan aPracticePlan);
	public List<PracticePlan> getAll();
	public PracticePlanId nextIdentity();
	public PracticePlan getPracticePlanById(String id);
	public PracticePlan getPracticePlanById(PracticePlanId practicePlanId);
}
