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
import com.jurin_n.jax_rs.resources.common.BaseProcess;

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
	protected Response process(final String methodName, final String... param) {

		BaseProcess baseProcess = new BaseProcess(this){
			@Override
			public void createMethod() throws Exception{
				method = baseResource.getClass().getDeclaredMethod(methodName,String[].class);
			}
			
			@Override
			public Response invokeMethod() throws Exception{
				return (Response)method.invoke(baseResource,param);
			}
		};

		return process(baseProcess);
	}

	protected Response process(final String methodName) {

		BaseProcess baseProcess = new BaseProcess(this){
			@Override
			public void createMethod() throws Exception{
				method = baseResource.getClass().getDeclaredMethod(methodName);
			}
			
			@Override
			public Response invokeMethod() throws Exception{
				return (Response)method.invoke(baseResource);
			}
		};

		return process(baseProcess);
	}

	protected Response process(final String methodName, final BaseJsonMarshaller json) {

		BaseProcess baseProcess = new BaseProcess(this){
			@Override
			public void createMethod() throws Exception{
				method = baseResource.getClass().getDeclaredMethod(methodName, BaseJsonMarshaller.class);
			}
			
			@Override
			public Response invokeMethod() throws Exception{
				return (Response)method.invoke(baseResource,json);
			}
		};

		return process(baseProcess);
	}
	
	protected Response process(final String methodName, final String id
								, final BaseJsonMarshaller json) {

		BaseProcess baseProcess = new BaseProcess(this){
			@Override
			public void createMethod() throws Exception{
				method = baseResource.getClass().getDeclaredMethod(
									 methodName
									,String.class
									,BaseJsonMarshaller.class
									);
			}
			
			@Override
			public Response invokeMethod() throws Exception{
				return (Response)method.invoke(baseResource,id,json);
			}
		};

		return process(baseProcess);
	}

	protected Response process(BaseProcess baseProcess){
		Response res = null;
		try {
			//Method作成
			baseProcess.createMethod();
	
			//前処理
			beforeProcess(baseProcess.getMethod());
	
			//メソッド呼び出し
			res = baseProcess.invokeMethod();
	
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
