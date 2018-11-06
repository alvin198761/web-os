package org.alvin.opsdev.desktop.system.notes;
import java.util.List;

import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.notes.Notes;
import org.alvin.opsdev.webos.commom.app.notes.NotesCond;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @类说明:便签信息数据控制器层
 * @author:高振中
 * @date:2018-05-12 16:51:08
 **/
@RestController
@RequestMapping("/api/notes")
public class NotesController {
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private NotesService service; //注入便签信息数据逻辑层

	/**
	 * @方法说明:新增便签信息记录
	 **/
	@RequestMapping("save")
	public int save(@RequestBody Notes notes) {
		return service.save(notes);
	}

	/**
	 * @方法说明:删除便签信息记录(多条)
	 **/
	@RequestMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

	/**
	 * @方法说明:修改便签信息记录
	 **/
	@RequestMapping("update")
	public int update(@RequestBody Notes notes) {
		return service.update(notes);
	}

	/**
	 * @方法说明:按条件查询分页便签信息列表
	 **/
	@RequestMapping("queryPage")
	public Page<Notes> queryPage(@RequestBody NotesCond cond ){
		return service.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页便签信息列表
	 **/
	@RequestMapping("queryList")
	public List<Notes> queryList(@RequestBody NotesCond cond ){
		return service.queryList(cond);
	}

	/**
	 * @方法说明:按主键查单个便签信息记录
	 **/
	@RequestMapping("findById")
	public Notes findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

	/**
	 * @方法说明:按条件查询便签信息记录个数
	 **/
	@RequestMapping("queryCount")
	public long queryCount(@RequestBody NotesCond cond ){
		return service.queryCount(cond);
	}
}