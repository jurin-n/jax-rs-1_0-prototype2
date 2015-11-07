package com.jurin_n.services;

import javax.persistence.EntityManager;

import com.jurin_n.entity.Team;
import com.jurin_n.infrastructure.persistence.JPATeamRepository;

public class TeamService {
	private EntityManager em;
	
	public TeamService(EntityManager em) {
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
