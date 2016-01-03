package com.jurin_n.domain.model.identity;

import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jurin_n.domain.model.identity.user.User;
import com.jurin_n.domain.model.identity.user.UserDescriptor;
import com.jurin_n.domain.model.identity.user.UserId;
import com.jurin_n.domain.model.identity.user.UserRepository;

@Stateless
public class AuthenticationService {
	@Inject
	private UserRepository repo;

	private String dateHeader = null;
	private String authHeader = null;
	private String userId = null;
	private String authKey = null;
	
	public UserDescriptor authenticateFromHeader(Map<String, String> headers) {
		//Headerから必要な情報を取得し、フィールドにセット
		setFiledsForAuth(headers);

		//ユーザIDをキーにリポジトリからユーザ情報を取得
		User selectedUser = repo.getUserById(new UserId(userId));
		if(selectedUser == null){
			return null;
		}

		//アプリケーションで利用できるユーザ情報を設定
		UserDescriptor userDesc = new UserDescriptor(
										 selectedUser.getUserid()
										,selectedUser.getName()
										,selectedUser.getRoles()
										);
		return userDesc;
	}

	private void setFiledsForAuth(Map<String, String> headers) {
		dateHeader = headers.get("Date");
		authHeader = headers.get("Authorization");

		if (dateHeader == null || authHeader == null) {
			throw new IllegalArgumentException();
		}

		userId = authHeader.split(":")[0];
		authKey = authHeader.split(":")[1];
	}
}
