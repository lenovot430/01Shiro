package cn.hl.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class RealmOne implements Realm{

	/**
	 * 执行登陆验证
	 */
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//获取用户名
		String principal=String.valueOf(token.getPrincipal());
		System.out.println("用户名："+principal);
		
		//获取密码
		String credentials=String.valueOf((char[])token.getCredentials());
		System.out.println("密码："+credentials);
		
		//验证用户名
		if(!"admin".equals(principal)) {
			throw new UnknownAccountException("用户名不存在");
		}
		
		if(!"123".equals(credentials)) {
			throw new IncorrectCredentialsException("密码错误");
		}
		
		return new SimpleAuthenticationInfo(principal, credentials, this.getName());
	}

	/**
	 * 获取基本类名
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		System.out.println("calssName"+this.getClass().getName());
		
		return this.getClass().getName();	
	}

	/**
	 * 判断token是否被支持
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		// TODO Auto-generated method stub
		return token instanceof UsernamePasswordToken;
	}

}
