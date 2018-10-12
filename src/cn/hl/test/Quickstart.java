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
			log.info("������ȷ��ֵ [" + value + "]");
		}

		// let's login the current user so we can check against roles and permissions:
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken("guest", "guest");
			token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				log.info("����û��������� " + token.getPrincipal());
			} catch (IncorrectCredentialsException ice) {
				log.info("����˺ŵ����� " + token.getPrincipal() + " �Ǵ���");
			} catch (LockedAccountException lae) {
				log.info("�û����˻�" + token.getPrincipal() + "������.  "
						+ "����ϵ����Ա����.");
			}
			// ... catch more exceptions here (maybe custom ones specific to your
			// application?
			catch (AuthenticationException ae) {
				// unexpected condition? error?
			}
		}

		// say who they are:
		// print their identifying principal (in this case, a username):
		log.info("�û� [" + currentUser.getPrincipal() + "] ��½�ɹ�.");

		// test a role:
		if (currentUser.hasRole("schwartz")) {
			log.info("��ӵ�� Schwartz�Ľ�ɫ!");
		} else {
			log.info("���,�ο�.");
		}

		// test a typed permission (not instance-level)
		if (currentUser.isPermitted("lightsaber:weild")) {
			log.info("��ӵ��lightsaber���ܵ�wiselyȨ��.");
		} else {
			log.info("��Ǹ,�㲻����lightsaber���ܵ�wiselyȨ��");
		}

		// a (very powerful) Instance Level permission:
		if (currentUser.isPermitted("winnebago:drive:eagle5")) {
			log.info("������winnebago���ܵ�driveȨ�ޣ�Ȩ�޵ĵȼ���'eagle5'.  "
					+ "����Կ�ף���Ŀ���!");
		} else {
			log.info("��Ǹ����������winnebago���ܵ�driveȨ�ޣ�Ȩ�޵ĵȼ���'eagle5'");
		}

		// all done - log out!
		currentUser.logout();

		System.exit(0);
	}
}
