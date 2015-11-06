package com.jurin_n.entity;

import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Team
 *
 */
@Entity
@Table(name="t_team")
public class Team {
	
	@Id
	private String id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Member> members;
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	public List<Member> getMembers() {
		return members;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Team() {
		super();
	}
   
}
