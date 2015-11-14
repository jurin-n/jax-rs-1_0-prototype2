package com.jurin_n.domain.model.practice;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

import com.jurin_n.infrastructure.persistence.JPAPracticeMenuRepository;

public class PracticeMenuTest {
    private static EntityManager getEm() {
    	
    	URI dbUri = null;
		try {
			dbUri = new URI(System.getenv("DATABASE_URL"));
		} catch (URISyntaxException e) {
			fail();
		}	
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		
		String host = dbUri.getHost();
		int port = dbUri.getPort();
		String databaseName = dbUri.getPath().substring(1);
		
    	Map<String,String> pro = new HashMap<>();
    	
    	pro.put("javax.persistence.jdbc.user", username);
    	pro.put("javax.persistence.jdbc.password", password);
    	pro.put("javax.persistence.jdbc.url",
    			//"jdbc:postgresql://db1.c66qnw4o8ewu.ap-northeast-1.rds.amazonaws.com:5432/db1");
    			"jdbc:postgresql://" + host+ ":"+ port +"/" + databaseName);
    	return Persistence.createEntityManagerFactory("jax-rs-1_0-prototype2-UnitTest")
                          .createEntityManager();
    }
	@Test
	public void test() {
		EntityManager em = this.getEm();
		
		PracticeMenuService sut  = new PracticeMenuService();
		sut.setEntityManager(em);
		sut.repo = new JPAPracticeMenuRepository();
        
        em.getTransaction().begin();  
		
        sut.addPracticeMenu("12キーで練習ああああ。");
		
		em.getTransaction().commit();
	}

}
