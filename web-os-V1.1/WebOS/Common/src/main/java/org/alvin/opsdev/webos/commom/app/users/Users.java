package org.alvin.opsdev.webos.commom.app.users;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * @类说明:用户信息实体类
 * @author:唐植超
 * @date:2018-05-12 16:51:08
 **/
@Setter
@Getter
public class Users {

	//数据库中的字段
	private Long id;// 主键
	private String login_name;// 登录名称
	private String nick_name;// 昵称
	private String password;// 密码
	private Byte auth;// 身份
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date create_time;// 创建时间
	private Long author;// 创建人
	private Byte status;// 状态

	//此处可添加查询显示辅助字段
	private int theme = 0;

}