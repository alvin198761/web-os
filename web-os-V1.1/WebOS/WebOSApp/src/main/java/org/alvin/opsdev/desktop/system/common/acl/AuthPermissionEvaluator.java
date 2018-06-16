package org.alvin.opsdev.desktop.system.common.acl;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/22.
 */
@Component
public class AuthPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        @SuppressWarnings("unused")
		UserSessionSubject userSessionSubject = (UserSessionSubject) authentication.getPrincipal();
//        System.out.println("----");
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
//        System.out.println("11111");
        return false;
    }
}
