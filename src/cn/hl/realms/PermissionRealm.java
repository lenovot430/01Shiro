package cn.hl.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class PermissionRealm extends AuthorizingRealm {

	//获取授权信息
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
		// TODO Auto-generated method stub
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		
		//向授权信息中添加角色
		info.addRole("admin");
		info.addRole("guest");
		
		//向授权信息中添加权限
		info.addStringPermission("user:insert");
		info.addStringPermission("user:update");
		
		
		return info;
	}

	//获取验证信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		//获取账号信息
		String account=String.valueOf(token.getPrincipal());
		
		//获取密码信息
		String pwd= String.valueOf((char[])token.getCredentials());
		
		
		return new SimpleAuthenticationInfo(account, pwd,this.getClass().getName());
	}

}
