package cn.hl.test;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSingleRealm {

	private static final transient Logger logger=LoggerFactory.getLogger(TestSingleRealm.class);
	
	public static void main(String[] args) {
		//��ȡFactory���������
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-config.ini");
		
		//ͨ���������ȡSecurityManager����
		SecurityManager securityManager =factory.getInstance();
		
		//��SecurityManager�йܵ�SecurityUtils���й���
		SecurityUtils.setSecurityManager(securityManager);
		
		//��ȡSubject����
		Subject subject=SecurityUtils.getSubject();
		
		//ͨ��Subject������֤
		UsernamePasswordToken token=new UsernamePasswordToken("admin", "123");
		//UsernamePasswordToken token=new UsernamePasswordToken("admin@163.com", "123123");
		try {
			subject.login(token);
		}
		catch(UnknownAccountException ex){
			System.out.println("�˺Ų�����");
		}
		catch(IncorrectCredentialsException ex) {
			System.out.println("�������");
		}
		
		if(subject.isAuthenticated()) {
			System.out.println("�û���¼�ɹ�");
		}
		
		//�û�ע��
		subject.logout();
		
	}
	
}
