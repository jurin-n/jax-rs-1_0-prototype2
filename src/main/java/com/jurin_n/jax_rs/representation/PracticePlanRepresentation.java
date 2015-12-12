package com.jurin_n.jax_rs.representation;

import java.util.Date;

import com.jurin_n.domain.model.practice.PracticeStatus;
import com.jurin_n.jax_rs.providers.BaseJsonMarshaller;

public class PracticePlanRepresentation implements BaseJsonMarshaller {
	private String id;
	private PracticeMenuRepresentation practiceMenu;
	private PracticeMemberRepresentation practiceMember;
	private PracticeStatus status;
	private Date startDate;
	private Date endDate;
	
	public PracticePlanRepresentation(){
		super();
	}
	
	public PracticePlanRepresentation(
			  String id
			, PracticeMenuRepresentation practiceMenu
			, PracticeMemberRepresentation practiceMember){
		this.id = id;
		this.practiceMenu = practiceMenu;
		this.practiceMember = practiceMember;
	}
	
	public PracticePlanRepresentation(
			  String id
			, PracticeMenuRepresentation practiceMenu
			, PracticeMemberRepresentation practiceMember
			, PracticeStatus status
			, Date startDate
			, Date endDate) {
		this.id = id;
		this.practiceMenu = practiceMenu;
		this.practiceMember = practiceMember;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getId() {
		return id;
	}

	public PracticeMenuRepresentation getPracticeMenu() {
		return practiceMenu;
	}
	
	public PracticeMemberRepresentation getPracticeMember() {
		return practiceMember;
	}

	public PracticeStatus getStatus() {
		return status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
}
