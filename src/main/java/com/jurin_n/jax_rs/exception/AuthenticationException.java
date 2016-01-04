package com.jurin_n.jax_rs.exception;

public class AuthenticationException extends RuntimeException {

	public AuthenticationException(String aMessage) {
		super(aMessage);
	}
}
