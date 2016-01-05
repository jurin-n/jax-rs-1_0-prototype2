package com.jurin_n.jax_rs.resources;

import java.util.HashMap;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import com.jurin_n.domain.model.identity.AuthenticationService;
import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.identity.user.UserDescriptor;
import com.jurin_n.jax_rs.exception.AuthenticationException;
import com.jurin_n.jax_rs.exception.AuthorizationException;

public class BaseResource {
	@Context HttpHeaders headers;
	@Inject private AuthenticationService auth;
	protected UserDescriptor userDescriptor;
	
	protected void authentication(){
		MultivaluedMap<String, String> multivaluedMap = headers.getRequestHeaders();
		HashMap<String,String> map = new HashMap<>();
		map.put("Authorization", multivaluedMap.get("Authorization").get(0));
		//map.put("Date", multivaluedMap.get("Date").get(0));
		map.put("Date", "dummy");
		
		userDescriptor = auth.authenticateFromHeader(map);
		
		if(userDescriptor==null){
			throw new AuthenticationException("認証失敗");
		}
	}

	protected void checkPermissions(PermissionValue permission) {
		if(userDescriptor.inPermission(permission)==false){
			throw new AuthorizationException("認可失敗");
		}
	}
}
