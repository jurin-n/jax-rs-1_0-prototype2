package com.jurin_n.application;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jurin_n.domain.model.team.MemberService;
import com.jurin_n.domain.model.team.Team;
import com.jurin_n.domain.model.team.TeamService;

@Stateless
public class TeamApplicationService {
	@Inject TeamService ts;	
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
