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

	//实例化Logger对象
	private static final transient Logger logger =LoggerFactory.getLogger(TestSingleRealm.class);

	public static void main(String[] args) {
		
		//实例化工厂对象
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:jdbc-shiro.ini");
		
		//实例化SecurityManager对象
		SecurityManager securityManager = factory.getInstance();
		
		//托管SecurityManager对象
		SecurityUtils.setSecurityManager(securityManager);
		
		//获取Subject对象
		Subject subject = SecurityUtils.getSubject();
		
		//通过Subject实现用户登陆验证
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
			System.out.println("用户登录成功");
		}
		
		//注销用户
		subject.logout();
		
	}

}
