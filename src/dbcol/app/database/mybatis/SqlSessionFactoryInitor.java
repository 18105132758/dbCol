package dbcol.app.database.mybatis;

import java.io.IOException;
import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import dbcol.app.AppContext;
import dbcol.app.database.entity.DataSourceConfig;
import dbcol.app.database.exceptions.BusinessException;
import dbcol.app.database.mapper.dbCollection.DataSourceConfigMapper;
import test.mybatis.data.entity.Author;

public class SqlSessionFactoryInitor {
	
	/**
	 * 根据数据源配置初始化mybatis SQLSessionFactory
	 * @param dsc
	 * @return
	 */
	public static SqlSessionFactory initSqlSessionFactory(DataSourceConfig dsc) {
		/**
		 * （1）创建数据源，
		 * （2）创建事务工厂
		 * （3）创建环境
		 * （4）创建配置对象
		 * （5）特性配置、注册别名
		 * （6）注册Mapper接口或者mapper映射
		 * （7）构建SQLSessionFactory
		 */
		DataSource dataSource = new UnpooledDataSource(dsc.getDbType().getDriverClass(), dsc.jointJdbcURL(), dsc.getUserName(), dsc.getPassword());
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		/*------------------------特性配置------------------------*/
		configuration.setLazyLoadingEnabled(true);	//启用懒加载
		configuration.setMapUnderscoreToCamelCase(true); //启用驼峰式规则映射属性
		
		configuration.getTypeAliasRegistry().registerAlias(Author.class);	//注册别名   仅示例使用
		//注册mapper  dbcol.app.database.mapper.tableList.DBTableMapper
//		configuration.addMappers("dbcol.app.database.mapper.tableList");	//文件不存在？？？？
//		configuration.addMappers("dbcol.app.database.mapper.tableList.*", DBTableMapper.class);	//接口没有注册成功？？
//		configuration.addMappers("dbcol.app.database.mapper.tableList.*");	//接口没有注册成功？？

		configuration.addMapper(dsc.getDbType().getMapperClass());	
//		configuration.addMapper(AuthorMapper.class);	
//		configuration.addMapper(BlogMapper.class);
		
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);
		return factory;
	}
	
	/**
	 * 基于XML配置初始化SQLSessionFactory
	 * @param configPath
	 * @return
	 */
	public static SqlSessionFactory buildSessionFactoryFactoryFromXML(String configPath) {
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(configPath);
			SqlSessionFactory sqlSessionFactory =
					new SqlSessionFactoryBuilder().build(inputStream);
			return sqlSessionFactory;
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("mybatis 初始化失败，配置文件：" + configPath, e);
		}
	}
	
	/**
	 * 构建系统使用的SQLSessionFactory
	 * @return
	 */
	public static SqlSessionFactory buildAppSessionFactory() {
		DataSource dataSource = new UnpooledDataSource("org.sqlite.JDBC", "jdbc:sqlite:" + AppContext.getAppPath() + "/database/appDB.db", 
				null, null);
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		/*------------------------特性配置------------------------*/
		configuration.setLazyLoadingEnabled(true);	//启用懒加载
		configuration.setMapUnderscoreToCamelCase(true); //启用驼峰式规则映射属性
		configuration.setLogImpl(StdOutImpl.class);		//sql输出到控制台
		
		configuration.getTypeAliasRegistry().registerAlias(Author.class);	//注册别名   仅示例使用
		//注册mapper, 这里，需要将对应的mapper映射文件放置在Mapper相同路径下，且名称与类名相同，仅后缀不同
		configuration.addMapper(DataSourceConfigMapper.class);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);
		
		return factory;
	}

}
