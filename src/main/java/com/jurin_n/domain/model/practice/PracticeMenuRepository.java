package com.jurin_n.domain.model.practice;

import javax.persistence.EntityManager;

public interface PracticeMenuRepository {
	public void setEntityManager(EntityManager em);
	public void add(PracticeMenu aPracticeMenu);
	public void remove(PracticeMenu aPracticeMenu);
	public PracticeMenuId nextIdentity();
}
