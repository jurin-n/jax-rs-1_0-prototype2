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
