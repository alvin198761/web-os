package org.alvin.opsdev.desktop.system.appicons;

import org.alvin.opsdev.webos.commom.Page;
import org.alvin.opsdev.webos.commom.app.appicons.AppIcons;
import org.alvin.opsdev.webos.commom.app.appicons.AppIconsCond;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @类说明:应用程序图标数据逻辑层
 * @author:高振中
 * @date:2018-05-12 16:51:08
 **/
@Service
public class AppIconsService {
    @SuppressWarnings("unused")
    private Log logger = LogFactory.getLog(getClass());
    @Autowired
    private AppIconsDao dao; //注入应用程序图标数据访问层

    /**
     * @方法说明:新增应用程序图标记录
     **/
    @Transactional
    public int save(AppIcons appIcons) {
        return dao.save(appIcons);
    }

    /**
     * @方法说明:删除应用程序图标记录(多条)
     **/
    public int delete(Long ids[]) {
        //return dao.deleteLogic(ids);//逻辑删除
        return dao.delete(ids);//物理删除
    }

    /**
     * @方法说明:按ID查找单个应用程序图标记录
     **/
    public AppIcons findById(Long id) {
        return dao.findById(id);
    }

    /**
     * @方法说明:更新应用程序图标记录
     **/
    @Transactional
    public int update(AppIcons appIcons) {
        return dao.update(appIcons);
    }

    /**
     * @方法说明:按条件查询分页应用程序图标列表
     **/
    public Page<AppIcons> queryPage(AppIconsCond cond) {
        return dao.queryPage(cond);
    }

    /**
     * @方法说明:按条件查询不分页应用程序图标列表(使用范型)
     **/
    public List<AppIcons> queryList(AppIconsCond cond) {
        return dao.queryList(cond);
    }

    /**
     * @方法说明:按条件查询应用程序图标记录个数
     **/
    public long queryCount(AppIconsCond cond) {
        return dao.queryCount(cond);
    }

    /**
     * 获取侧边条app
     *
     * @param id
     * @return
     */
    public List<AppIcons> sidebarApps(Long id) {
        //目前是固定的 5个
        AppIconsCond appIconsCond = new AppIconsCond();
        appIconsCond.setType((byte) 2);
        appIconsCond.setStatus((byte) 1);
        return this.dao.queryList(appIconsCond);
    }

    /**
     * 获取鱼眼球菜单
     *
     * @param id
     * @return
     */
    public List<AppIcons> fishEyeApps(Long id) {
        //目前是固定的 8个
        AppIconsCond appIconsCond = new AppIconsCond();
        appIconsCond.setType((byte) 1);
        appIconsCond.setStatus((byte) 1);
        return this.dao.queryList(appIconsCond);
    }
}