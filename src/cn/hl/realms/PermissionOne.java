package cn.hl.realms;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.shiro.authz.Permission;

public class PermissionOne implements Permission,Serializable{

	//权限字符串的分隔符
	protected static final String PART_DIVIDER_TOKEN="\\+";
	//大小写标记
	protected static final boolean DEFAULT_CASE_SENSITIVE=false;
	
	//控制器信息
	private String contoller="";
	//实例Id
	private String instanceld="";
	
	/**
	 * 无参构造函数
	 */
	public PermissionOne() {
		// TODO Auto-generated constructor stub
	}

	 /**
	  * 带参构造函数
	  * @param permission
	  */
	public PermissionOne(String permission) {
		// TODO Auto-generated constructor stub
		this(permission,false);
	}
	
	/**
	 * 带参构造函数
	 * @param permission
	 * @param caseSensitive
	 */
	public PermissionOne(String permission,boolean caseSensitive) {
		// TODO Auto-generated constructor stub
		this.setPart(permission, caseSensitive);
	}
	
	private void setPart(String permission,boolean caseSensitive) {
		
		//判断permission字符串是否为空
		if(StringUtils.isBlank(permission)) {
			permission="";
		}
		
		//如果caseSensitive为true则将字符串内容全部转为小写
		if(caseSensitive) {
			permission=permission.toLowerCase();
		}
		
		//对字符串进行拆分
		String[] arr=permission.split(PART_DIVIDER_TOKEN);
		
		//判断拆分结果是否有效，如果有效则赋值给对应的变量
		if(arr!=null&&arr.length==2) {
			this.contoller=arr[0];
			this.instanceld=arr[1];
		}
	}
	
	
	/**
	 * 验证当前Permission对象是否可以进行处理
	 * @param arg0
	 * @return
	 */
	@Override
	public boolean implies(Permission p) {
		// TODO Auto-generated method stub
		
		//验证Permission对象是否是有自定义类型
		if(!(p instanceof PermissionOne)) {
			return false;
		}
		
		//将Permission对象强制转为PermissionOne类型
		PermissionOne other=(PermissionOne)p;
		
		//如果p的成员数据与当前对象成员数据相同则认为可以进行处理
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
