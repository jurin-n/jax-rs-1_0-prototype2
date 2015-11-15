package com.jurin_n.domain.model.practice;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_PracticePlan")
public class PracticePlan {
	@EmbeddedId
	private PracticePlanId practicePlanId;
	@Embedded
	@AttributeOverride(name="id", column=@Column(name = "menuId"))
	private PracticeMenuId practiceMenuId;
	@Embedded
	@AttributeOverride(name="id", column=@Column(name = "memberId"))
	private PracticeMemberId memberId;
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
		this.memberId = aMemberId;
		this.status = PracticeStatus.OPEN;
		this.startDate = aStartDate;
		this.endDate = aEndDate;
		this.createDate = date;
		this.updateDate = date;
	}

	public PracticePlanId getPracticePlanId() {
		return practicePlanId;
	}
}
