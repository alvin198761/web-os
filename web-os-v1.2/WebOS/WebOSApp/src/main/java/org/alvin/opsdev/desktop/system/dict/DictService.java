package org.alvin.opsdev.desktop.system.dict;

import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.dict.Dict;
import org.alvin.opsdev.webos.commom.app.dict.DictCond;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @类说明:base_dict数据逻辑层
 * @author:高振中
 * @date:2018-06-16 13:41:24
 **/
@Service
public class DictService {
	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private DictDao dao; //注入base_dict数据访问层

	/**
	 * @方法说明:新增base_dict记录
	 **/
	@Transactional
	public int save(Dict dict) {
		return dao.save(dict);
	}

	/**
	 * @方法说明:删除base_dict记录(多条)
	 **/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	 * @方法说明:按ID查找单个base_dict记录
	 **/
	public Dict findById(Long id) {
		return dao.findById(id);
	}

	/**
	 * @方法说明:更新base_dict记录
	 **/
	@Transactional
	public int update(Dict dict) {
		return dao.update(dict);
	}

	/**
	 * @方法说明:按条件查询分页base_dict列表
	 **/
	public Page<Dict> queryPage(DictCond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页base_dict列表(使用范型)
	 **/
	public List<Dict> queryList(DictCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询base_dict记录个数
	 **/
	public long queryCount(DictCond cond) {
		return dao.queryCount(cond);
	}
}