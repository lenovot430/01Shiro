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
	 * ִ�е�½��֤
	 */
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//��ȡ�û���
		String principal=String.valueOf(token.getPrincipal());
		System.out.println("�û�����"+principal);
		
		//��ȡ����
		String credentials=String.valueOf((char[])token.getCredentials());
		System.out.println("���룺"+credentials);
		
		//��֤�û���
		if(!"admin".equals(principal)) {
			throw new UnknownAccountException("�û���������");
		}
		
		if(!"123".equals(credentials)) {
			throw new IncorrectCredentialsException("�������");
		}
		
		return new SimpleAuthenticationInfo(principal, credentials, this.getName());
	}

	/**
	 * ��ȡ��������
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		System.out.println("calssName"+this.getClass().getName());
		
		return this.getClass().getName();	
	}

	/**
	 * �ж�token�Ƿ�֧��
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		// TODO Auto-generated method stub
		return token instanceof UsernamePasswordToken;
	}

}
