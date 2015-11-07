package com.jurin_n.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.jurin_n.entity.Team;

@Stateless
public class TeamApplicationService {

	@EJB
	TeamService ts;
	
	public void createTeam(Team t){
		ts.createTeam(t);
	}
	
	public Team getTeamById(String id){
		Team t = ts.getTeamById(id);
		return t;
	}
}
