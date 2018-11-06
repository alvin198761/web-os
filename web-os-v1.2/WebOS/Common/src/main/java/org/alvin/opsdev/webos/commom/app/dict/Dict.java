package org.alvin.opsdev.webos.commom.app.dict;
import lombok.Getter;
import lombok.Setter;

/**
 * @类说明:base_dict实体类
 * @author:高振中
 * @date:2018-06-16 13:41:24
 **/
@Setter
@Getter
public class Dict {

	//数据库中的字段
	private Long id;// 主键
	private Long data_key;// 键
	private String data_value;// 值
	private String remark;// 备注
	private Long type_code;// 分类
	private String type_name;// 类型名称
	private Long author;// 创建人

	//此处可添加查询显示辅助字段

}