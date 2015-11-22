package com.jurin_n.application;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jurin_n.domain.model.practice.PracticeMember;
import com.jurin_n.domain.model.practice.PracticeMemberId;
import com.jurin_n.domain.model.practice.PracticeMemberRepository;
import com.jurin_n.domain.model.practice.PracticeMenuId;
import com.jurin_n.domain.model.practice.PracticeMenuRepository;
import com.jurin_n.domain.model.practice.PracticePlan;
import com.jurin_n.domain.model.practice.PracticePlanId;
import com.jurin_n.domain.model.practice.PracticePlanRepository;
import com.jurin_n.domain.model.practice.PracticePlanService;

@Stateless
public class PracticeApplicationService {
	@Inject PracticePlanService ps;
	@Inject PracticePlanRepository planRepo;
	@Inject PracticeMemberRepository memberRepo;
	@Inject PracticeMenuRepository menuRepo;
	//
	// PracticePlan
	//
	public List<PracticePlan> getPracticePlanList() {
		List<PracticePlan> list  = ps.getPracticePlanList();
		for(PracticePlan plan : list){
			
			plan.setPracticeMember(
						memberRepo.getMemberById(plan.getPracticeMemberId())
					);
			plan.setPracticeMenu(
						menuRepo.getMenuById(plan.getPracticeMenuId())
					);
		}
		return list;
	}
	public PracticePlan getPracticePlan(String id) {
		return planRepo.getPracticePlanById(id);
	}

	public PracticePlan addPracticePlan(PracticePlan aPlan) {		
		PracticePlan plan = new PracticePlan(
				 planRepo.nextIdentity()
				,aPlan.getPracticeMenuId()
				,aPlan.getPracticeMemberId()
				,aPlan.getStartDate()
				,aPlan.getEndDate()
				);
		planRepo.add(plan);
		return plan;
	}	
	//
	// PracticeMember
	//
	public PracticeMember addPracticeMember(PracticeMember aMember) {
		if(aMember.getPracticeMemberId() == null){
			aMember.setMemberId(memberRepo.nextIdentity());
		}
		memberRepo.add(aMember);
		return memberRepo.getMemberById(aMember.getPracticeMemberId());
	}

	public List<PracticeMember> getPracticeMemberList() {
		List<PracticeMember> list = memberRepo.getPracticeMemberAll();
		return list;
	}
}
