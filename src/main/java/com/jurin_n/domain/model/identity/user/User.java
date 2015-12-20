package com.jurin_n.domain.model.identity.user;

import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.jurin_n.domain.model.identity.Status;
import com.jurin_n.domain.model.identity.role.Role;

@Entity
@Table(name = "t_User")
public class User {
	@EmbeddedId
	private UserId userid;
	@OneToMany
	private Set<Role> roles;
	private Status  status;
	//private TenantId tenantId; //テナント対応時に追加予定。
}
