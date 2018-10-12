package cn.hl.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class RealmTwo implements Realm{

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		//获取账号信息
		String principal=String.valueOf(token.getPrincipal());
		
		//获取密码
		String credential=String.valueOf((char[])token.getCredentials());
		
		//通过邮件地址进行验证
		if(!"admin@163.com".equals(principal)) {
			throw new UnknownAccountException("账号信息不存在");
		}
		
		if(!"123123".equals(credential)) {
			throw new IncorrectCredentialsException("密码错误");
		}
		
		
		return new SimpleAuthenticationInfo(principal, credential, this.getName());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		// TODO Auto-generated method stub
		return token instanceof UsernamePasswordToken;
	}

}
