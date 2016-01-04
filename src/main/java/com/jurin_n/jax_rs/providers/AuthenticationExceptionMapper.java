package com.jurin_n.jax_rs.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jurin_n.jax_rs.exception.AuthenticationException;

@Provider
public class AuthenticationExceptionMapper
		implements ExceptionMapper<AuthenticationException> {

	@Override
	public Response toResponse(AuthenticationException e) {
		return Response.noContent()
				.status(Response.Status.UNAUTHORIZED).build();
	}

}
