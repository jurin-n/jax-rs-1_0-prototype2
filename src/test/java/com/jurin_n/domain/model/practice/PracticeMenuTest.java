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
