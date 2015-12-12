package com.jurin_n.domain.model.practice.menu;

public interface PracticeMenuRepository {
	public void add(PracticeMenu aPracticeMenu);
	public void remove(PracticeMenu aPracticeMenu);
	public PracticeMenuId nextIdentity();
	public PracticeMenu getMenuById(PracticeMenuId practiceMenuId);
}
