package com.jurin_n.infrastructure.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jurin_n.domain.model.practice.PracticeMember;
import com.jurin_n.domain.model.practice.PracticeMemberId;
import com.jurin_n.domain.model.practice.PracticeMemberRepository;

@Stateless
public class JPAPracticeMemberRepository implements PracticeMemberRepository {
	@PersistenceContext
	private EntityManager em;
	
	public void setEntityManager(EntityManager em){
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
	public void add(PracticeMember aMember) {
		em.persist(aMember);
	}

	@Override
	public void remove(PracticeMember aMember) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PracticeMemberId nextIdentity() {
		return new PracticeMemberId(
				java.util.UUID.randomUUID().toString().toUpperCase()
				);
	}

}
