package cn.hl.test;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.Assert;

public class TestSubject {

	private static final transient Logger logger=LoggerFactory.getLogger(TestSubject.class);
	
	public static void main(String[] args) {
		
		//获取Factory对象
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:permission-shiro.ini");
		
		//获取securityManager对象
		SecurityManager securityManager =factory.getInstance();
		
		//托管securityManager对象
		SecurityUtils.setSecurityManager(securityManager);
		
		//获取Subject对象
		Subject subject=SecurityUtils.getSubject();
		
		//通过subject执行登陆验证
		UsernamePasswordToken token = new UsernamePasswordToken("mike","123");
		
		try {
			subject.login(token);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		if(subject.isAuthenticated()) {
			//角色验证
			//hasRole()		:验证用户是否具有某一种角色
			Assert.assertTrue("Mike不具有role1角色",subject.hasRole("role1"));
			
			//hasRoles()	:验证用户是否具有多种角色
			boolean[] results =subject.hasRoles(Arrays.asList("role1","role2"));
			
			//Assert.assertTrue("Mike不具role1角色",results[0]);
			//Assert.assertTrue("Mike不具有role3角色",result[1]);
			
			//权限验证
			//isPermitted() : 验证用户是否具有莫一种权限
			Assert.assertTrue("Mike不具有department:insert权限",subject.isPermitted("department:insert"));
			
			//isPermitted()	: 验证用户是否具有多种权限
			boolean[] result2=subject.isPermitted("department:insert","department:delete");
			
			Assert.assertTrue("Mike不具有department:insert",result2[0]);
			Assert.assertTrue("Mike不具有department:delete",result2[1]);
			
		}else {
			System.out.println("登陆失败");
		}
		
		//登陆验证
		subject.logout();
	}
	
}
