package com.jurin_n.infrastructure.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jurin_n.domain.model.practice.PracticeMenu;
import com.jurin_n.domain.model.practice.PracticeMenuId;
import com.jurin_n.domain.model.practice.PracticeMenuRepository;

@Stateless
public class JPAPracticeMenuRepository implements PracticeMenuRepository {
	@PersistenceContext
	private EntityManager em;

	public JPAPracticeMenuRepository(EntityManager em) {
		this.setEntityManager(em);
	}

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

	@Override
	public PracticeMenu getMenuById(PracticeMenuId id) {
		return em.find(PracticeMenu.class,id);
	}
}
