package cn.hl.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCustomer {

	private static final Logger logger=LoggerFactory.getLogger(TestCustomer.class);
	
	public static void main(String[] args) {
		//��ȡFactory����
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:customer-shiro.ini");
	
		//��ȡSecurityManager����
		SecurityManager securityManager=factory.getInstance();
		
		//�й�SecurityManager����
		SecurityUtils.setSecurityManager(securityManager);
		
		//��ȡSubject����
		Subject subject=SecurityUtils.getSubject();
		
		//ͨ��Subjectִ�е�½��֤
		UsernamePasswordToken token = new UsernamePasswordToken("Mike","123");
		
		try {
			subject.login(token);
		}
		catch(AuthenticationException ex){
			System.out.println(ex.getMessage());
		}
		
		if(subject.isAuthenticated()) {
			System.out.println("��½�ɹ�");
			System.out.println("user:insert= "+subject.isPermitted("user:insert"));
			System.out.println("user:select="+subject.isPermitted("user:select"));
		}
		else {
			System.out.println("��½ʧ��");
		}
		
		//ע����½
		subject.logout();
	}
}
