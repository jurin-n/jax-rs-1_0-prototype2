package com.jurin_n.jax_rs.resources;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.jurin_n.domain.model.BaseEntity;
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
	
	protected Response process(String method) {
		Response res = null;
		try {
			Method m = this.getClass().getDeclaredMethod(method);

			//権限チェック
			checkPermissions(m.getAnnotation(Permmisions.class).value());

			//メソッド呼び出し
			res = (Response)m.invoke(this);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
