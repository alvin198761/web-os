package org.alvin.opsdev.webos.commom.app.appicons;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * @类说明:应用程序图标实体类
 * @author:唐植超
 * @date:2018-05-12 16:51:08
 **/
@Setter
@Getter
public class AppIcons {

	//数据库中的字段
	private Long id;// 主键
	private Byte type;// 图标类型
	private String title;// 显示文本
	private String tip;// 悬停提示
	private String icon;// 图标
	private Long publish_id;// 发布人
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date create_time;// 创建时间
	private Long parent_id;// 父节点
	private String route_url;// 对应路由地址
	private Byte status;// 状态

	//此处可添加查询显示辅助字段

}