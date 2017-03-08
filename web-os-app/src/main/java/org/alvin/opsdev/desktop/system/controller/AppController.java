package org.alvin.opsdev.desktop.system.controller;

import org.alvin.opsdev.desktop.system.bean.AppBean;
import org.alvin.opsdev.desktop.system.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tangzhichao on 2017/3/6.
 */
@RestController
public class AppController {

    @Autowired
    private AppService appService;

    @RequestMapping(value = "api/sidebar/apps", method = RequestMethod.GET)
    public List<AppBean> sidebarApps() {
        return appService.sidebarApps();
    }

    @RequestMapping(value = "/api/fisheye/apps", method = RequestMethod.GET)
    public List<AppBean> fishEyeApps() {
        return appService.fishEyeApps();
    }

    @RequestMapping(value = "/api/content/apps/{id}" ,method = RequestMethod.GET)
    public List<AppBean> contentApps(@PathVariable("id") String id){
        return appService.subApps(id);
    }
}
