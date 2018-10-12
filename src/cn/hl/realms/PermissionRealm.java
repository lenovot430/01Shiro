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

	//��ȡ��Ȩ��Ϣ
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
		// TODO Auto-generated method stub
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		
		//����Ȩ��Ϣ����ӽ�ɫ
		info.addRole("admin");
		info.addRole("guest");
		
		//����Ȩ��Ϣ�����Ȩ��
		info.addStringPermission("user:insert");
		info.addStringPermission("user:update");
		
		
		return info;
	}

	//��ȡ��֤��Ϣ
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		//��ȡ�˺���Ϣ
		String account=String.valueOf(token.getPrincipal());
		
		//��ȡ������Ϣ
		String pwd= String.valueOf((char[])token.getCredentials());
		
		
		return new SimpleAuthenticationInfo(account, pwd,this.getClass().getName());
	}

}
