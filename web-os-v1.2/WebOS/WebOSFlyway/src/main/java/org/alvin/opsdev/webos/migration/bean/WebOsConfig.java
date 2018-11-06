package org.alvin.opsdev.webos.migration.bean;

import java.util.List;
import java.util.Map;

public class WebOsConfig {

    private String dropMode;
    
    private Map<String, List<DataSourceVo>> config;

    public String getDropMode() {
		return dropMode;
	}

	public void setDropMode(String dropMode) {
		this.dropMode = dropMode;
	}

	public Map<String, List<DataSourceVo>> getConfig() {
        return config;
    }

    public void setConfig(Map<String, List<DataSourceVo>> config) {
        this.config = config;
    }
}
