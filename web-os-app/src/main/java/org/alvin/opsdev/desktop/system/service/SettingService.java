package org.alvin.opsdev.desktop.system.service;

import com.google.common.collect.Lists;
import org.alvin.opsdev.desktop.system.bean.RdpBean;
import org.alvin.opsdev.desktop.system.bean.SshBean;
import org.alvin.opsdev.desktop.system.bean.VncBean;
import org.alvin.opsdev.desktop.system.domain.Protocol;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangzhichao on 2017/3/6.
 */
@Service
public class SettingService {

    public List<RdpBean> rdps() {
        List<RdpBean> list = Lists.newArrayList();
        RdpBean rdpBean = new RdpBean();
        rdpBean.setId(1L);
        rdpBean.setDomain("");
        rdpBean.setIp("192.168.0.103");
        rdpBean.setPort(3389);
        rdpBean.setUser("administrator");
        rdpBean.setPassword("1");
        list.add(rdpBean);
        return list;
    }

    public List<SshBean> sshs() {
        List<SshBean> list = Lists.newArrayList();
        SshBean sshBean = new SshBean();
        sshBean.setId(1L);
        sshBean.setIp("192.168.0.104");
        sshBean.setPort(22);
        sshBean.setUser("root");
        sshBean.setPassword("111111");

        return list;
    }

    public List<VncBean> vncs() {
        List<VncBean> list = Lists.newArrayList();
        VncBean vnc = new VncBean();
        vnc.setId(1L);
        vnc.setIp("192.168.0.104");
        vnc.setPort(5900);
        //vnc.setUser("root");
        vnc.setPassword("");
        return list;
    }

    public Protocol getRDP(Long id) {
        Protocol protocol = new Protocol();

        return protocol;
    }

    public Protocol getSSH(Long id) {
        Protocol protocol = new Protocol();

        return protocol;
    }

    public Protocol getVNC(Long id) {
        Protocol protocol = new Protocol();

        return protocol;
    }
}
