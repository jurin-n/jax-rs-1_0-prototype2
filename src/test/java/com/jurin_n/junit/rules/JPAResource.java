package com.jurin_n.junit.rules;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.rules.ExternalResource;

public class JPAResource extends ExternalResource {
    
	private EntityManager em;
    
	@Override
	protected void before(){
		Logger.getAnonymousLogger().info("EntityManger setup.");
		em = Persistence
			.createEntityManagerFactory("jax-rs-1_0-prototype2-UnitTest")
			.createEntityManager();
	}
	
	@Override
	protected void after(){
		em.close();
		Logger.getAnonymousLogger().info("EntityManger close.");
	}
	
	public EntityManager getEm() {
		return this.em;
	}
}
