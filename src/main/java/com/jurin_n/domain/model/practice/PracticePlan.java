package com.jurin_n.domain.model.practice;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jurin_n.domain.model.BaseEntity;

@Entity
@Table(name="t_PracticePlan")
@NamedQueries({
	@NamedQuery(name = "PracticePlan.FIND_ALL", query = "select p from PracticePlan p")
})
public class PracticePlan extends BaseEntity {

	@EmbeddedId
	private PracticePlanId practicePlanId;
	@Embedded @AttributeOverride(name="id", column=@Column(name = "menuId"))
	//@JsonIgnore
	private PracticeMenuId practiceMenuId;
	@Transient
	private PracticeMenu practiceMenu;
	@Embedded @AttributeOverride(name="id", column=@Column(name = "memberId"))
	//@JsonIgnore
	private PracticeMemberId practiceMemberId;
	@Transient
	private PracticeMember practiceMember;
	PracticeStatus status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	public PracticePlan(){
		super();
	}
	
	public PracticePlan(
			 PracticePlanId aPracticePlanId
			,PracticeMenuId aPracticeMenuId
			,PracticeMemberId aMemberId
			,Date aStartDate
			,Date aEndDate
			){
		Date date = new Date();
		this.practicePlanId = aPracticePlanId;
		this.practiceMenuId = aPracticeMenuId;
		this.practiceMemberId = aMemberId;
		this.status = PracticeStatus.OPEN;
		this.startDate = aStartDate;
		this.endDate = aEndDate;
		this.createDate = date;
		this.updateDate = date;
	}

	public PracticePlanId getPracticePlanId() {
		return practicePlanId;
	}
	public PracticeMenuId getPracticeMenuId() {
		return practiceMenuId;
	}
	public PracticeMenu getPracticeMenu() {
		return practiceMenu;
	}
	public PracticeMemberId getPracticeMemberId() {
		return practiceMemberId;
	}
	public PracticeMember getPracticeMember() {
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

	public Date getCreateDate() {
		return createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	
	public void setPracticeMenu(PracticeMenu aPracticeMenu) {
		this.practiceMenu = aPracticeMenu;
	}

	public void setPracticeMember(PracticeMember aPracticeMember) {
		this.practiceMember = aPracticeMember;
	}

	public void setPracticePlanId(PracticePlanId aPracticePlanId) {
		this.practicePlanId = aPracticePlanId;	
	}
}
