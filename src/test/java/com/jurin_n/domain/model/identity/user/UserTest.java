package com.jurin_n.domain.model.identity.user;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.jurin_n.domain.model.identity.Status;
import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.identity.role.Role;
import com.jurin_n.domain.model.identity.role.RoleValue;

public class UserTest {

	private String A_USER_ID_FOR_TEST = "user001";
	private String A_USER_NAME_FOR_TEST = "テスト　太郎";
	private Set<Role> roles;
	private Role role;
	private Set<PermissionValue> permissions;

	@Before
	public void createRole(){
		roles = new HashSet<>();
		permissions = new HashSet<>();

		//writePlanのPermissionを保有するADMINロール作成
		role = new Role();
		role.setValue(RoleValue.ADMIN);
		permissions.add(PermissionValue.writePlan);
		permissions.add(PermissionValue.readPlan);
		role.setPermissions(permissions);

		roles.add(role);
	}
	
	@Test
	public void inRoleメソッドの引数に渡したRoleがUserに含まれてる場合_inRoleメソッドはtrueを返す() {
		User user = new User(
				 new UserId(A_USER_ID_FOR_TEST)
				,A_USER_NAME_FOR_TEST
				,roles
				,Status.ACTIVE);

		assertTrue(user.inRole(RoleValue.ADMIN));
	}
	
	@Test
	public void inRoleメソッドの引数に渡したRoleがUserに含まれていない場合_inRoleメソッドはfalseを返す() {
		User user = new User(
				 new UserId(A_USER_ID_FOR_TEST)
				,A_USER_NAME_FOR_TEST
				,roles
				,Status.ACTIVE);

		assertFalse(user.inRole(RoleValue.MEMBER));
	}

	@Test
	public void inPermissionメソッドの引数に渡したPermissionがUserに含まれている場合_inPermissionメソッドはtrueを返す() {
		User user = new User(
				 new UserId(A_USER_ID_FOR_TEST)
				,A_USER_NAME_FOR_TEST
				,roles
				,Status.ACTIVE);

		assertTrue(user.inPermission(PermissionValue.writePlan));
	}
	
	@Test
	public void inPermissionメソッドの引数に渡したPermissionがUserに含まれていない場合_inPermissionメソッドはfalseを返す() {
		User user = new User(
				 new UserId(A_USER_ID_FOR_TEST)
				,A_USER_NAME_FOR_TEST
				,roles
				,Status.ACTIVE);

		assertFalse(user.inPermission(PermissionValue.writeMenu));
	}
}
