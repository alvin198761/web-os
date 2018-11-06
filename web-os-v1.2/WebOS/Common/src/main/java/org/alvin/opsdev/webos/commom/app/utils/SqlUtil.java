package org.alvin.opsdev.webos.commom.app.utils;

import com.google.common.base.Joiner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.List;

public class SqlUtil {

	private static final Log logger = LogFactory.getLog(SqlUtil.class);

	/**
	 * @方法说明:数据库中执行的SQL语句
	 */
	public static void showSql(String sql, Object[] obj) {
		String param;
		for (int j = 0; null != obj && j < obj.length; j++) {
			param = "null";
			if (null != obj[j]) {
				String cname = obj[j].getClass().getName();
				if (cname.contains("Date") || cname.contains("Timestamp")) {
					param = "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(obj[j]) + "'";
				} else if (cname.contains("String")) {
					param = "'" + (String) obj[j] + "'";
				} else {
					param = obj[j].toString();
				}
			}
			sql = sql.replaceFirst("[?]", param);
		}
		logger.info(sql);
	}

	/**
	 * @方法说明:把组数拼接成IN语句(字符型)
	 */
	// @Deprecated
	public static String ArrayToIn(String ids) {// 字符IN字符窜
		String[] arr = ids.split(",");
		return " IN ('" + Joiner.on("','").join(arr) + "')";
	}

	/**
	 * @方法说明:把组数拼接成IN语句
	 */
	public static String ArrayToIn(Long ids[]) {
		return " IN ('" + Joiner.on("','").join(ids) + "')";
	}

	/**
	 * @方法说明:把List拼接成IN语句(数值型)
	 */
	public static String ArrayToIn(List<?> ids) {// 数值IN字符窜
		return " ('" + Joiner.on("','").join(ids) + "')";
	}

}
