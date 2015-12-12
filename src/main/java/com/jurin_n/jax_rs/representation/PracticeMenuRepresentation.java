package com.jurin_n.jax_rs.representation;

import com.jurin_n.jax_rs.providers.BaseJsonMarshaller;

public class PracticeMenuRepresentation implements BaseJsonMarshaller {
	private String id;
	private String menu;
	
	public PracticeMenuRepresentation(){
		super();
	}
	
	public PracticeMenuRepresentation(String id, String menu){
		this.id = id;
		this.menu = menu;
	}

	public String getId() {
		return id;
	}

	public String getMenu() {
		return menu;
	}
}
