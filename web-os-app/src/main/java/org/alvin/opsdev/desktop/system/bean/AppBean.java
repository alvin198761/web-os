package org.alvin.opsdev.desktop.system.bean;

import java.util.List;

/**
 * Created by tangzhichao on 2017/3/6.
 */
public class AppBean {

    private String id;
    private Integer type;
    private String title;
    private String icon;
    private String url;
    private List<AppBean> apps;
    private Integer msgCount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public List<AppBean> getApps() {
        return apps;
    }

    public void setApps(List<AppBean> apps) {
        this.apps = apps;
    }

    public Integer getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(Integer msgCount) {
        this.msgCount = msgCount;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
