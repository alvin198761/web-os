package org.alvin.opsdev.desktop.system.notes;
import java.util.List;

import org.alvin.opsdev.desktop.system.common.BaseDao;
import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.notes.Notes;
import org.alvin.opsdev.webos.commom.app.notes.NotesCond;
import org.alvin.opsdev.webos.commom.app.utils.SqlUtil;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;



/**
 * @类说明:便签信息数据访问层
 * @author:高振中
 * @date:2018-05-12 16:51:08
 **/
@Repository
public class NotesDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SQL及初始化工作
	 **/
	public NotesDao () {
		select.append("SELECT t.id,t.title,t.content,t.last_time,t.author,t.status FROM notes t WHERE 1=1");

		insert.append("INSERT INTO notes (title,content,last_time,author,status)");
		insert.append(" VALUES (:title,:content,:last_time,:author,:status)");
	}

	/**
	 * @方法说明:新增便签信息记录
	 **/
	public int save(Notes vo) {
		String sql = "REPLACE INTO notes (id,title,content,last_time,author,status) VALUES (?,?,?,?,?,?) ";
		Object[] params ={vo.getId(),vo.getTitle(),vo.getContent(),vo.getLast_time(),vo.getAuthor(),vo.getStatus()};
		//logger.info(SqlUtil.showSql(sql, params));//显示SQL语句
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:新增便签信息记录返回自增涨主键值
	 **/
	public long saveReturnPK(Notes vo) {
		return saveKey(vo, insert.toString(), "id");
	}

	/**
	 * @方法说明:批量插入便签信息记录
	 **/
	public int[] insertBatch(List<Notes> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:物理删除便签信息记录(多条)
	 **/
	public int delete(Long ids[]) {
		String sql = "DELETE FROM notes WHERE id" + SqlUtil.ArrayToIn(ids);//数值型ID使用ArrayToInNum
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:按ID查找单个便签信息实体
	 **/
	public Notes findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Notes.class));
	}

	/**
	 * @方法说明:更新便签信息记录
	 **/
	public int update(Notes vo) {
		String sql = "UPDATE notes SET title=?,content=?,last_time=?,author=?,status=? WHERE id=? ";
		Object[] params = {vo.getTitle(),vo.getContent(),vo.getLast_time(),vo.getAuthor(),vo.getStatus(),vo.getId()};
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:按条件查询分页便签信息列表-根据需要替换成自己的SQL
	 **/
	public Page<Notes> queryPage(NotesCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		//sb.append(cond.getOrderSql());//增加排序子句;
		//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, Notes.class);
	}

	/**
	 * @方法说明:按条件查询不分页便签信息列表(使用范型)-根据需要替换成自己的SQL
	 **/
	public List<Notes> queryList(NotesCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Notes.class));
	}

	/**
	 * @方法说明:按条件查询便签信息记录个数
	 **/
	public long queryCount(NotesCond cond) {
		String countSql = "SELECT COUNT(1) FROM notes t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明:逻辑删除便签信息记录(多条)
	 **/
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE notes SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}
}