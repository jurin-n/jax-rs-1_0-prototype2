package com.jurin_n.jax_rs.resources.common;

import java.lang.reflect.Method;

import javax.ws.rs.core.Response;

import com.jurin_n.jax_rs.resources.BaseResource;

public abstract class BaseProcess {
	protected Method method;
	protected BaseResource baseResource;
	
	public BaseProcess(BaseResource baseResource) {
		this.baseResource = baseResource;
	}
	public Method getMethod(){
		return method;
	}
	public abstract void createMethod() throws Exception;
	public abstract Response invokeMethod() throws Exception;
}
