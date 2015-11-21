package com.jurin_n.domain.model.practice;

public interface PracticeMenuRepository {
	public void add(PracticeMenu aPracticeMenu);
	public void remove(PracticeMenu aPracticeMenu);
	public PracticeMenuId nextIdentity();
}
