package com.jurin_n.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jurin_n.entity.Team;

@Stateless
public class TeamApplicationService {
	@PersistenceContext
	private EntityManager em;
	
	public void createTeam(Team t){
		TeamService ts = new TeamService(em);
		ts.createTeam(t);
	}
	
	public Team getTeamById(String id){
		TeamService ts = new TeamService(em);
		Team t = ts.getTeamById(id);
		return t;
	}
}
