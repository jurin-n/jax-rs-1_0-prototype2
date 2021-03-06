package com.jurin_n.domain.model.identity.user;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.jurin_n.domain.model.BaseEntity;

@Embeddable
public class UserId extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	//このidフィールドをスーパークラスに移動したいが、
	//JPAがフィールドとして処理しなくなるのでサブクラスに記述。
	private String id;
	
	public UserId(){
		super();
	}
	
	public UserId(String anId){
		super();
		this.id = anId;
	}

	public String getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object anObject){
		
		if(anObject == this) return true; //等値なので等価
		if(anObject == null) return false;
		
		if(anObject instanceof UserId){
			UserId typedObject = (UserId) anObject;
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
