package com.jurin_n.jax_rs.resources;

import java.lang.reflect.Method;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.jurin_n.domain.model.identity.AuthenticationService;
import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.identity.user.UserDescriptor;
import com.jurin_n.jax_rs.providers.BaseJsonMarshaller;

public class BaseResource {
	@Context HttpHeaders headers;
	@Context HttpServletRequest servletRequest;
	@Inject private AuthenticationService auth;
	protected UserDescriptor userDescriptor;

	protected void authentication() {
		MultivaluedMap<String, String> multivaluedMap = headers.getRequestHeaders();
		HashMap<String,String> map = new HashMap<>();
		map.put("Authorization", multivaluedMap.get("Authorization").get(0));
		//map.put("Date", multivaluedMap.get("Date").get(0));
		map.put("Date", "dummy"); //TODO Dateヘッダ取得ロジック追加

		userDescriptor = auth.authenticateFromHeader(map);

		if(userDescriptor==null){
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
	}
	
	protected void checkPermissions(PermissionValue permission) {
		if(userDescriptor.inPermission(permission)==false){
			throw new WebApplicationException(Response.Status.FORBIDDEN);
		}
	}
	
	//TODO このメソッド必要か再考。
	protected Response process(String method, String... param) {
		Response res = null;
		try {
			Method m = this.getClass().getDeclaredMethod(method,String[].class);

			//前処理
			beforeProcess(m);

			//メソッド呼び出し
			res = (Response)m.invoke(this,param); //TODO 警告の対応。

			//後処理
			afterProcess();

		} catch (WebApplicationException e){
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
	
	//TODO processメソッドを下記のようにかけないか検討。実現できたら、Exception処理とか前処理.処理の手続きを１つにまとめることできるはず。
	/*
	protected Response process(String method, BaseJsonMarshaller json) {
		ProcessMethod processMethod = new (
			@Override
			public void createMethod(){
				m = this.getClass().getDeclaredMethod(method,BaseJsonMarshaller.class);
			}
			
			@Override
			public Response invokeMethod(){
				return (Response)m.invoke(this,json);
			}
		);

		return process(processMethod);
	}
	
	protected Response process(ProcessMethod processMethod){
		Response res = null;
		try {
			//Method作成
			processMethod.createMethod();
	
			//前処理
			beforeProcess(processMethod.getMethod());
	
			//メソッド呼び出し
			res = processMethod.invokeMethod();
	
			//後処理
			afterProcess();
		} catch (WebApplicationException e){
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
	
	*/
	
	protected Response process(String method, BaseJsonMarshaller json) {
		Response res = null;
		try {
			Method m = this.getClass().getDeclaredMethod(method,BaseJsonMarshaller.class);

			//前処理
			beforeProcess(m);

			//メソッド呼び出し
			res = (Response)m.invoke(this,json);

			//後処理
			afterProcess();

		} catch (WebApplicationException e){
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
	
	protected Response process(String method, String id, BaseJsonMarshaller json) {
		Response res = null;
		try {
			Method m = this.getClass().getDeclaredMethod(method,String.class,BaseJsonMarshaller.class);

			//前処理
			beforeProcess(m);

			//メソッド呼び出し
			res = (Response)m.invoke(this,id,json);

			//後処理
			afterProcess();
			
		} catch (WebApplicationException e){
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
	
	protected Response process(String method) {
		Response res = null;
		try {
			Method m = this.getClass().getDeclaredMethod(method);

			//前処理
			beforeProcess(m);

			//メソッド呼び出し
			res = (Response)m.invoke(this);

			//後処理
			afterProcess();
			
		} catch (WebApplicationException e){
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
	
	private void beforeProcess(Method method){
		//TODO 処理時間計測開始

		//認証
		authentication();
		
		//権限チェック
		checkPermissions(method.getAnnotation(Permmisions.class).value());

	}
	private void afterProcess(){
		//TODO 処理時間計測終了
	}
}
