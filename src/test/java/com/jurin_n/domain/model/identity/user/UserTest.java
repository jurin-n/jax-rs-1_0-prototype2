package com.jurin_n.domain.model.identity.user;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.jurin_n.domain.model.identity.Status;
import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.identity.role.Role;
import com.jurin_n.domain.model.identity.role.RoleValue;

public class UserTest {

	private String A_USER_ID_FOR_TEST = "user001";
	private String A_USER_NAME_FOR_TEST = "テスト　太郎";

	@Test
	public void test_inRoleメソッドの引数に渡したRoleがUserに含まれてる場合_inRoleメソッドはtrueを返す() {
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setValue(RoleValue.ADMIN);
		roles.add(role);
		User user = new User(
				 new UserId(A_USER_ID_FOR_TEST)
				,A_USER_NAME_FOR_TEST
				,roles
				,Status.ACTIVE);
		
		assertTrue(user.inRole(RoleValue.ADMIN));
	}
	
	@Test
	public void test_inRoleメソッドの引数に渡したRoleがUserに含まれていない場合_inRoleメソッドはfalseを返す() {
//		assertThat(selectedUser.inRole(RoleValue.MEMBER), is(false));
		fail("未実装");
	}

	@Test
	public void test_inPermissionメソッドの引数に渡したPermissionがUserに含まれている場合_inPermissionメソッドはtrueを返す() {
		Role role = new Role();
		Set<PermissionValue> permissions = new HashSet<>();
		permissions.add(PermissionValue.readPlan);
		role.setPermissions(permissions);
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		User user = new User(
				 new UserId(A_USER_ID_FOR_TEST)
				,A_USER_NAME_FOR_TEST
				,roles
				,Status.ACTIVE);

		assertTrue(user.inPermission(PermissionValue.readPlan));
	}
	
	@Test
	public void test_inPermissionメソッドの引数に渡したPermissionがUserに含まれていない場合_inPermissionメソッドはfalseを返す() {
//		assertThat(selectedUser.inPermission(PermissionValue.writePlan), is(false));
		fail("未実装");
	}
}
