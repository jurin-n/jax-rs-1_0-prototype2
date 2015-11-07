package com.jurin_n.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jurin_n.entity.Team;

@Stateless
public class TeamApplicationService {
	@EJB TeamService ts;	
	@Inject MemberService ms;
	
	public void createTeam(Team t){
		ts.createTeam(t);
	}
	
	public Team getTeamById(String id){
		Team t = ts.getTeamById(id);
		return t;
	}

	public void createSoccerTeam(Team t) {
		//メンバーチェック
		ms.checkMembersForSoccer(t.getMembers());
		
		//チーム作成
		ts.createTeam(t);
	}
}
