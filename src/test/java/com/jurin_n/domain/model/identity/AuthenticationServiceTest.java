package com.jurin_n.domain.model.identity;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.jurin_n.domain.model.identity.user.User;
import com.jurin_n.domain.model.identity.user.UserDescriptor;
import com.jurin_n.domain.model.identity.user.UserId;
import com.jurin_n.junit.rules.JPAResource;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.CoreMatchers.nullValue;

public class AuthenticationServiceTest {
	@ClassRule
	public static JPAResource jpa = new JPAResource();
	
	//テスト対象クラス
	private AuthenticationService sut;

	@Before
	public void setUp(){
		//テスト対象セットアップ
		sut = new AuthenticationService();
	}
	
	@Test
	public void test_authenticateFromHeader_認証成功() {
		//初期化
		User user = jpa.getEm().find(User.class, new UserId("user001"));
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "user001:xxxx");
		headers.put("Date", "xxx");
		
		//テスト実行
		UserDescriptor userDescriptor = sut.authenticateFromHeader(headers);
		
		//検証
		assertThat(userDescriptor,is(not(nullValue())));
		assertThat(userDescriptor.getUserId(),is(user.getUserid()));
	}

	@Test
	public void test_authenticateFromHeader_存在しないユーザIDのため認証失敗() {
		//初期化
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "xxxx001:xxxx");
		headers.put("Date", "xxx");
		
		//テスト実行
		UserDescriptor userDescriptor = sut.authenticateFromHeader(headers);
		
		//検証
		assertThat(userDescriptor,is(nullValue()));
	}
	
	@Test
	public void test_authenticateFromHeader_Authorizationヘッダがないため認証失敗() {
		//初期化
		Map<String, String> headers = new HashMap<>();
		headers.put("Date", "xxx");

		//テスト実行
		UserDescriptor userDescriptor = sut.authenticateFromHeader(headers);
		
		//検証
		assertThat(userDescriptor,is(nullValue()));
	}
	
	@Test
	public void test_authenticateFromHeader_Dateヘッダがないため認証失敗() {
		//初期化
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "xxx");

		//テスト実行
		UserDescriptor userDescriptor = sut.authenticateFromHeader(headers);
		
		//検証
		assertThat(userDescriptor,is(nullValue()));
	}
}
