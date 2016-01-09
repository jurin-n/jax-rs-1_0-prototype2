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
	
	protected Response process(String methodName, Object... params){
		Response res = null;
		try {
			Class<?>[] clazzes = new Class<?>[params.length];
			for(int i=0 ; i< params.length ; i++){
				clazzes[i] = params[i].getClass();
			}
			
			//Method作成
			Method method = this.getClass().getDeclaredMethod(methodName,clazzes);	

			//前処理
			beforeProcess(method);

			//メソッド呼び出し
			res = (Response) method.invoke(this,params);
	
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
