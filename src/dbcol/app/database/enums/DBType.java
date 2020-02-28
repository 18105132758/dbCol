package dbcol.app.database.enums;

import dbcol.app.database.mapper.tableList.MysqlTableMapper;

/**
 * 数据库类型枚举类
 * @author 周昱君
 *
 */
public enum DBType {
	
	MYSQL("com.mysql.cj.jdbc.Driver", MysqlTableMapper.class), ORACLE(null, null), SQLSERVER(null, null);
	
	
	private final String driverClass;
	
	private Class mapperClass;
	
	private static String[] nameArr;
	static {
		nameArr = new String[DBType.values().length];
		int i = 0;
		for(DBType dbType : DBType.values()) {
			nameArr[i++] = dbType.name();
		}
	}
	
	
	private DBType(String driverClass, Class mapperClass) {
		this.driverClass = driverClass;
		this.mapperClass = mapperClass;
	}


	@Override
	public String toString() {
		return this.name();
	}

	
	public String getDriverClass() {
		return driverClass;
	}

	public Class getMapperClass() {
		return this.mapperClass;
	}
	

	/**
	 * 将字符串转换成枚举类型
	 * @param dbTypeStr
	 * @return
	 */
	public static DBType convert(String dbTypeStr) {
		return DBType.valueOf(dbTypeStr);
//		throw new BusinessException("param \"database type\" is blank: " + dbTypeStr);
//		throw new BusinessException("error database type: " + dbTypeStr);
	}
	
	/**
	 * 校验类型信息是否合法
	 * @param dbTypeStr
	 * @return
	 */
	public static boolean isValidType(String dbTypeStr) {
		try {
			DBType.valueOf(dbTypeStr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String[] getElementsNameArr() {
		return nameArr;
	}
}
