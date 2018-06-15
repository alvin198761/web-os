package org.alvin.opsdev.desktop.system.users;
import java.util.List;

import org.alvin.opsdev.desktop.system.common.BaseDao;
import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.users.Users;
import org.alvin.opsdev.webos.commom.app.users.UsersCond;
import org.alvin.opsdev.webos.commom.app.utils.SqlUtil;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;



/**
 * @类说明:用户信息数据访问层
 * @author:高振中
 * @date:2018-05-12 16:51:08
 **/
@Repository
public class UsersDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SQL及初始化工作
	 **/
	public UsersDao () {
		select.append("SELECT t.id,t.login_name,t.nick_name,t.password,t.auth,t.create_time,t.author,t.status FROM user_info t WHERE 1=1");

		insert.append("INSERT INTO user_info (login_name,nick_name,password,auth,create_time,author,status)");
		insert.append(" VALUES (:login_name,:nick_name,:password,:auth,:create_time,:author,:status)");
	}

	/**
	 * @方法说明:新增用户信息记录
	 **/
	public int save(Users vo) {
		String sql = "REPLACE INTO user_info (id,login_name,nick_name,password,auth,create_time,author,status) VALUES (?,?,?,?,?,?,?,?) ";
		Object[] params ={vo.getId(),vo.getLogin_name(),vo.getNick_name(),vo.getPassword(),vo.getAuth(),vo.getCreate_time(),vo.getAuthor(),vo.getStatus()};
		//logger.info(SqlUtil.showSql(sql, params));//显示SQL语句
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:新增用户信息记录返回自增涨主键值
	 **/
	public long saveReturnPK(Users vo) {
		return saveKey(vo, insert.toString(), "id");
	}

	/**
	 * @方法说明:批量插入用户信息记录
	 **/
	public int[] insertBatch(List<Users> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:物理删除用户信息记录(多条)
	 **/
	public int delete(Long ids[]) {
		String sql = "DELETE FROM user_info WHERE id" + SqlUtil.ArrayToIn(ids);//数值型ID使用ArrayToInNum
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:按ID查找单个用户信息实体
	 **/
	public Users findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Users.class));
	}

	/**
	 * @方法说明:更新用户信息记录
	 **/
	public int update(Users vo) {
		String sql = "UPDATE user_info SET login_name=?,nick_name=?,password=?,auth=?,create_time=?,author=?,status=? WHERE id=? ";
		Object[] params = {vo.getLogin_name(),vo.getNick_name(),vo.getPassword(),vo.getAuth(),vo.getCreate_time(),vo.getAuthor(),vo.getStatus(),vo.getId()};
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:按条件查询分页用户信息列表-根据需要替换成自己的SQL
	 **/
	public Page<Users> queryPage(UsersCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		//sb.append(cond.getOrderSql());//增加排序子句;
		//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, Users.class);
	}

	/**
	 * @方法说明:按条件查询不分页用户信息列表(使用范型)-根据需要替换成自己的SQL
	 **/
	public List<Users> queryList(UsersCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Users.class));
	}

	/**
	 * @方法说明:按条件查询用户信息记录个数
	 **/
	public long queryCount(UsersCond cond) {
		String countSql = "SELECT COUNT(1) FROM user_info t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明:逻辑删除用户信息记录(多条)
	 **/
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE user_info SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}
}