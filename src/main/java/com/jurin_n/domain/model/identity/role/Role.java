package com.jurin_n.domain.model.identity.role;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.jurin_n.domain.model.identity.permission.Permission;

@Entity
@Table(name = "t_Role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private RoleValue value;
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Permission> permissions;
	
	public Role(){
		super();
	}
	
	public Role(RoleValue value, Set<Permission> permissions) {
		super();
		this.value = value;
		this.permissions = permissions;
	}	
	public String getId() {
		return id;
	}
	public RoleValue getValue() {
		return value;
	}
	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setValue(RoleValue value) {
		this.value = value;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
}
