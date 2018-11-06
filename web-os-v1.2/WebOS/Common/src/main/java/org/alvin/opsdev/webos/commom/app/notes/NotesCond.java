package org.alvin.opsdev.webos.commom.app.notes;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.alvin.opsdev.webos.commom.app.utils.BaseCondition;

/**
 * @类说明:便签信息查询条件实体类
 * @author:唐植超
 * @date:2018-05-12 16:51:08
 **/
@Setter
@Getter
public class NotesCond extends BaseCondition  {

	/**
	 * @方法说明:拼加自定义条件;可添加新条件、属性字段,删除不用的条件、属性字段
	 **/
	@Override
	public void addCondition() { 
		add(id, "AND t.id = ?");
		add(title, "AND t.title LIKE ?", 3);
		add(content, "AND t.content LIKE ?", 3);
		add(last_time, "AND t.last_time = ?");
		add(author, "AND t.author = ?");
		add(status, "AND t.status = ?");
	}

	//页面查询条件的ID名称、查询条可以自行增减、把不用条件清理掉
	private Long id;// 主键
	private String title;// 标题
	private String content;// 内容
	private Date last_time;// 最后修改时间
	private Long author;// 作者
	private Byte status;// 状态

}