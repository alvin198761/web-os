package org.alvin.opsdev.webos.commom.app.appicons;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.alvin.opsdev.webos.commom.app.utils.BaseCondition;

/**
 * @类说明:应用程序图标查询条件实体类
 * @author:唐植超
 * @date:2018-05-12 16:51:08
 **/
@Setter
@Getter
public class AppIconsCond extends BaseCondition  {

	/**
	 * @方法说明:拼加自定义条件;可添加新条件、属性字段,删除不用的条件、属性字段
	 **/
	@Override
	public void addCondition() { 
		add(id, "AND t.id = ?");
		add(type, "AND t.type = ?");
		add(title, "AND t.title LIKE ?", 3);
		add(tip, "AND t.tip LIKE ?", 3);
		add(icon, "AND t.icon LIKE ?", 3);
		add(publish_id, "AND t.publish_id = ?");
		add(create_time, "AND t.create_time = ?");
		add(parent_id, "AND t.parent_id = ?");
		add(route_url, "AND t.route_url LIKE ?", 3);
		add(status, "AND t.status = ?");
	}

	//页面查询条件的ID名称、查询条可以自行增减、把不用条件清理掉
	private Long id;// 主键
	private Byte type;// 图标类型
	private String title;// 显示文本
	private String tip;// 悬停提示
	private String icon;// 图标
	private Long publish_id;// 发布人
	private Date create_time;// 创建时间
	private Long parent_id;// 父节点
	private String route_url;// 对应路由地址
	private Byte status;// 状态

}