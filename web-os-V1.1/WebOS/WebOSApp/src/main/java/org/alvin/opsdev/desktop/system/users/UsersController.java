package org.alvin.opsdev.desktop.system.users;
import java.util.List;

import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.users.Users;
import org.alvin.opsdev.webos.commom.app.users.UsersCond;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @类说明:用户信息数据控制器层
 * @author:高振中
 * @date:2018-05-12 16:51:08
 **/
@RestController
@RequestMapping("/api/users")
public class UsersController {
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private UsersService service; //注入用户信息数据逻辑层

	/**
	 * @方法说明:新增用户信息记录
	 **/
	@RequestMapping("save")
	public int save(@RequestBody Users users) {
		return service.save(users);
	}

	/**
	 * @方法说明:删除用户信息记录(多条)
	 **/
	@RequestMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

	/**
	 * @方法说明:修改用户信息记录
	 **/
	@RequestMapping("update")
	public int update(@RequestBody Users users) {
		return service.update(users);
	}

	/**
	 * @方法说明:按条件查询分页用户信息列表
	 **/
	@RequestMapping("queryPage")
	public Page<Users> queryPage(@RequestBody UsersCond cond ){
		return service.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页用户信息列表
	 **/
	@RequestMapping("queryList")
	public List<Users> queryList(@RequestBody UsersCond cond ){
		return service.queryList(cond);
	}

	/**
	 * @方法说明:按主键查单个用户信息记录
	 **/
	@RequestMapping("findById")
	public Users findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

	/**
	 * @方法说明:按条件查询用户信息记录个数
	 **/
	@RequestMapping("queryCount")
	public long queryCount(@RequestBody UsersCond cond ){
		return service.queryCount(cond);
	}
}