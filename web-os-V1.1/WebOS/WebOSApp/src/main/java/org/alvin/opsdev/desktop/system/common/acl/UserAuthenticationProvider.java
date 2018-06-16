package org.alvin.opsdev.desktop.system.common.acl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * Created by Administrator on 2017/8/22.
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserSecurityService userSecurityService;

	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private Boolean isDev;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		String username = token.getName();
		// 从数据库找到的用户
		UserDetails userDetails = null;
		try {
			userDetails = userSecurityService.loadUserByUsername(username);
		} catch (UsernameNotFoundException ex) {
			logger.error(ex);
			ex.printStackTrace();
			throw ex;
		} catch (Exception ex) {
			logger.error(ex);
			ex.printStackTrace();
			throw new BadCredentialsException("内部错误，请联系管理员");
		}
		if (userDetails == null) {
			throw new UsernameNotFoundException("用户不存在");
		} else if (!userDetails.isEnabled()) {
			throw new DisabledException("用户已被禁用");
		} else if (!userDetails.isAccountNonExpired()) {
			throw new AccountExpiredException("账号已过期");
		} else if (!userDetails.isAccountNonLocked()) {
			throw new LockedException("账号已被锁定");
		} else if (!userDetails.isCredentialsNonExpired()) {
			throw new LockedException("凭证已过期");
		}
		if (!isDev) {
			// 数据库用户的密码
			String password = userDetails.getPassword();
			// 与authentication里面的credentials相比较
			if (!password.equals(token.getCredentials())) {
				throw new BadCredentialsException("用户名或密码错误");
			}
		}
		// 授权
		return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// 返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}
}