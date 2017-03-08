package org.alvin.opsdev.desktop.system.controller;

import org.alvin.opsdev.desktop.system.bean.RdpBean;
import org.alvin.opsdev.desktop.system.bean.SshBean;
import org.alvin.opsdev.desktop.system.bean.VncBean;
import org.alvin.opsdev.desktop.system.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tangzhichao on 2017/3/6.
 */
@RestController
public class SettingController {

    @Autowired
    private SettingService settingService;

    @RequestMapping(value = "/api/setting/rdps", method = RequestMethod.GET)
    public List<RdpBean> rdps() {
        return settingService.rdps();
    }

    @RequestMapping(value = "/api/setting/sshs", method = RequestMethod.GET)
    public List<SshBean> sshs() {
        return settingService.sshs();
    }

    @RequestMapping(value = "/api/setting/vncs", method = RequestMethod.GET)
    public List<VncBean> vncs() {
        return settingService.vncs();
    }
}
