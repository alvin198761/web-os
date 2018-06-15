package org.alvin.opsdev.webos.commom.app.notes;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * @类说明:便签信息实体类
 * @author:唐植超
 * @date:2018-05-12 16:51:08
 **/
@Setter
@Getter
public class Notes {

	//数据库中的字段
	private Long id;// 主键
	private String title;// 标题
	private String content;// 内容
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date last_time;// 最后修改时间
	private Long author;// 作者
	private Byte status;// 状态

	//此处可添加查询显示辅助字段

}