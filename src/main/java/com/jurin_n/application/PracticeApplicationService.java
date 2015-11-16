package com.jurin_n.application;

import java.util.List;

import javax.inject.Inject;

import com.jurin_n.domain.model.practice.PracticePlan;
import com.jurin_n.domain.model.practice.PracticePlanService;

public class PracticeApplicationService {
	@Inject PracticePlanService ps;
	
	public List<PracticePlan> getPracticePlanList() {
		List<PracticePlan> list  = ps.getPracticePlanList();
		return list;
	}

}
