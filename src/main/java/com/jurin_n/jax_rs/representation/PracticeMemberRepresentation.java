package com.jurin_n.jax_rs.representation;

import com.jurin_n.jax_rs.providers.BaseJsonMarshaller;

public class PracticeMemberRepresentation implements BaseJsonMarshaller {
	private String id;
	private String name;
	
	public PracticeMemberRepresentation(){
		super();
	}
	
	public PracticeMemberRepresentation(String id, String name){
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
