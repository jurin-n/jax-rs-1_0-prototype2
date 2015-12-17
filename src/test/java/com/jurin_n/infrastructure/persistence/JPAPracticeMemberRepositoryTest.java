package com.jurin_n.infrastructure.persistence;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.CoreMatchers.nullValue;

import com.jurin_n.domain.model.practice.member.PracticeMember;
import com.jurin_n.domain.model.practice.member.PracticeMemberRepository;
import com.jurin_n.jax_rs.representation.PracticeMemberRepresentation;

public class JPAPracticeMemberRepositoryTest {
    private static EntityManager getEm() {
    	return Persistence.createEntityManagerFactory("jax-rs-1_0-prototype2-UnitTest")
                .createEntityManager();
    }
    private PracticeMemberRepository repo;
    private EntityManager em;
	
    @Before
	public void setUp(){
    	em = this.getEm();
		repo = new JPAPracticeMemberRepository(em);
	}
	
    @After
	public void tearDown(){
    	em.close();
    	repo = null;
	}
	
    @Test
	public void testPracticeMemberの追加と更新ができることを確認() {
		/*
		 * 1つ追加
		 */		
		//JAX-RSからの新規登録リクエスト
		PracticeMemberRepresentation aMember
				= new PracticeMemberRepresentation(
							  repo.nextIdentity().getId()
							, "テスト　太郎");
		//リクエスト -> モデル変換
		PracticeMember addMember = new PracticeMember(aMember);

		//登録
		em.getTransaction().begin();
		repo.add(addMember);		
		em.getTransaction().commit();
		
		// 追加したPracticeMemberを検索
		PracticeMember gotMember = repo.getMemberById(addMember.getPracticeMemberId());
		
		assertThat(gotMember,is(not(nullValue())));
		assertThat(gotMember.getPracticeMemberId()
						,is(addMember.getPracticeMemberId()));
		assertThat(gotMember.getName()
						,is(addMember.getName()));
		
		
		/*
		 * 更新
		 */
		//JAX-RSからの更新リクエスト
		aMember = new PracticeMemberRepresentation(
				  addMember.getPracticeMemberId().getId()
				, "テスト　二郎");
		
		//リクエスト -> モデル変換
		PracticeMember updateMember = new PracticeMember(aMember);
		//更新
		em.getTransaction().begin();
		repo.update(updateMember);
		em.getTransaction().commit();
		
		
		//更新したPracticeMemberを検索
		gotMember = repo.getMemberById(updateMember.getPracticeMemberId());
		
		assertThat(gotMember,is(not(nullValue())));
		assertThat(gotMember.getPracticeMemberId()
						,is(updateMember.getPracticeMemberId()));
		assertThat(gotMember.getName()
				,is(updateMember.getName())); //更新後の名称になってること確認
		
		//テストで使ったデータを削除。
		repo.remove(gotMember);
	}
	
    @Test(expected=IllegalStateException.class)
	public void test存在しないIdのPracticeMemberで更新できないこと確認() {
		//JAX-RSからの新規登録リクエスト
		PracticeMemberRepresentation aMember = new PracticeMemberRepresentation("ccc", "テスト　太郎");
		//リクエスト -> モデル変換
		PracticeMember updateMember = new PracticeMember(aMember);
		//更新
		em.getTransaction().begin();
		repo.update(updateMember);
		fail();
		em.getTransaction().commit();
    }
    
	@Test
	public void testすべてのPracticeMemberを検索できる() {
		/*
		 * 3つ追加
		 */		
		//リクエスト -> モデル変換
		PracticeMember addMember1 = new PracticeMember(
										new PracticeMemberRepresentation(repo.nextIdentity().getId(), "テスト　太郎")
									);
		PracticeMember addMember2 = new PracticeMember(
										new PracticeMemberRepresentation(repo.nextIdentity().getId(), "テスト　二郎")
									);
		PracticeMember addMember3 = new PracticeMember(
										new PracticeMemberRepresentation(repo.nextIdentity().getId(), "テスト　三郎")
									);
		//登録
		em.getTransaction().begin();
		repo.add(addMember1);	
		repo.add(addMember2);
		repo.add(addMember3);
		em.getTransaction().commit();
		List<PracticeMember> list =  repo.getPracticeMemberAll();
		
		assertThat(list.size(),is(3));
	}
}
