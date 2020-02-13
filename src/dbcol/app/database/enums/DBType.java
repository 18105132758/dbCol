package dbcol.app.database.enums;

import org.apache.commons.lang3.StringUtils;

import dbcol.app.database.exceptions.BusinessException;

/**
 * 数据库类型枚举类
 * @author 周昱君
 *
 */
public enum DBType {
	
	MYSQL("mysql"), ORACLE("oracle"), SQLSERVER("sqlServer");
	
	private final String value;
	
	
	private DBType(String value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return value;
	}

	/**
	 * 将字符串转换成枚举类型
	 * @param dbTypeStr
	 * @return
	 */
	public static DBType convert(String dbTypeStr) {
		if(StringUtils.isBlank(dbTypeStr)) {
			throw new BusinessException("param \"database type\" is blank: " + dbTypeStr);
		}
		switch (dbTypeStr) {
		case "mysql":
			return MYSQL;
		case "oracle":
			return ORACLE;
		case "sqlServer":
			return SQLSERVER;
		default:
			throw new BusinessException("error database type: " + dbTypeStr);
		}
	}
	
	/**
	 * 校验类型信息是否合法
	 * @param dbTypeStr
	 * @return
	 */
	public static boolean isValidType(String dbTypeStr) {
		try {
			convert(dbTypeStr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
