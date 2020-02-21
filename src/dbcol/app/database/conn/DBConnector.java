package dbcol.app.database.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dbcol.app.database.entity.DataSourceConfig;
import dbcol.app.database.exceptions.BusinessException;
import dbcol.app.utils.ClosableUtils;

/**
 * 数据库连接器
 * @author zhouyj
 *
 */
public class DBConnector {
	
	/**
	 * 数据库连接测试
	 * @param dsCfg
	 * @return
	 */
	public static boolean connTest(DataSourceConfig dsCfg) {
		Connection con = null;
		try{
			Class.forName(dsCfg.getDbType().getDriverClass());
			con = DriverManager.getConnection(dsCfg.jointJdbcURL(),dsCfg.getUserName(),dsCfg.getPassword());
			if(con != null) {
				System.out.println("连接成功");
				return true;
			}
			return false;
		} catch (ClassNotFoundException e) {
			throw new BusinessException("不支持的数据库类型!", e);
		} catch (SQLException e) {
			throw new BusinessException("连接失败:" + e.getMessage(), e);
		}finally {
			ClosableUtils.closeDBConnection(con);
		}
	}
	
}
