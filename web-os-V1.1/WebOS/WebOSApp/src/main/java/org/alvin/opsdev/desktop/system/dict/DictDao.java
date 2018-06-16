package org.alvin.opsdev.desktop.system.dict;

import org.alvin.opsdev.desktop.system.common.BaseDao;
import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.dict.Dict;
import org.alvin.opsdev.webos.commom.app.dict.DictCond;
import org.alvin.opsdev.webos.commom.app.utils.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 * @类说明:base_dict数据访问层
 * @author:高振中
 * @date:2018-06-16 13:41:24
 **/
@Repository
public class DictDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SQL及初始化工作
	 **/
	public DictDao () {
		select.append("SELECT t.id,t.data_key,t.data_value,t.remark,t.type_code,t.type_name,t.author FROM base_dict t WHERE 1=1");

		insert.append("INSERT INTO base_dict (data_key,data_value,remark,type_code,type_name,author)");
		insert.append(" VALUES (:data_key,:data_value,:remark,:type_code,:type_name,:author)");
	}

	/**
	 * @方法说明:新增base_dict记录
	 **/
	public int save(Dict vo) {
		String sql = "REPLACE INTO base_dict (id,data_key,data_value,remark,type_code,type_name,author) VALUES (?,?,?,?,?,?,?) ";
		Object[] params ={vo.getId(),vo.getData_key(),vo.getData_value(),vo.getRemark(),vo.getType_code(),vo.getType_name(),vo.getAuthor()};
		//logger.info(SqlUtil.showSql(sql, params));//显示SQL语句
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:新增base_dict记录返回自增涨主键值
	 **/
	public long saveReturnPK(Dict vo) {
		return saveKey(vo, insert.toString(), "id");
	}

	/**
	 * @方法说明:批量插入base_dict记录
	 **/
	public int[] insertBatch(List<Dict> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:物理删除base_dict记录(多条)
	 **/
	public int delete(Long ids[]) {
		String sql = "DELETE FROM base_dict WHERE id" + SqlUtil.ArrayToIn(ids);//数值型ID使用ArrayToInNum
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:按ID查找单个base_dict实体
	 **/
	public Dict findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Dict.class));
	}

	/**
	 * @方法说明:更新base_dict记录
	 **/
	public int update(Dict vo) {
		String sql = "UPDATE base_dict SET data_key=?,data_value=?,remark=?,type_code=?,type_name=?,author=? WHERE id=? ";
		Object[] params = {vo.getData_key(),vo.getData_value(),vo.getRemark(),vo.getType_code(),vo.getType_name(),vo.getAuthor(),vo.getId()};
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:按条件查询分页base_dict列表-根据需要替换成自己的SQL
	 **/
	public Page<Dict> queryPage(DictCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		//sb.append(cond.getOrderSql());//增加排序子句;
		//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, Dict.class);
	}

	/**
	 * @方法说明:按条件查询不分页base_dict列表(使用范型)-根据需要替换成自己的SQL
	 **/
	public List<Dict> queryList(DictCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Dict.class));
	}

	/**
	 * @方法说明:按条件查询base_dict记录个数
	 **/
	public long queryCount(DictCond cond) {
		String countSql = "SELECT COUNT(1) FROM base_dict t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明:逻辑删除base_dict记录(多条)
	 **/
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE base_dict SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}
}