package com.jurin_n.domain.model.practice.plan;

import javax.persistence.Embeddable;

import com.jurin_n.domain.model.BaseIdEntity;

@Embeddable
public class PracticePlanId extends BaseIdEntity {

	//このidフィールドをスーパークラスに移動したいが、
	//JPAがフィールドとして処理しなくなるのでサブクラスに記述。
	private String id;
	
	public PracticePlanId(){
		super();
	}
	
	public PracticePlanId(String anId){
		super();
		this.id = anId;
	}

	public String getId() {
		return this.id;
	}

	protected boolean isEquals(Object anObject){
		PracticePlanId typedObject = (PracticePlanId) anObject;
		if(this.getId().equals(typedObject.getId())){
			//idが同じならば等価
			return true;
		}
		return false;
	}

	protected int addHashCodeValue(int aResult){
		int result = 0;
		//resultに各フィールドの影響を加える
		result = result * 31 + this.getId().hashCode();
		return result;
	}
	
	@Override
	public String toString(){
		return this.getClass().getSimpleName() + "[id=" + this.getId() + "]";
	}
}
