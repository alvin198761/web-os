package org.alvin.opsdev.desktop.system.protocols;
import java.util.List;

import org.alvin.opsdev.desktop.system.common.BaseDao;
import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.protocols.Protocols;
import org.alvin.opsdev.webos.commom.app.protocols.ProtocolsCond;
import org.alvin.opsdev.webos.commom.app.utils.SqlUtil;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;



/**
 * @类说明:远程协议数据访问层
 * @author:高振中
 * @date:2018-05-12 16:51:08
 **/
@Repository
public class ProtocolsDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SQL及初始化工作
	 **/
	public ProtocolsDao () {
		select.append("SELECT t.id,t.domain,t.user,t.ip,t.port,t.password,t.author,t.create_time,t.status,t.type FROM protocols t WHERE 1=1");

		insert.append("INSERT INTO protocols (domain,user,ip,port,password,author,create_time,status,type)");
		insert.append(" VALUES (:domain,:user,:ip,:port,:password,:author,:create_time,:status,:type)");
	}

	/**
	 * @方法说明:新增远程协议记录
	 **/
	public int save(Protocols vo) {
		String sql = "REPLACE INTO protocols (id,domain,user,ip,port,password,author,create_time,status,type) VALUES (?,?,?,?,?,?,?,?,?,?) ";
		Object[] params ={vo.getId(),vo.getDomain(),vo.getUser(),vo.getIp(),vo.getPort(),vo.getPassword(),vo.getAuthor(),vo.getCreate_time(),vo.getStatus(),vo.getType()};
		//logger.info(SqlUtil.showSql(sql, params));//显示SQL语句
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:新增远程协议记录返回自增涨主键值
	 **/
	public long saveReturnPK(Protocols vo) {
		return saveKey(vo, insert.toString(), "id");
	}

	/**
	 * @方法说明:批量插入远程协议记录
	 **/
	public int[] insertBatch(List<Protocols> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:物理删除远程协议记录(多条)
	 **/
	public int delete(Long ids[]) {
		String sql = "DELETE FROM protocols WHERE id" + SqlUtil.ArrayToIn(ids);//数值型ID使用ArrayToInNum
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:按ID查找单个远程协议实体
	 **/
	public Protocols findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Protocols.class));
	}

	/**
	 * @方法说明:更新远程协议记录
	 **/
	public int update(Protocols vo) {
		String sql = "UPDATE protocols SET domain=?,user=?,ip=?,port=?,password=?,author=?,create_time=?,status=?,type=? WHERE id=? ";
		Object[] params = {vo.getDomain(),vo.getUser(),vo.getIp(),vo.getPort(),vo.getPassword(),vo.getAuthor(),vo.getCreate_time(),vo.getStatus(),vo.getType(),vo.getId()};
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:按条件查询分页远程协议列表-根据需要替换成自己的SQL
	 **/
	public Page<Protocols> queryPage(ProtocolsCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		//sb.append(cond.getOrderSql());//增加排序子句;
		//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, Protocols.class);
	}

	/**
	 * @方法说明:按条件查询不分页远程协议列表(使用范型)-根据需要替换成自己的SQL
	 **/
	public List<Protocols> queryList(ProtocolsCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Protocols.class));
	}

	/**
	 * @方法说明:按条件查询远程协议记录个数
	 **/
	public long queryCount(ProtocolsCond cond) {
		String countSql = "SELECT COUNT(1) FROM protocols t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明:逻辑删除远程协议记录(多条)
	 **/
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE protocols SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}
}