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
		//获取Factory工厂类对象
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-config.ini");
		
		//通过工厂类获取SecurityManager对象
		SecurityManager securityManager =factory.getInstance();
		
		//将SecurityManager托管到SecurityUtils进行管理
		SecurityUtils.setSecurityManager(securityManager);
		
		//获取Subject对象
		Subject subject=SecurityUtils.getSubject();
		
		//通过Subject进行验证
		UsernamePasswordToken token=new UsernamePasswordToken("admin", "123");
		//UsernamePasswordToken token=new UsernamePasswordToken("admin@163.com", "123123");
		try {
			subject.login(token);
		}
		catch(UnknownAccountException ex){
			System.out.println("账号不存在");
		}
		catch(IncorrectCredentialsException ex) {
			System.out.println("密码错误");
		}
		
		if(subject.isAuthenticated()) {
			System.out.println("用户登录成功");
		}
		
		//用户注销
		subject.logout();
		
	}
	
}
