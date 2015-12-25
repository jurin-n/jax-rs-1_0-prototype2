package com.jurin_n.domain.model.identity;

import java.util.Map;

import com.jurin_n.domain.model.identity.user.UserDescriptor;
import com.jurin_n.domain.model.identity.user.UserId;

public class AuthenticationService {

	public UserDescriptor authenticateFromHeader(Map<String, String> headers) {
		String dateHeader = headers.get("Date");
		String authHeader = headers.get("Authorization");

		if (dateHeader == null) {
			return null;
		}
		if (authHeader == null) {
			return null;
		}

		UserDescriptor userDesc = new UserDescriptor(
										 new UserId("")
										,""
										);
		return userDesc;
	}
}
