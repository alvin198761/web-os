package org.alvin.opsdev.desktop.system.common;

import org.alvin.opsdev.desktop.system.common.acl.UserSessionSubject;
import org.alvin.opsdev.webos.commom.app.users.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class IndexCotroller extends PrincipalController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/guacamole")
	public String guacamole() {
		return "guacamole";
	}

	@RequestMapping("/settings")
	public String settings() {
		return "settings";
	}

	@RequestMapping("/browser")
	public String browser() {
		return "browser";
	}

	@ResponseBody
	@RequestMapping("/api/session/info")
	public Users sessionInfo(Principal principal) {
		UserSessionSubject userSessionSubject = getSubject(principal);
		Users user = userSessionSubject.getUser();
		user.setTheme(0);
		return user;
	}

	@ResponseBody
	@RequestMapping("/api/session/heartbeat")
	public Long heartbeat() {
		return System.currentTimeMillis();
	}
}
