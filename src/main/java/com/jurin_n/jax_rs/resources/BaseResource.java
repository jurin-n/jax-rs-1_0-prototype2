package com.jurin_n.jax_rs.resources;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.identity.user.UserDescriptor;

public class BaseResource {
	@Context HttpHeaders headers;
	@Context HttpServletRequest servletRequest;
	protected UserDescriptor userDescriptor;

	@PostConstruct
	public void init(){
		//認証フィルターで取得したユーザ情報をセット
		userDescriptor
			= (UserDescriptor) servletRequest.getAttribute("userDescriptor");
	}
	
	protected void checkPermissions(PermissionValue permission) {
		if(userDescriptor.inPermission(permission)==false){
			throw new WebApplicationException(Response.Status.FORBIDDEN);
		}
	}
}
