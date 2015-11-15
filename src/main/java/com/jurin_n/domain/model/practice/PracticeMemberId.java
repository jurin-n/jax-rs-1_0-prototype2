package com.jurin_n.domain.model.practice;

import javax.persistence.Embeddable;

@Embeddable
public class PracticeMemberId {
	
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
