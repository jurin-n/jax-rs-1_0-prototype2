package com.jurin_n.application;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jurin_n.domain.model.practice.member.PracticeMemberId;
import com.jurin_n.domain.model.practice.menu.PracticeMenu;
import com.jurin_n.domain.model.practice.menu.PracticeMenuId;
import com.jurin_n.domain.model.practice.menu.PracticeMenuRepository;
import com.jurin_n.domain.model.practice.plan.PracticePlan;
import com.jurin_n.domain.model.practice.plan.PracticePlanId;
import com.jurin_n.domain.model.practice.plan.PracticePlanRepository;
import com.jurin_n.infrastructure.persistence.JPAPracticeMenuRepository;
import com.jurin_n.infrastructure.persistence.JPAPracticePlanRepository;

public class PracticeApplicationServiceTest {
    private static EntityManager getEm() {
    	return Persistence.createEntityManagerFactory("jax-rs-1_0-prototype2-UnitTest")
                .createEntityManager();
    }
    private PracticeApplicationService sut;
    private PracticeMenuRepository menuRepo;
    private PracticePlanRepository planRepo;
    private EntityManager em;
	
    @Before
	public void setUp(){
    	em = this.getEm();
    	sut = new PracticeApplicationService();
    	menuRepo = new JPAPracticeMenuRepository(em);
    	planRepo = new JPAPracticePlanRepository(em);
    	sut.menuRepo = menuRepo;
    	sut.planRepo = planRepo;
	}
    
    @After
	public void tearDown(){
    	em.close();
    	sut = null;
	}
    
	@Test(expected=RuntimeException.class)
	public void test_deletePracticeMenu_削除対象が存在しない場合_例外スロー() {
		em.getTransaction().begin();
		PracticeMenuId id = new PracticeMenuId("test");
		sut.deletePracticeMenu(id);
		em.getTransaction().commit();
	}
	
	@Test(expected=RuntimeException.class)
	public void test_deletePracticeMenu_削除対象のメニューがプランに登録中なのでエラー() {
		//初期化
		em.getTransaction().begin();
		PracticeMenu menu = new PracticeMenu(
				 new PracticeMenuId("menu001")
				,"メニュー"
				);
		menuRepo.add(menu);
		planRepo.add(new PracticePlan(
							 new PracticePlanId("plan001")
							,menu.getPracticeMenuId()
							,new PracticeMemberId("member001")
							,new Date()
							,new Date()
							)
					);
		em.getTransaction().commit();
		
		//テストの実行
		em.getTransaction().begin();
		sut.deletePracticeMenu(menu.getPracticeMenuId());
		em.getTransaction().commit();
	}

	@Test
	public void test_deletePracticeMenu_削除対象のメニューがプランなければ正常終了() {
		//初期化
		em.getTransaction().begin();
		PracticeMenu menu = new PracticeMenu(
				 new PracticeMenuId("menu002")
				,"メニュー"
				);
		menuRepo.add(menu);
		em.getTransaction().commit();
		
		//テストの実行
		em.getTransaction().begin();
		sut.deletePracticeMenu(menu.getPracticeMenuId());
		em.getTransaction().commit();
	}
}
