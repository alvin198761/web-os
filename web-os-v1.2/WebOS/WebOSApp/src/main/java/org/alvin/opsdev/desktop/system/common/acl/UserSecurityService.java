package org.alvin.opsdev.desktop.system.common.acl;

import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import org.alvin.opsdev.desktop.system.appicons.AppIconsService;
import org.alvin.opsdev.desktop.system.dict.DictService;
import org.alvin.opsdev.desktop.system.users.UsersService;
import org.alvin.opsdev.webos.commom.ConstCode;
import org.alvin.opsdev.webos.commom.app.appicons.AppIcons;
import org.alvin.opsdev.webos.commom.app.appicons.AppIconsCond;
import org.alvin.opsdev.webos.commom.app.dict.Dict;
import org.alvin.opsdev.webos.commom.app.dict.DictCond;
import org.alvin.opsdev.webos.commom.app.users.Users;
import org.alvin.opsdev.webos.commom.app.users.UsersCond;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 登录时加载数据
 */
@Component
public class UserSecurityService implements UserDetailsService {


	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private Boolean isDev;
	@Autowired
	private UsersService usersService;
	@Autowired
	private AppIconsService appIconsService;
	@Autowired
	private DictService dictService;


	@Override
	public UserDetails loadUserByUsername(String loginName) {
		GrantedAuthority grantedAuthority = null;
		Users osUser = null;
		if (isDev) {
			grantedAuthority = new SimpleGrantedAuthority("ADMIN");
			osUser = new Users();
			osUser.setId(0L);
			osUser.setLogin_name(loginName);
			osUser.setNick_name("超级管理员");
			osUser.setPassword(Hashing.md5().hashString("admin", Charset.forName("utf-8")).toString());
		} else {
			grantedAuthority = new SimpleGrantedAuthority("USER");
			//获取用户信息
			UsersCond usersCond = new UsersCond();
			usersCond.setLogin_name(loginName);
			List<Users> users = this.usersService.queryList(usersCond);
			if (users.isEmpty()) {
				return null;
			}
			osUser = users.get(0);
			if (osUser == null) {
				return null;
			}
		}

		AppIconsCond appIconsCond = new AppIconsCond();
		appIconsCond.setPublish_id(osUser.getId());
		appIconsCond.setStatus(ConstCode.STAUS_ENABLED);
		Map<Byte, List<AppIcons>> appsMap = appIconsService.queryList(appIconsCond).parallelStream().collect(Collectors.groupingBy(AppIcons::getType));

		//获取用户配置信息
		DictCond dictCond = new DictCond();
		dictCond.setType_code(Byte.valueOf(ConstCode.DICT_CUSTOM_KEY).longValue());
		Map<Long, List<Dict>> dictMap = dictService.queryList(dictCond).parallelStream().collect(Collectors.groupingBy(Dict::getData_key));
		//封装用户资料
		return new UserSessionSubject(osUser, Lists.newArrayList(grantedAuthority), appsMap, dictMap);
	}


}
