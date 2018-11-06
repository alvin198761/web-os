package org.alvin.opsdev.desktop.config;

import org.alvin.opsdev.desktop.system.common.acl.AuthPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * Created by Administrator on 2017/8/22.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableGlobalAuthentication
public class GlobalSecurityConfig extends GlobalMethodSecurityConfiguration {

	@Autowired
	private AuthPermissionEvaluator authPermissionEvaluator;

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		DefaultMethodSecurityExpressionHandler handler = (DefaultMethodSecurityExpressionHandler) super.createExpressionHandler();
		handler.setPermissionEvaluator(authPermissionEvaluator);
		return handler;
	}
}
