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
		//获取Factory工厂
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:customer-shiro.ini");
	
		//获取SecurityManager对象
		SecurityManager securityManager=factory.getInstance();
		
		//托管SecurityManager对象
		SecurityUtils.setSecurityManager(securityManager);
		
		//获取Subject对象
		Subject subject=SecurityUtils.getSubject();
		
		//通过Subject执行登陆验证
		UsernamePasswordToken token = new UsernamePasswordToken("Mike","123");
		
		try {
			subject.login(token);
		}
		catch(AuthenticationException ex){
			System.out.println(ex.getMessage());
		}
		
		if(subject.isAuthenticated()) {
			System.out.println("登陆成功");
			System.out.println("user:insert= "+subject.isPermitted("user:insert"));
			System.out.println("user:select="+subject.isPermitted("user:select"));
		}
		else {
			System.out.println("登陆失败");
		}
		
		//注销登陆
		subject.logout();
	}
}
