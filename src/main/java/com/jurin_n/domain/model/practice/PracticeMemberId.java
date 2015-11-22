package com.jurin_n.domain.model.practice;

import javax.persistence.Embeddable;

import com.jurin_n.domain.model.BaseEntity;

@Embeddable
public class PracticeMemberId extends BaseEntity {
	
	private String id;
	
	public PracticeMemberId(){
		super();
	}
	
	public PracticeMemberId(String anId){
		super();
		this.setId(anId);
	}
	
	private void setId(String anId) {
		this.id = anId;
	}
	
	public String getId() {
		return this.id;
	}
}
