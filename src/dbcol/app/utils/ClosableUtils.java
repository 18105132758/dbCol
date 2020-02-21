package dbcol.app.utils;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ClosableUtils {
	
	/**
	 * 关闭资源
	 * @param resource
	 */
	public static void close(Closeable resource) {
		try {
			if(resource != null) {
				resource.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 */
	public static void closeDBConnection(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
