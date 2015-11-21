package com.jurin_n.domain.model.practice;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jurin_n.domain.model.BaseEntity;

@Entity
@Table(name="t_PracticeMember")
@NamedQueries({
	@NamedQuery(name = "PracticeMember.FIND_ALL", query = "select p from PracticeMember p")
})
public class PracticeMember extends BaseEntity {
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PracticeMemberId getMemberId() {
		return memberId;
	}

	public void setMemberId(PracticeMemberId aMemberId) {
		this.memberId = aMemberId;
	}
}
