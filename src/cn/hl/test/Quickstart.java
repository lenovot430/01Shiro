package cn.hl.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple Quickstart application showing how to use Shiro's API.
 *
 * @since 0.9 RC2
 */
public class Quickstart {

	private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);

	public static void main(String[] args) {

		// (file: and url: prefixes load from files and urls respectively):
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();

		SecurityUtils.setSecurityManager(securityManager);

		// Now that a simple Shiro environment is set up, let's see what you can do:

		// get the currently executing user:
		Subject currentUser = SecurityUtils.getSubject();

		// Do some stuff with a Session (no need for a web or EJB container!!!)
		Session session = currentUser.getSession();
		session.setAttribute("someKey", "aValue");
		String value = (String) session.getAttribute("someKey");
		if (value.equals("aValue")) {
			log.info("检索正确的值 [" + value + "]");
		}

		// let's login the current user so we can check against roles and permissions:
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken("guest", "guest");
			token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				log.info("这个用户名不存在 " + token.getPrincipal());
			} catch (IncorrectCredentialsException ice) {
				log.info("这个账号的密码 " + token.getPrincipal() + " 是错误");
			} catch (LockedAccountException lae) {
				log.info("用户名账户" + token.getPrincipal() + "被锁定.  "
						+ "请联系管理员解锁.");
			}
			// ... catch more exceptions here (maybe custom ones specific to your
			// application?
			catch (AuthenticationException ae) {
				// unexpected condition? error?
			}
		}

		// say who they are:
		// print their identifying principal (in this case, a username):
		log.info("用户 [" + currentUser.getPrincipal() + "] 登陆成功.");

		// test a role:
		if (currentUser.hasRole("schwartz")) {
			log.info("你拥有 Schwartz的角色!");
		} else {
			log.info("你好,游客.");
		}

		// test a typed permission (not instance-level)
		if (currentUser.isPermitted("lightsaber:weild")) {
			log.info("你拥有lightsaber功能的wisely权限.");
		} else {
			log.info("抱歉,你不具有lightsaber功能的wisely权限");
		}

		// a (very powerful) Instance Level permission:
		if (currentUser.isPermitted("winnebago:drive:eagle5")) {
			log.info("您具有winnebago功能的drive权限，权限的等级是'eagle5'.  "
					+ "这是钥匙，玩的开心!");
		} else {
			log.info("抱歉，您不具有winnebago功能的drive权限，权限的等级是'eagle5'");
		}

		// all done - log out!
		currentUser.logout();

		System.exit(0);
	}
}
