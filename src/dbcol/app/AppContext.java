package dbcol.app;
/**
 * 系统上下文，缓存系统中需要使用的类
 * @author zyj
 *
 */

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

import dbcol.app.database.dao.Query;
import dbcol.app.database.entity.DataSourceConfig;
import dbcol.app.database.exceptions.BusinessException;
import dbcol.app.database.mybatis.SqlSessionFactoryInitor;
/**
 * 上下文环境，主要是缓存一些在全应用都会用到的类、库、资源
 * @author 周昱君
 *
 */
public class AppContext {
	private static Query query = new Query();
	/**
	 * 当前外部数据源名称
	 */
	public static DataSourceConfig CURR_DS = new DataSourceConfig();
	/**
	 * 当前外部数据源对应的mybatis  sessionFactory
	 */
	public static SqlSessionFactory CURR_DS_SESSION_FACTORY;
	
	private static class Holder{
		private static SqlSessionFactory appSessionFactory = 
//				SqlSessionFactoryInitor.buildSessionFactoryFactoryFromXML("myconfig/mybatis-config-sqlite.xml");
				SqlSessionFactoryInitor.buildAppSessionFactory();
	}
	
	/**
	 * 获取应用sessionFactory
	 * @return
	 */
	public static SqlSessionFactory getAppSqlSessionFactory() {
		return Holder.appSessionFactory;
	}
	
	/**
	 * 获取Mybatis管理的mapper， 开启事务默认提交机制
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> T getMybatisMapper(Class<T> clazz) {
		return getAppSqlSessionFactory().openSession(true)	//openSession(true)：自动提交事务，如果不设置，则需要手动提交事务
				.getMapper(clazz);
	}
	
	
	public static Query getQuery() {
		return query;
	}
	
	
	
	/**
	 * 获取视图
	 * @param viewId
	 * @return
	 */
	public static IViewPart getView(String viewId) {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(viewId);
	}
	
	public static String getAppPath() {
		try {
			return FileLocator.toFileURL(Platform.getBundle("dbCol").getEntry("")).getPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("获取项目根路径异常：dbCol", e);
		}
	}
	
}
