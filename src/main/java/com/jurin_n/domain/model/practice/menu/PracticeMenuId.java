package com.jurin_n.domain.model.practice.menu;

import javax.persistence.Embeddable;

import com.jurin_n.domain.model.BaseEntity;

@Embeddable
public class PracticeMenuId extends BaseEntity {
	
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
	
	@Override
	public boolean equals(Object anObject){
		
		if(anObject == this) return true; //等値なので等価
		if(anObject == null) return false;
		
		if(anObject instanceof PracticeMenuId){
			PracticeMenuId typedObject = (PracticeMenuId) anObject;
			if(this.getId().equals(typedObject.getId())){
				//idが同じならば等価
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		//適当な初期値
		int result = 37;

		//resultに各フィールドの影響を加える
		result = result * 31 + this.getId().hashCode();

		return result;
	}
	
	@Override
	public String toString(){
		return this.getClass().getSimpleName() + "[id=" + this.getId() + "]";
	}
}