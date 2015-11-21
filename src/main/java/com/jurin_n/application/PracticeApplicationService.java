package com.jurin_n.application;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jurin_n.domain.model.practice.PracticeMember;
import com.jurin_n.domain.model.practice.PracticeMemberRepository;
import com.jurin_n.domain.model.practice.PracticePlan;
import com.jurin_n.domain.model.practice.PracticePlanService;

@Stateless
public class PracticeApplicationService {
	@Inject PracticePlanService ps;
	@Inject PracticeMemberRepository memberRepo;
	
	//
	// PracticePlan
	//
	public List<PracticePlan> getPracticePlanList() {
		List<PracticePlan> list  = ps.getPracticePlanList();
		return list;
	}
	
	//
	// PracticeMember
	//
	public PracticeMember addPracticeMember(PracticeMember aMember) {
		if(aMember.getMemberId() == null){
			aMember.setMemberId(memberRepo.nextIdentity());
		}
		memberRepo.add(aMember);
		return memberRepo.getMemberById(aMember.getMemberId());
	}

	public List<PracticeMember> getPracticeMemberList() {
		List<PracticeMember> list = memberRepo.getPracticeMemberAll();
		return list;
	}
}
