package cn.hl.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class TestJdbcRealm {

	//ʵ����Logger����
	private static final transient Logger logger =LoggerFactory.getLogger(TestSingleRealm.class);

	public static void main(String[] args) {
		
		//ʵ������������
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:jdbc-shiro.ini");
		
		//ʵ����SecurityManager����
		SecurityManager securityManager = factory.getInstance();
		
		//�й�SecurityManager����
		SecurityUtils.setSecurityManager(securityManager);
		
		//��ȡSubject����
		Subject subject = SecurityUtils.getSubject();
		
		//ͨ��Subjectʵ���û���½��֤
		UsernamePasswordToken token = new UsernamePasswordToken("101","123");
		
		try {
			subject.login(token);
		}
		catch(UnknownAccountException ex){
			ex.printStackTrace();
		}
		catch(IncorrectCredentialsException ex) {
			ex.printStackTrace();
		}
		
		if(subject.isAuthenticated()) {
			System.out.println("�û���¼�ɹ�");
		}
		
		//ע���û�
		subject.logout();
		
	}

}
