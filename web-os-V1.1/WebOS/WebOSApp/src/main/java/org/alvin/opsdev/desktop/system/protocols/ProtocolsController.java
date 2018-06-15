package org.alvin.opsdev.desktop.system.protocols;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.alvin.opsdev.desktop.system.common.PrincipalController;
import org.alvin.opsdev.desktop.system.common.acl.SessionUserSubject;
import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.protocols.Protocols;
import org.alvin.opsdev.webos.commom.app.protocols.ProtocolsCond;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @类说明:远程协议数据控制器层
 * @author:高振中
 * @date:2018-05-12 16:51:08
 **/
@RestController
@RequestMapping("/api/protocols")
public class ProtocolsController extends PrincipalController {
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ProtocolsService service; //注入远程协议数据逻辑层

	/**
	 * @方法说明:新增远程协议记录
	 **/
	@RequestMapping("save")
	public int save(@RequestBody Protocols protocols, Principal principal) {
		SessionUserSubject sessionUserSubject = getSubject(principal);
		protocols.setAuthor(sessionUserSubject.getId());
		protocols.setCreate_time(new Date());
		return service.save(protocols);
	}

	/**
	 * @方法说明:删除远程协议记录(多条)
	 **/
	@RequestMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

	/**
	 * @方法说明:修改远程协议记录
	 **/
	@RequestMapping("update")
	public int update(@RequestBody Protocols protocols) {
		return service.update(protocols);
	}

	/**
	 * @方法说明:按条件查询分页远程协议列表
	 **/
	@RequestMapping("queryPage")
	public Page<Protocols> queryPage(@RequestBody ProtocolsCond cond) {
		return service.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页远程协议列表
	 **/
	@RequestMapping("queryList")
	public List<Protocols> queryList(@RequestBody ProtocolsCond cond) {
		return service.queryList(cond);
	}

	/**
	 * @方法说明:按主键查单个远程协议记录
	 **/
	@RequestMapping("findById")
	public Protocols findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

	/**
	 * @方法说明:按条件查询远程协议记录个数
	 **/
	@RequestMapping("queryCount")
	public long queryCount(@RequestBody ProtocolsCond cond) {
		return service.queryCount(cond);
	}
}