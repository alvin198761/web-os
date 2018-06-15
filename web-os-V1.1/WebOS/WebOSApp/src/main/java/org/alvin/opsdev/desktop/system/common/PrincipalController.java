package org.alvin.opsdev.desktop.system.common;

import org.alvin.opsdev.desktop.system.common.acl.SessionUserSubject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

public abstract class PrincipalController {

    @Autowired
    private Boolean isDev;

    public SessionUserSubject getSubject(Principal principal) {
        if (isDev) {
            SessionUserSubject userSubject = new SessionUserSubject();
            userSubject.setId(0L);
            return userSubject;
        }
        return null;
    }
}
