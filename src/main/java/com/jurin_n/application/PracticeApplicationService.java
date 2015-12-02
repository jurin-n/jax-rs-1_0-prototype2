package com.jurin_n.application;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jurin_n.domain.model.practice.PracticeMember;
import com.jurin_n.domain.model.practice.PracticeMemberRepository;
import com.jurin_n.domain.model.practice.PracticeMenuRepository;
import com.jurin_n.domain.model.practice.PracticePlan;
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

	public void addPracticePlan(PracticePlan aPlan) {		
		aPlan.setPracticePlanId(planRepo.nextIdentity());
		planRepo.add(aPlan);
	}	
	//
	// PracticeMember
	//
	public void addPracticeMember(PracticeMember aMember) {
		aMember.setMemberId(memberRepo.nextIdentity());
		memberRepo.add(aMember);
	}

	public List<PracticeMember> getPracticeMemberList() {
		List<PracticeMember> list = memberRepo.getPracticeMemberAll();
		return list;
	}
}
