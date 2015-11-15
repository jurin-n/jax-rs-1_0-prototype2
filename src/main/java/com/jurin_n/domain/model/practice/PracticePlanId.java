package com.jurin_n.domain.model.practice;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PracticePlanId {
	@Column
	private String id;
	
	public PracticePlanId(){
		super();
	}
	
	public PracticePlanId(String anId){
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
