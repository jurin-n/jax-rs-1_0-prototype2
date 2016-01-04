package com.jurin_n.jax_rs.exception;

public class AuthorizationException extends RuntimeException {

	public AuthorizationException(String aMessage) {
		super(aMessage);
	}
}
