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
		
		//��ȡFactory����
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:permission-shiro.ini");
		
		//��ȡsecurityManager����
		SecurityManager securityManager =factory.getInstance();
		
		//�й�securityManager����
		SecurityUtils.setSecurityManager(securityManager);
		
		//��ȡSubject����
		Subject subject=SecurityUtils.getSubject();
		
		//ͨ��subjectִ�е�½��֤
		UsernamePasswordToken token = new UsernamePasswordToken("mike","123");
		
		try {
			subject.login(token);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		if(subject.isAuthenticated()) {
			//��ɫ��֤
			//hasRole()		:��֤�û��Ƿ����ĳһ�ֽ�ɫ
			Assert.assertTrue("Mike������role1��ɫ",subject.hasRole("role1"));
			
			//hasRoles()	:��֤�û��Ƿ���ж��ֽ�ɫ
			boolean[] results =subject.hasRoles(Arrays.asList("role1","role2"));
			
			//Assert.assertTrue("Mike����role1��ɫ",results[0]);
			//Assert.assertTrue("Mike������role3��ɫ",result[1]);
			
			//Ȩ����֤
			//isPermitted() : ��֤�û��Ƿ����Īһ��Ȩ��
			Assert.assertTrue("Mike������department:insertȨ��",subject.isPermitted("department:insert"));
			
			//isPermitted()	: ��֤�û��Ƿ���ж���Ȩ��
			boolean[] result2=subject.isPermitted("department:insert","department:delete");
			
			Assert.assertTrue("Mike������department:insert",result2[0]);
			Assert.assertTrue("Mike������department:delete",result2[1]);
			
		}else {
			System.out.println("��½ʧ��");
		}
		
		//��½��֤
		subject.logout();
	}
	
}
