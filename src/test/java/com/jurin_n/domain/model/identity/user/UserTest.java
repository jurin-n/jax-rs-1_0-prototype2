package com.jurin_n.domain.model.identity.user;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;

import com.jurin_n.domain.model.identity.Status;
import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.identity.role.Role;
import com.jurin_n.domain.model.identity.role.RoleValue;
import com.jurin_n.junit.rules.JPAResource;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.CoreMatchers.nullValue;
public class UserTest {

	@ClassRule
	public static JPAResource jpa = new JPAResource();

	public static void executeNativeSQL(String scriptPath,String characterSet) throws IOException{
		//sqlを読み込む
		Path path = Paths.get(scriptPath);
		List<String> lines 
			= Files.readAllLines(path, Charset.forName(characterSet)); //Shift-JISの場合は、MS932 指定？
		if(lines.size() != 0){
			jpa.getEm().getTransaction().begin();
			for(String line : lines){
				if(line.trim().length() == 0 ||line.startsWith("#") ){
					//実行なし
				}else{
					//実行
					Query q = jpa.getEm().createNativeQuery(line);
					q.executeUpdate();
				}
			}
			jpa.getEm().getTransaction().commit();
		}
	}
	@BeforeClass
	public static void setUpClass() throws IOException{
		executeNativeSQL("./src/test/resources/setupScript.sql","UTF-8");
	}
	
	//@AfterClass //t_userとの参照整合の関係でdeleteできず
	public static void tearDownClass() throws IOException{
		executeNativeSQL("./src/test/resources/tearDownScript.sql","UTF-8");
	}
	
	@Ignore
	@Test
	public void test_Roleクリア() {
		jpa.getEm().getTransaction().begin();
		Query q = jpa.getEm().createQuery("DELETE FROM Role c");
		q.executeUpdate();
		jpa.getEm().flush();
		jpa.getEm().getTransaction().commit();
	}

	@Ignore
	//@Test
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
	
	//@Ignore
	@Test
	public void test_UserにRole付与() {
		Role role = jpa.getEm().find(Role.class, "role102");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		User user = new User(
				 new UserId("user002")
				,"テスト　二郎"
				,roles
				,Status.ACTIVE);
		jpa.getEm().getTransaction().begin();
		jpa.getEm().persist(user);
		jpa.getEm().getTransaction().commit(); 
	}
	
	@Test
//	public void test_UserにADMINのRoleが含まれることを確認() {
	public void test_inRoleメソッドの引数に渡したRoleがUserに含まれてる場合_inRoleメソッドはtrueを返す() {
		/* セットアップ：ADMINロールを保持してるユーザ作成 */
		Role role = jpa.getEm().find(Role.class, "role101");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		User user = new User(
				 new UserId("user003")
				,"テスト　アド民"
				,roles
				,Status.ACTIVE);
		jpa.getEm().getTransaction().begin();
		jpa.getEm().persist(user);
		jpa.getEm().getTransaction().commit();
		
		//ADMINロール保持してるユーザ検索
		User selectedUser = jpa.getEm().find(User.class, new UserId("user003"));
		assertThat(selectedUser, is(not(nullValue())));

		/* アサーション */
		//セットアップしたユーザであるかチェック
		assertThat(selectedUser.getUserid(), is(user.getUserid()));
		assertThat(selectedUser.getRoles(), is(user.getRoles()));
		//ADMINロール保持してるかチェック
		assertThat(selectedUser.inRole(RoleValue.ADMIN), is(true));
		//MEMBERロール保持してないことチェック
		assertThat(selectedUser.inRole(RoleValue.MEMBER), is(false));
	}
	
	@Test
	public void test_inRoleメソッドの引数に渡したRoleがUserに含まれていない場合_inRoleメソッドはfalseを返す() {
		fail("未実装");
	}

	@Test
	//public void test_MEMBERロールを保持するUserにreadPlanのパーミッションが含まれることを確認() {
	public void test_inPermissionメソッドの引数に渡したPermissionがUserに含まれている場合_inPermissionメソッドはtrueを返す() {
		/* セットアップ：MEMBERロールを保持してるユーザ作成 */
		Role role = jpa.getEm().find(Role.class, "role102");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		User user = new User(
				 new UserId("user004")
				,"テスト　メンバー"
				,roles
				,Status.ACTIVE);
		jpa.getEm().getTransaction().begin();
		jpa.getEm().persist(user);
		jpa.getEm().getTransaction().commit();
		
		//MEMBERロール保持してるユーザ検索
		User selectedUser = jpa.getEm().find(User.class, new UserId("user004"));
		assertThat(selectedUser, is(not(nullValue())));

		/* アサーション */
		//セットアップしたユーザであるかチェック
		assertThat(selectedUser.getUserid(), is(user.getUserid()));
		assertThat(selectedUser.getRoles(), is(user.getRoles()));
		//ADMINロール保持してるかチェック
		assertThat(selectedUser.inRole(RoleValue.MEMBER), is(true));
		//MEMBERロール保持してないことチェック
		assertThat(selectedUser.inRole(RoleValue.ADMIN), is(false));
		
		//readPlanのパーミッション含まれることチェック
		assertThat(selectedUser.inPermission(PermissionValue.readPlan), is(true));

		//writePlanのパーミッション含まれてないことチェック
		assertThat(selectedUser.inPermission(PermissionValue.writePlan), is(false));
	}
	
	@Test
	public void test_inPermissionメソッドの引数に渡したPermissionがUserに含まれていない場合_inPermissionメソッドはfalseを返す() {
		fail("未実装");
	}
}
