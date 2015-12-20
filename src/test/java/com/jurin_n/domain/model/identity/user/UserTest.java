package com.jurin_n.domain.model.identity.user;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;

import org.junit.Rule;
import org.junit.Test;

import com.jurin_n.domain.model.identity.Status;
import com.jurin_n.domain.model.identity.permission.Permission;
import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.identity.role.Role;
import com.jurin_n.domain.model.identity.role.RoleValue;
import com.jurin_n.junit.rules.JPAResource;
import static org.hamcrest.core.Is.is;

public class UserTest {
    private User sut;
	
    @Rule
    public JPAResource jpa = new JPAResource();

    //@Test
	public void test_Roleクリア() {
		jpa.getEm().getTransaction().begin();
		Query q = jpa.getEm().createQuery("DELETE FROM Role c");
		q.executeUpdate();
		jpa.getEm().flush();
		jpa.getEm().getTransaction().commit();
	}
    
	@Test
	public void test_RoleとPermissions作成() {
		Set<PermissionValue> permissins = new HashSet<>();
		permissins.add(PermissionValue.readPlan);
		permissins.add(PermissionValue.readMenu);
		permissins.add(PermissionValue.readMember);
		permissins.add(PermissionValue.readRecord);
		permissins.add(PermissionValue.writePlan);
		permissins.add(PermissionValue.writeMenu);
		permissins.add(PermissionValue.writeMember);
		permissins.add(PermissionValue.writeRecord);
		Role role = new Role("role101",RoleValue.ADMIN,permissins);
		
		Set<PermissionValue> permissins2 = new HashSet<>();
		permissins2.add(PermissionValue.readPlan);
		permissins2.add(PermissionValue.readMenu);
		permissins2.add(PermissionValue.readMember);
		permissins2.add(PermissionValue.readRecord);
		Role role2 = new Role("role102",RoleValue.MEMBER,permissins2);
		jpa.getEm().getTransaction().begin();
		jpa.getEm().persist(role);
		jpa.getEm().persist(role2);
		jpa.getEm().getTransaction().commit(); 	

		Set<Role> roles = new HashSet<>();
		roles.add(role);
		roles.add(role2);
		
		User user = new User(
				 new UserId("user001")
				,"テスト　太郎"
				,roles
				,Status.ACTIVE);
		jpa.getEm().getTransaction().begin();
		jpa.getEm().persist(user);
		jpa.getEm().getTransaction().commit(); 
		
		User selectedUser = jpa.getEm().find(User.class,new UserId("user001"));
		Set<Role> usersRole = selectedUser.getRoles();
		for(Role r: usersRole){
			Set<PermissionValue> rolesPermisions = r.getPermissions();
			System.out.println("usersRole=" + r.getValue());
			if(rolesPermisions.contains(PermissionValue.writePlan)){
				System.out.println("プラン編集できます！！");
			}
			for(PermissionValue p :rolesPermisions){
				System.out.println("rolesPermisions=" + p.toString());
			}
		}
	}

}
