package test.mybatis;

import dbcol.app.database.entity.DataSourceConfig;
import dbcol.app.database.enums.DBType;

public class TestResource {
	
	public static DataSourceConfig mysqlDS;
	
	static {
		mysqlDS = new DataSourceConfig();
		mysqlDS.setDbType(DBType.MYSQL);
		mysqlDS.setDbName("zyj");
		mysqlDS.setHost("127.0.0.1");
		mysqlDS.setPassword("123456");
		mysqlDS.setPort(3306);
		mysqlDS.setUserName("root");
		
	}
	
	
}
