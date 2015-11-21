package com.jurin_n.domain.model.practice;

public interface PracticeMemberRepository {
	public void add(PracticeMember aMember);
	public void remove(PracticeMember aMember);
	public PracticeMemberId nextIdentity();
}
