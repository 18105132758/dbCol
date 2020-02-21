package dbcol.app.database.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import dbcol.app.database.entity.DataSourceConfig;
import test.mybatis.data.entity.Author;
import test.mybatis.data.mapper.AuthorMapper;
import test.mybatis.data.mapper.BlogMapper;

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
		//注册mapper
		configuration.addMappers("dbcol.app.database.tableList.mapper");
		configuration.addMapper(AuthorMapper.class);	
		configuration.addMapper(BlogMapper.class);
		
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);
		return factory;
	}
	
}
