package com.jurin_n.domain.model.identity.user;

public class UserDescriptor {

	private UserId userId;
	private String name;

	public UserDescriptor(UserId userId,String name) {
		super();
		this.userId = userId;
		this.name = name;
	}

	public UserId getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}
}
