package org.alvin.opsdev.desktop.system.appicons;

import org.alvin.opsdev.desktop.system.common.BaseDao;
import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.appicons.AppIcons;
import org.alvin.opsdev.webos.commom.app.appicons.AppIconsCond;
import org.alvin.opsdev.webos.commom.app.utils.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @类说明:应用程序图标数据访问层
 * @author:高振中
 * @date:2018-06-16 13:41:24
 **/
@Repository
public class AppIconsDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SQL及初始化工作
	 **/
	public AppIconsDao () {
		select.append("SELECT t.id,t.type,t.title,t.tip,t.icon,t.publish_id,t.create_time,t.parent_id,t.route_url,t.status,t.seq_num FROM app_icon t WHERE 1=1");

		insert.append("INSERT INTO app_icon (type,title,tip,icon,publish_id,create_time,parent_id,route_url,status,seq_num)");
		insert.append(" VALUES (:type,:title,:tip,:icon,:publish_id,:create_time,:parent_id,:route_url,:status,:seq_num)");
	}

	/**
	 * @方法说明:新增应用程序图标记录
	 **/
	public int save(AppIcons vo) {
		String sql = "REPLACE INTO app_icon (id,type,title,tip,icon,publish_id,create_time,parent_id,route_url,status,seq_num) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
		Object[] params ={vo.getId(),vo.getType(),vo.getTitle(),vo.getTip(),vo.getIcon(),vo.getPublish_id(),vo.getCreate_time(),vo.getParent_id(),vo.getRoute_url(),vo.getStatus(),vo.getSeq_num()};
		//logger.info(SqlUtil.showSql(sql, params));//显示SQL语句
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:新增应用程序图标记录返回自增涨主键值
	 **/
	public long saveReturnPK(AppIcons vo) {
		return saveKey(vo, insert.toString(), "id");
	}

	/**
	 * @方法说明:批量插入应用程序图标记录
	 **/
	public int[] insertBatch(List<AppIcons> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:物理删除应用程序图标记录(多条)
	 **/
	public int delete(Long ids[]) {
		String sql = "DELETE FROM app_icon WHERE id" + SqlUtil.ArrayToIn(ids);//数值型ID使用ArrayToInNum
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:按ID查找单个应用程序图标实体
	 **/
	public AppIcons findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(AppIcons.class));
	}

	/**
	 * @方法说明:更新应用程序图标记录
	 **/
	public int update(AppIcons vo) {
		String sql = "UPDATE app_icon SET type=?,title=?,tip=?,icon=?,publish_id=?,create_time=?,parent_id=?,route_url=?,status=?,seq_num=? WHERE id=? ";
		Object[] params = {vo.getType(),vo.getTitle(),vo.getTip(),vo.getIcon(),vo.getPublish_id(),vo.getCreate_time(),vo.getParent_id(),vo.getRoute_url(),vo.getStatus(),vo.getSeq_num(),vo.getId()};
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:按条件查询分页应用程序图标列表-根据需要替换成自己的SQL
	 **/
	public Page<AppIcons> queryPage(AppIconsCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		//sb.append(cond.getOrderSql());//增加排序子句;
		//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, AppIcons.class);
	}

	/**
	 * @方法说明:按条件查询不分页应用程序图标列表(使用范型)-根据需要替换成自己的SQL
	 **/
	public List<AppIcons> queryList(AppIconsCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		SqlUtil.showSql(sb.toString(),cond.getArray());//显示SQL语句
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(AppIcons.class));
	}

	/**
	 * @方法说明:按条件查询应用程序图标记录个数
	 **/
	public long queryCount(AppIconsCond cond) {
		String countSql = "SELECT COUNT(1) FROM app_icon t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明:逻辑删除应用程序图标记录(多条)
	 **/
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE app_icon SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}
}