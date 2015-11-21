package com.jurin_n.domain.model.team;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TeamService {
	
	@Inject
	TeamRepository repo;

	public void createTeam(Team t){
		repo.add(t);
	}
	
	
	public Team getTeamById(String id){
		Team t = repo.teamOfId(id);
		return t;
	}
}
