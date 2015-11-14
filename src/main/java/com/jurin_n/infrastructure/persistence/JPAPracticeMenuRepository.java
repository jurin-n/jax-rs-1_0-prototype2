package com.jurin_n.infrastructure.persistence;

import javax.persistence.EntityManager;

import com.jurin_n.domain.model.practice.PracticeMenu;
import com.jurin_n.domain.model.practice.PracticeMenuId;
import com.jurin_n.domain.model.practice.PracticeMenuRepository;

public class JPAPracticeMenuRepository implements PracticeMenuRepository {
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
	public void add(PracticeMenu aPracticeMenu) {
		em.persist(aPracticeMenu);
	}

	@Override
	public void remove(PracticeMenu aPracticeMenu) {
		// TODO Auto-generated method stub

	}

	@Override
	public PracticeMenuId nextIdentity() {
		return new PracticeMenuId(
				java.util.UUID.randomUUID().toString().toUpperCase()
				);
	}

}
