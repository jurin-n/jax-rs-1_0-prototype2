package com.jurin_n.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jurin_n.entity.Team;
import com.jurin_n.infrastructure.persistence.JPATeamRepository;

@Stateless
public class TeamService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	JPATeamRepository repo;
	
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
		repo.setEntityManager(em);
		repo.add(t);
	}
	
	
	public Team getTeamById(String id){
		repo.setEntityManager(em);
		Team t = repo.teamOfId(id);
		return t;
	}
}
