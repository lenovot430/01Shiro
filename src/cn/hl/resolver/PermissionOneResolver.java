package cn.hl.resolver;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import cn.hl.realms.PermissionOne;

public class PermissionOneResolver implements PermissionResolver{

	@Override
	public Permission resolvePermission(String permissionString) {
		
		if(permissionString.indexOf("+")==-1) {
			return new WildcardPermission(permissionString);
		}else {
			return new PermissionOne(permissionString);
		}
		
	}

}
