package com.jurin_n.domain.model.practice;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Ignore;
import org.junit.Test;

import com.jurin_n.infrastructure.persistence.JPAPracticePlanRepository;

public class PracticePlanServiceTest {
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
    			"jdbc:postgresql://" + host+ ":"+ port +"/" + databaseName);
    	return Persistence.createEntityManagerFactory("jax-rs-1_0-prototype2-UnitTest",pro)
                          .createEntityManager();
    }
	
    @Test
	//@Ignore //PracticePlanエンティティに@Embedded３つつけているのでエラー。
	public void test() {
		EntityManager em = this.getEm();
		PracticePlanService sut = new PracticePlanService();
		sut.setEntityManager(em);
		sut.repo = new JPAPracticePlanRepository();
	    em.getTransaction().begin();  
		
	    Date aStartDate = new Date();
	    Date aEndDate = new Date(aStartDate.getTime()+1000L*60*60*24*3);//３日加算
	    sut.addPracticePlan(
				     new PracticeMenuId("PracticeMenuId-For-UnitTest")
					,new PracticeMemberId("MemberId-For-UnitTest")
					,aStartDate
					,aEndDate
					);
			
		em.getTransaction().commit();
	}

}
