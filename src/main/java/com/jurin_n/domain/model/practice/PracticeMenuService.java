package com.jurin_n.domain.model.practice;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PracticeMenuService {

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	PracticeMenuRepository repo;
	
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
	
	public PracticeMenuId addPracticeMenu(String aMenu){
		PracticeMenu menu = new PracticeMenu(
				 repo.nextIdentity() 
				,aMenu
				);
		
		repo.setEntityManager(em);
		repo.add(menu);
		
		return menu.getPracticeMenuId();
	}
}
