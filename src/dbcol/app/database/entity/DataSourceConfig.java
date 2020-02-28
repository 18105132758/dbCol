package dbcol.app.database.entity;

import org.apache.commons.lang3.StringUtils;

import dbcol.app.database.enums.DBType;

/**
 * 数据源配置信息
 * @author zhouyj
 *
 */
public class DataSourceConfig {
	
	private String dsName;
	
	private String jdbcURL;
	
	private String host;
	
	private int port;
	
	private String dbName;
	
	private String userName;
	
	private String password;

	private DBType dbType;
	
	public String getJdbcURL() {
		return jdbcURL;
	}

	public void setJdbcURL(String jdbcURL) {
		this.jdbcURL = jdbcURL;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DBType getDbType() {
		return dbType;
	}

	public void setDbType(DBType dbType) {
		this.dbType = dbType;
	}

	public String getDsName() {
		return dsName;
	}

	public void setDsName(String dsName) {
		this.dsName = dsName;
	}
	
	/**
	 * 连接元素，生产URL
	 * @return
	 */
	public String jointJdbcURL() {
		if(StringUtils.isNotBlank(jdbcURL)) {
			return jdbcURL;
		}
		StringBuilder url = new StringBuilder("jdbc:mysql://")
				.append(host).append(":").append(port).append("/")
				.append(dbName)
				.append("?useSSL=false&serverTimezone=UTC");
		return url.toString();
//		jdbc:mysql://127.0.0.1:3306/zyj?useSSL=false&serverTimezone=UTC;
		
	}

	@Override
	public String toString() {
		return "DataSourceConfig [dsName=" + dsName + ", jdbcURL=" + jdbcURL + ", host=" + host + ", port=" + port
				+ ", dbName=" + dbName + ", userName=" + userName + ", password=" + password + ", dbType=" + dbType
				+ "]";
	}
	
}
