package com.jurin_n.jax_rs.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jurin_n.jax_rs.exception.AuthorizationException;

@Provider
public class AuthorizationExceptionMapper
		implements ExceptionMapper<AuthorizationException> {

	@Override
	public Response toResponse(AuthorizationException e) {
		return Response.noContent()
				.status(Response.Status.FORBIDDEN).build();
	}

}
