package org.alvin.opsdev.desktop.config;

import org.alvin.opsdev.desktop.system.common.acl.UserAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/8/22.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserAuthenticationProvider userAuthenticationProvider;

	private Boolean enabled = true;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (!enabled) {
			http.authorizeRequests()
//                    .antMatchers("/**").fullyAuthenticated()
					.antMatchers("/**").permitAll()
					.and().csrf().disable();
		} else {
			http.authorizeRequests()
					//放过的url
					.antMatchers("/login", "/j_spring_security_check", "gucamole").permitAll()
					.antMatchers("/api/loginValidate").permitAll()
					.antMatchers("/js/**", "img/**", "css/**", "component/**").permitAll()
					.antMatchers("/code", "/check").permitAll()
					.antMatchers("/resources/**").permitAll()
					.antMatchers("/application/**").permitAll()
					.antMatchers("/static/favicon.ico").permitAll()

					.anyRequest().anonymous()
					.and().httpBasic()
					//登录页面设置
					.and().formLogin().loginPage("/login")
					.failureUrl("/login?error")
					.loginProcessingUrl("/j_spring_security_check")
					.defaultSuccessUrl("/")
					.usernameParameter("j_username") // 用户名参数
					.passwordParameter("j_password") // 密码参数;
					.and()
					.logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login") //退出登录
					.and()
					.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint("/login")) //入口点
					.and()
					.csrf().disable()
					.headers().frameOptions().disable();
			//
			UsernamePasswordAuthenticationFilter filter = this.usernamePasswordAuthenticationFilter();
			filter.setAuthenticationManager(super.authenticationManager());
			http.addFilter(filter);
			super.configure(http);
		}
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userAuthenticationProvider);
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	private LoginUrlAuthenticationEntryPoint authenticationEntryPoint(String url) {
		return new LoginUrlAuthenticationEntryPoint(url) {

			public void commence(HttpServletRequest request, HttpServletResponse response,
								 AuthenticationException authException) throws IOException, ServletException {
				if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
				} else {
					super.commence(request, response, authException);
				}
			}
		};
	}

	private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() {
		UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter() {
			public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
					throws AuthenticationException {
				//  String codeInSession = SessionUtils.getSession(request).getCode();
//                Object codeObj = request.getParameter("code");
//                if (codeObj == null || codeInSession == null) {
//                    request.setAttribute("j_username", request.getParameter("j_username"));
//                    request.setAttribute("j_password", request.getParameter("j_password"));
//                    throw new BadCredentialsException("验证码为空");
//                }
//                if (!codeInSession.equalsIgnoreCase(codeObj.toString())) {
//                    request.setAttribute("j_username", request.getParameter("j_username"));
//                    request.setAttribute("j_password", request.getParameter("j_password"));
//                    throw new BadCredentialsException("验证码不匹配");
//                }
				return super.attemptAuthentication(request, response);
			}
		};

		return filter;
	}
}
