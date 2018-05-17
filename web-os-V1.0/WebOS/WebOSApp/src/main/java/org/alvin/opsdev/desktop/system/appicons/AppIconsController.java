package org.alvin.opsdev.desktop.system.appicons;
import java.security.Principal;
import java.util.List;

import org.alvin.opsdev.desktop.system.common.PrincipalController;
import org.alvin.opsdev.desktop.system.common.acl.SessionUserSubject;
import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.appicons.AppIcons;
import org.alvin.opsdev.webos.commom.app.appicons.AppIconsCond;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @类说明:应用程序图标数据控制器层
 * @author:高振中
 * @date:2018-05-12 16:51:08
 **/
@RestController
@RequestMapping("/api/appIcons")
public class AppIconsController extends PrincipalController {
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AppIconsService service; //注入应用程序图标数据逻辑层

	/**
	 * @方法说明:新增应用程序图标记录
	 **/
	@RequestMapping("save")
	public int save(@RequestBody AppIcons appIcons) {
		return service.save(appIcons);
	}

	/**
	 * @方法说明:删除应用程序图标记录(多条)
	 **/
	@RequestMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

	/**
	 * @方法说明:修改应用程序图标记录
	 **/
	@RequestMapping("update")
	public int update(@RequestBody AppIcons appIcons) {
		return service.update(appIcons);
	}

	/**
	 * @方法说明:按条件查询分页应用程序图标列表
	 **/
	@RequestMapping("queryPage")
	public Page<AppIcons> queryPage(@RequestBody AppIconsCond cond ){
		return service.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页应用程序图标列表
	 **/
	@RequestMapping("queryList")
	public List<AppIcons> queryList(@RequestBody AppIconsCond cond ){
		return service.queryList(cond);
	}

	/**
	 * @方法说明:按主键查单个应用程序图标记录
	 **/
	@RequestMapping("findById")
	public AppIcons findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

	/**
	 * @方法说明:按条件查询应用程序图标记录个数
	 **/
	@RequestMapping("queryCount")
	public long queryCount(@RequestBody AppIconsCond cond ){
		return service.queryCount(cond);
	}

	@RequestMapping(value = "sidebar", method = RequestMethod.GET)
	public List<AppIcons> sidebarApps(Principal principal) {
		SessionUserSubject userSubject = this.getSubject(principal);
		return this.service.sidebarApps(userSubject.getId());
	}

	@RequestMapping(value = "fisheye", method = RequestMethod.GET)
	public List<AppIcons> fishEyeApps(Principal principal) {
		SessionUserSubject userSubject = this.getSubject(principal);
		return service.fishEyeApps(userSubject.getId());
	}
}