package com.jurin_n.domain.model.identity.user;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;

import com.jurin_n.domain.model.identity.Status;
import com.jurin_n.domain.model.identity.permission.Permission;
import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.identity.role.Role;
import com.jurin_n.domain.model.identity.role.RoleValue;
import com.jurin_n.junit.rules.JPAResource;

public class UserTest {
    private User sut;
	
    @Rule
    public JPAResource jpa = new JPAResource();
    
	@Test
	public void test() {
		Set<Permission> permissins = new HashSet<>();
		permissins.add(new Permission(PermissionValue.readPlan));
		permissins.add(new Permission(PermissionValue.writePlan));
		permissins.add(new Permission(PermissionValue.readMenu));
		permissins.add(new Permission(PermissionValue.writeMenu));
		
		Set<Role> roles = new HashSet<>();
		Role role = new Role(RoleValue.ADMIN,permissins);
		roles.add(role);

    	sut = new User(
    			 new UserId("user001")
    			,"テスト　太郎"
    			,roles
    			,Status.ACTIVE
    			);

		jpa.getEm().getTransaction().begin();
		jpa.getEm().persist(sut);
		jpa.getEm().getTransaction().commit();
	}

}
