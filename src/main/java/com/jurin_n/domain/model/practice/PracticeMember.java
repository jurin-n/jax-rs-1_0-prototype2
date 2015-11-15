package com.jurin_n.domain.model.practice;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_PracticeMember")
public class PracticeMember {
	@EmbeddedId
	private PracticeMemberId memberId;
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	public PracticeMember(){
		super();
	}
	
	public PracticeMember(PracticeMemberId aMemberId,String aName){
		Date date = new Date();
		this.memberId = aMemberId;
		this.name = aName;
		this.createDate = date;
		this.updateDate = date;
	}
	
	public PracticeMemberId getMemberId() {
		return memberId;
	}
}
