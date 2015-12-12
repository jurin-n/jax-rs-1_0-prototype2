package com.jurin_n.domain.model.practice.record;

import javax.persistence.Embeddable;

@Embeddable
public class PracticeRecordId {
	private String id;
	
	public PracticeRecordId(){
		super();
	}
	
	public PracticeRecordId(String anId){
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
		
		if(anObject instanceof PracticeRecordId){
			PracticeRecordId typedObject = (PracticeRecordId) anObject;
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
