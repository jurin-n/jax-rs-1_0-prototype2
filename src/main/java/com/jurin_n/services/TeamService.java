package com.jurin_n.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jurin_n.entity.Team;
import com.jurin_n.infrastructure.persistence.JPATeamRepository;

@Stateless
public class TeamService {
	
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

	public void createTeam(Team t){
		JPATeamRepository jtr = new JPATeamRepository(em);
		jtr.add(t);
	}
	
	
	public Team getTeamById(String id){
		JPATeamRepository jtr = new JPATeamRepository(em);
		Team t = jtr.teamOfId(id);
		return t;
	}
}
