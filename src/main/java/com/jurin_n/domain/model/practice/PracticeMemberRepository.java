package com.jurin_n.domain.model.practice;

import javax.persistence.EntityManager;

public interface PracticeMemberRepository {
	public void setEntityManager(EntityManager em);
	public void add(PracticeMember aMember);
	public void remove(PracticeMember aMember);
	public PracticeMemberId nextIdentity();
}
