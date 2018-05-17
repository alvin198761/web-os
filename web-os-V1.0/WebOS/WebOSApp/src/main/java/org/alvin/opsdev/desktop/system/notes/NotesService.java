package org.alvin.opsdev.desktop.system.notes;
import java.util.List;

import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.notes.Notes;
import org.alvin.opsdev.webos.commom.app.notes.NotesCond;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @类说明:便签信息数据逻辑层
 * @author:高振中
 * @date:2018-05-12 16:51:08
 **/
@Service
public class NotesService {
	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private NotesDao dao; //注入便签信息数据访问层

	/**
	 * @方法说明:新增便签信息记录
	 **/
	@Transactional
	public int save(Notes notes) {
		return dao.save(notes);
	}

	/**
	 * @方法说明:删除便签信息记录(多条)
	 **/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	 * @方法说明:按ID查找单个便签信息记录
	 **/
	public Notes findById(Long id) {
		return dao.findById(id);
	}

	/**
	 * @方法说明:更新便签信息记录
	 **/
	@Transactional
	public int update(Notes notes) {
		return dao.update(notes);
	}

	/**
	 * @方法说明:按条件查询分页便签信息列表
	 **/
	public Page<Notes> queryPage(NotesCond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页便签信息列表(使用范型)
	 **/
	public List<Notes> queryList(NotesCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询便签信息记录个数
	 **/
	public long queryCount(NotesCond cond) {
		return dao.queryCount(cond);
	}
}