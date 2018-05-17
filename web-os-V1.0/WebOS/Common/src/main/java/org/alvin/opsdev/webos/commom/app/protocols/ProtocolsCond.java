package org.alvin.opsdev.webos.commom.app.protocols;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.alvin.opsdev.webos.commom.app.utils.BaseCondition;

/**
 * @类说明:远程协议查询条件实体类
 * @author:唐植超
 * @date:2018-05-12 16:51:08
 **/
@Setter
@Getter
public class ProtocolsCond extends BaseCondition  {

	/**
	 * @方法说明:拼加自定义条件;可添加新条件、属性字段,删除不用的条件、属性字段
	 **/
	@Override
	public void addCondition() { 
		add(id, "AND t.id = ?");
		add(domain, "AND t.domain LIKE ?", 3);
		add(user, "AND t.user LIKE ?", 3);
		add(ip, "AND t.ip LIKE ?", 3);
		add(port, "AND t.port = ?");
		add(password, "AND t.password LIKE ?", 3);
		add(author, "AND t.author = ?");
		add(create_time, "AND t.create_time = ?");
		add(status, "AND t.status = ?");
		add(type, "AND t.type = ?");
	}

	//页面查询条件的ID名称、查询条可以自行增减、把不用条件清理掉
	private Long id;// 主键
	private String domain;// ad域
	private String user;// 用户名
	private String ip;// ip地址
	private Integer port;// 端口
	private String password;// 密码
	private Long author;// 作者
	private Date create_time;// 时间
	private Byte status;// 状态
	private Byte type;// 协议类型

}