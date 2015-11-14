package com.jurin_n.domain.model.practice;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PracticeMenuId {
	@Column
	private String id;
	
	public PracticeMenuId(){
		super();
	}
	
	public PracticeMenuId(String anId){
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
