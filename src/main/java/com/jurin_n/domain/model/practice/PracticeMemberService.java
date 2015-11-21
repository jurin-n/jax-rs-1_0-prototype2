package com.jurin_n.domain.model.practice;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PracticeMemberService {
	
	@Inject
	PracticeMemberRepository repo;
	
	public PracticeMemberId addMember(String aName){
		PracticeMember menu = new PracticeMember(
				 repo.nextIdentity() 
				,aName
				);
		
		repo.add(menu);
		
		return menu.getMemberId();
	}
}
