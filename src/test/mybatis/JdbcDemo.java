package test.mybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDemo {
	private static String dbUrl="jdbc:mysql://127.0.0.1:3306/zyj?useSSL=false&serverTimezone=UTC";
	private static String dbUserName="root";
	private static String dbPassword="123456";
	private static String jdbcName="com.mysql.cj.jdbc.Driver";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		if(con != null) {
			System.out.println("连接成功");
		}
	}
}
