package cn.hl.realms;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.shiro.authz.Permission;

public class PermissionOne implements Permission,Serializable{

	//Ȩ���ַ����ķָ���
	protected static final String PART_DIVIDER_TOKEN="\\+";
	//��Сд���
	protected static final boolean DEFAULT_CASE_SENSITIVE=false;
	
	//��������Ϣ
	private String contoller="";
	//ʵ��Id
	private String instanceld="";
	
	/**
	 * �޲ι��캯��
	 */
	public PermissionOne() {
		// TODO Auto-generated constructor stub
	}

	 /**
	  * ���ι��캯��
	  * @param permission
	  */
	public PermissionOne(String permission) {
		// TODO Auto-generated constructor stub
		this(permission,false);
	}
	
	/**
	 * ���ι��캯��
	 * @param permission
	 * @param caseSensitive
	 */
	public PermissionOne(String permission,boolean caseSensitive) {
		// TODO Auto-generated constructor stub
		this.setPart(permission, caseSensitive);
	}
	
	private void setPart(String permission,boolean caseSensitive) {
		
		//�ж�permission�ַ����Ƿ�Ϊ��
		if(StringUtils.isBlank(permission)) {
			permission="";
		}
		
		//���caseSensitiveΪtrue���ַ�������ȫ��תΪСд
		if(caseSensitive) {
			permission=permission.toLowerCase();
		}
		
		//���ַ������в��
		String[] arr=permission.split(PART_DIVIDER_TOKEN);
		
		//�жϲ�ֽ���Ƿ���Ч�������Ч��ֵ����Ӧ�ı���
		if(arr!=null&&arr.length==2) {
			this.contoller=arr[0];
			this.instanceld=arr[1];
		}
	}
	
	
	/**
	 * ��֤��ǰPermission�����Ƿ���Խ��д���
	 * @param arg0
	 * @return
	 */
	@Override
	public boolean implies(Permission p) {
		// TODO Auto-generated method stub
		
		//��֤Permission�����Ƿ������Զ�������
		if(!(p instanceof PermissionOne)) {
			return false;
		}
		
		//��Permission����ǿ��תΪPermissionOne����
		PermissionOne other=(PermissionOne)p;
		
		//���p�ĳ�Ա�����뵱ǰ�����Ա������ͬ����Ϊ���Խ��д���
		if(this.contoller.equals(other.contoller)&&this.instanceld.equals(other.instanceld)) {
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		String str =ReflectionToStringBuilder.toString(this);
		
		System.out.println("str="+str);
		
		return str;
	}
	

}
