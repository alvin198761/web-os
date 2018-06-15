package org.alvin.opsdev.webos.migration;

import org.alvin.opsdev.webos.migration.bean.DataSourceVo;
import org.alvin.opsdev.webos.migration.bean.WebOsConfig;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@ConfigurationProperties()
@Data
public class FlywayMain {
	private Log logger = LogFactory.getLog(getClass());
	private String driver = "com.mysql.jdbc.Driver";
	private WebOsConfig webosConfig;
	private String params = "?useUnicode=true&characterEncoding=utf-8&useSSL=false";

	@PostConstruct
	public void init() {
		webosConfig.getConfig().forEach((k, v) -> exec(v, k));
	}

	public void exec(List<DataSourceVo> list, String... path) {
		for (DataSourceVo vo : list) {
			String dbname = vo.getUrl().split(":")[3].split("/")[1];
			String newUrl = vo.getUrl().replace(dbname, "mysql") + params;
			DataSource ds = DataSourceBuilder.create().driverClassName(driver).url(newUrl).username(vo.getUser()).password(vo.getPassword()).build();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
			int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.schemata WHERE schema_name='" + dbname + "'", Integer.class);
			if (count == 0 || webosConfig.getDropMode().equals("delete")) {
				jdbcTemplate.execute("DROP DATABASE IF EXISTS " + dbname);
				jdbcTemplate.execute("CREATE DATABASE " + dbname + " DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci");
				logger.info("创建数据库[" + dbname + "]完成!!");
			}
			DataSource dataSource = DataSourceBuilder.create().driverClassName(driver).url(vo.getUrl() + params).username(vo.getUser()).password(vo.getPassword()).build();
			Flyway flyway = new Flyway();
			flyway.setDataSource(dataSource);
			flyway.setLocations(path);
			flyway.migrate();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(FlywayMain.class, args);
	}


}
