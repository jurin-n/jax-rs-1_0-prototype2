package com.jurin_n.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jurin_n.entity.Team;

@Stateless
public class TeamService {
	@PersistenceContext
	private EntityManager em;
	  
	public void createTeam(Team t){
		em.persist(t);
	}
	
	public Team getTeamById(String id){
		return em.find(Team.class, id);
	}
}
