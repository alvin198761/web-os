package org.alvin.opsdev.desktop.system.dict;

import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.dict.Dict;
import org.alvin.opsdev.webos.commom.app.dict.DictCond;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @类说明:base_dict数据控制器层
 * @author:高振中
 * @date:2018-06-16 13:41:24
 **/
@RestController
@RequestMapping("/api/dict")
public class DictController {
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private DictService service; //注入base_dict数据逻辑层

	/**
	 * @方法说明:新增base_dict记录
	 **/
	@RequestMapping("save")
	public int save(@RequestBody Dict dict) {
		return service.save(dict);
	}

	/**
	 * @方法说明:删除base_dict记录(多条)
	 **/
	@RequestMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

	/**
	 * @方法说明:修改base_dict记录
	 **/
	@RequestMapping("update")
	public int update(@RequestBody Dict dict) {
		return service.update(dict);
	}

	/**
	 * @方法说明:按条件查询分页base_dict列表
	 **/
	@RequestMapping("queryPage")
	public Page<Dict> queryPage(@RequestBody DictCond cond ){
		return service.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页base_dict列表
	 **/
	@RequestMapping("queryList")
	public List<Dict> queryList(@RequestBody DictCond cond ){
		return service.queryList(cond);
	}

	/**
	 * @方法说明:按主键查单个base_dict记录
	 **/
	@RequestMapping("findById")
	public Dict findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

	/**
	 * @方法说明:按条件查询base_dict记录个数
	 **/
	@RequestMapping("queryCount")
	public long queryCount(@RequestBody DictCond cond ){
		return service.queryCount(cond);
	}
}