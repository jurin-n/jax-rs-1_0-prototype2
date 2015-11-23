package com.jurin_n.domain.model.practice;

import javax.persistence.Embeddable;

import com.jurin_n.domain.model.BaseEntity;
import java.io.Serializable;

@Embeddable
public class PracticePlanId extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	
	@Override
	public boolean equals(Object anObject){
		boolean equalObjects = false;
		
		if(anObject != null && this.getClass() == anObject.getClass()){
			PracticePlanId typedObject = (PracticePlanId) anObject;
			equalObjects = this.getId().equals(typedObject.getId());
		}
		
		return equalObjects;
	}
	
}
