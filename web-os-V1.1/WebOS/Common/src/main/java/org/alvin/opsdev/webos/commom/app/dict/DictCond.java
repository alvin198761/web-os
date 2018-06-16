package org.alvin.opsdev.webos.commom.app.dict;
import lombok.Getter;
import lombok.Setter;
import org.alvin.opsdev.webos.commom.app.utils.BaseCondition;

import static com.sun.javafx.fxml.expression.Expression.add;

/**
 * @类说明:base_dict查询条件实体类
 * @author:高振中
 * @date:2018-06-16 13:41:24
 **/
@Setter
@Getter
public class DictCond extends BaseCondition {

	/**
	 * @方法说明:拼加自定义条件;可添加新条件、属性字段,删除不用的条件、属性字段
	 **/
	@Override
	public void addCondition() { 
		add(id, "AND t.id = ?");
		add(data_key, "AND t.data_key = ?");
		add(data_value, "AND t.data_value LIKE ?", 3);
		add(remark, "AND t.remark LIKE ?", 3);
		add(type_code, "AND t.type_code = ?");
		add(type_name, "AND t.type_name LIKE ?", 3);
		add(author, "AND t.author = ?");
	}

	//页面查询条件的ID名称、查询条可以自行增减、把不用条件清理掉
	private Long id;// 主键
	private Long data_key;// 键
	private String data_value;// 值
	private String remark;// 备注
	private Long type_code;// 分类
	private String type_name;// 类型名称
	private Long author;// 创建人

}