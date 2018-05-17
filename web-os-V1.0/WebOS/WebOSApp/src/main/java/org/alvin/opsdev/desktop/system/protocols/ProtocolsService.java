package org.alvin.opsdev.desktop.system.protocols;
import java.util.List;

import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.protocols.Protocols;
import org.alvin.opsdev.webos.commom.app.protocols.ProtocolsCond;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @类说明:远程协议数据逻辑层
 * @author:高振中
 * @date:2018-05-12 16:51:08
 **/
@Service
public class ProtocolsService {
	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ProtocolsDao dao; //注入远程协议数据访问层

	/**
	 * @方法说明:新增远程协议记录
	 **/
	@Transactional
	public int save(Protocols protocols) {
		return dao.save(protocols);
	}

	/**
	 * @方法说明:删除远程协议记录(多条)
	 **/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	 * @方法说明:按ID查找单个远程协议记录
	 **/
	public Protocols findById(Long id) {
		return dao.findById(id);
	}

	/**
	 * @方法说明:更新远程协议记录
	 **/
	@Transactional
	public int update(Protocols protocols) {
		return dao.update(protocols);
	}

	/**
	 * @方法说明:按条件查询分页远程协议列表
	 **/
	public Page<Protocols> queryPage(ProtocolsCond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页远程协议列表(使用范型)
	 **/
	public List<Protocols> queryList(ProtocolsCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询远程协议记录个数
	 **/
	public long queryCount(ProtocolsCond cond) {
		return dao.queryCount(cond);
	}
}