package org.alvin.opsdev.desktop.system.common.acl;

import lombok.Getter;
import org.alvin.opsdev.webos.commom.ConstCode;
import org.alvin.opsdev.webos.commom.app.appicons.AppIcons;
import org.alvin.opsdev.webos.commom.app.dict.Dict;
import org.alvin.opsdev.webos.commom.app.users.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 权限数据
 */
@Getter
public class UserSessionSubject extends User {

	private static final long serialVersionUID = 1L;
	//用户信息
	private Users user;
	//属于该用户的菜单
	private Map<Byte, List<AppIcons>> allAppMap;
	//用户配置
	private Map<Long, List<Dict>> userConfig;

	public UserSessionSubject(Users user, Collection<? extends GrantedAuthority> authorities, Map<Byte, List<AppIcons>> allAppMap, Map<Long, List<Dict>> userConfig) {
		super(user.getLogin_name(), user.getPassword(), authorities);
		this.user = user;
		this.allAppMap = allAppMap;
		this.userConfig = userConfig;
	}

	/**
	 * 桌面按钮
	 *
	 * @return
	 */
	public List<AppIcons> getDesktopApps() {
		return Collections.unmodifiableList(this.allAppMap.get(ConstCode.APP_TYPE_DESKTOP));
	}

	/**
	 * 启动按钮
	 *
	 * @return
	 */
	public List<AppIcons> getStartMenuApps() {
		return Collections.unmodifiableList(this.allAppMap.get(ConstCode.APP_TYPE_START_MENU));
	}

	/**
	 * 参数配置项目
	 *
	 * @return
	 */
	public Map<Long, List<Dict>> configMap() {
		return Collections.unmodifiableMap(this.configMap());
	}
}
