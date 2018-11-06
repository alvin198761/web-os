package org.alvin.opsdev.desktop.system.common;

import org.alvin.opsdev.desktop.system.common.acl.UserSessionSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;

public abstract class PrincipalController {

	@Autowired
	private Boolean isDev;

	public UserSessionSubject getSubject(Principal principal) {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
		return (UserSessionSubject) token.getPrincipal();
	}
}
