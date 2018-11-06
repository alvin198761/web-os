package org.alvin.opsdev.webos.commom.app.users;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.alvin.opsdev.webos.commom.app.utils.BaseCondition;

/**
 * @类说明:用户信息查询条件实体类
 * @author:唐植超
 * @date:2018-05-12 16:51:08
 **/
@Setter
@Getter
public class UsersCond extends BaseCondition {

	/**
	 * @方法说明:拼加自定义条件;可添加新条件、属性字段,删除不用的条件、属性字段
	 **/
	@Override
	public void addCondition() {
		add(id, "AND t.id = ?");
		add(login_name, "AND t.login_name LIKE ?", 3);
		add(nick_name, "AND t.nick_name LIKE ?", 3);
		add(password, "AND t.password LIKE ?", 3);
		add(auth, "AND t.auth = ?");
		add(create_time, "AND t.create_time = ?");
		add(author, "AND t.author = ?");
		add(status, "AND t.status = ?");
		//
		add(login_name_eq, "AND t.login_name = ?");
	}

	//页面查询条件的ID名称、查询条可以自行增减、把不用条件清理掉
	private Long id;// 主键
	private String login_name;// 登录名称
	private String nick_name;// 昵称
	private String password;// 密码
	private Byte auth;// 身份
	private Date create_time;// 创建时间
	private Long author;// 创建人
	private Byte status;// 状态
	//登录用，必须是等于
	private String login_name_eq;

}