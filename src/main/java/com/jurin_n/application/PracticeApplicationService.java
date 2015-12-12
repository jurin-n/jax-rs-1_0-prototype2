package com.jurin_n.application;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jurin_n.domain.model.practice.member.PracticeMember;
import com.jurin_n.domain.model.practice.member.PracticeMemberId;
import com.jurin_n.domain.model.practice.member.PracticeMemberRepository;
import com.jurin_n.domain.model.practice.menu.PracticeMenuRepository;
import com.jurin_n.domain.model.practice.plan.PracticePlan;
import com.jurin_n.domain.model.practice.plan.PracticePlanRepository;
import com.jurin_n.domain.model.practice.plan.PracticePlanService;

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
		memberRepo.add(aMember);
	}

	public List<PracticeMember> getPracticeMemberList() {
		List<PracticeMember> list = memberRepo.getPracticeMemberAll();
		return list;
	}

	public void updatePracticeMember(PracticeMember aMember) {
		memberRepo.add(aMember);
	}

	public void deletePracticeMember(PracticeMemberId practiceMemberId) {
		PracticeMember aMember = memberRepo.getMemberById(practiceMemberId);
		memberRepo.remove(aMember);
	}
	
	public PracticeMember getPracticeMember(PracticeMemberId practiceMemberId) {
		PracticeMember aMember = memberRepo.getMemberById(practiceMemberId);
		return aMember;
	}
}
