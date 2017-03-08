package org.alvin.opsdev.desktop.system.bean;

/**
 * Created by tangzhichao on 2017/3/6.
 */
public class VncBean {

    private Long id;
    private String ip;
    private Integer port;
    private String user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
