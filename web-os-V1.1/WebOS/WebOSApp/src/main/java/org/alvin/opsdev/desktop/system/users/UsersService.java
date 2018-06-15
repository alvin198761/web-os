package org.alvin.opsdev.desktop.system.users;
import java.util.List;

import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.users.Users;
import org.alvin.opsdev.webos.commom.app.users.UsersCond;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @类说明:用户信息数据逻辑层
 * @author:高振中
 * @date:2018-05-12 16:51:08
 **/
@Service
public class UsersService {
	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private UsersDao dao; //注入用户信息数据访问层

	/**
	 * @方法说明:新增用户信息记录
	 **/
	@Transactional
	public int save(Users users) {
		return dao.save(users);
	}

	/**
	 * @方法说明:删除用户信息记录(多条)
	 **/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	 * @方法说明:按ID查找单个用户信息记录
	 **/
	public Users findById(Long id) {
		return dao.findById(id);
	}

	/**
	 * @方法说明:更新用户信息记录
	 **/
	@Transactional
	public int update(Users users) {
		return dao.update(users);
	}

	/**
	 * @方法说明:按条件查询分页用户信息列表
	 **/
	public Page<Users> queryPage(UsersCond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页用户信息列表(使用范型)
	 **/
	public List<Users> queryList(UsersCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询用户信息记录个数
	 **/
	public long queryCount(UsersCond cond) {
		return dao.queryCount(cond);
	}
}