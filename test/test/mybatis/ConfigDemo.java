package test.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dbcol.app.database.entity.DBTable;
import dbcol.app.database.mapper.tableList.MysqlTableMapper;
import dbcol.app.database.mybatis.SqlSessionFactoryInitor;
import test.mybatis.data.entity.Author;
import test.mybatis.data.mapper.AuthorMapper;

public class ConfigDemo {
	public static void main(String[] args) {
		SqlSessionFactory factory = SqlSessionFactoryInitor.initSqlSessionFactory(TestResource.mysqlDS);
		
		SqlSession session = factory.openSession();
		
		MysqlTableMapper tableMapper = session.getMapper(MysqlTableMapper.class);
		List<DBTable> tables = tableMapper.selectTableList("zyj");
//		System.out.println(String.join(",", tables));
		
		authorMapperTest(session);
	}
	
	private static void authorMapperTest(SqlSession session) {
		AuthorMapper authorMapper = session.getMapper(AuthorMapper.class);
		Author author = authorMapper.selectById(4);
		System.out.println(author);
	}
	
//	private static SqlSessionFactory initSqlSessionFactory() {
//		DataSourceConfig dsc = TestResource.mysqlDS;
//		/**
//		 * （1）创建数据源，
//		 * （2）创建事务工厂
//		 * （3）创建环境
//		 * （4）创建配置对象
//		 * （5）特性配置、注册别名
//		 * （6）注册Mapper接口或者mapper映射
//		 * （7）构建SQLSessionFactory
//		 */
//		DataSource dataSource = new UnpooledDataSource(dsc.getDbType().getDriverClass(), dsc.jointJdbcURL(), dsc.getUserName(), dsc.getPassword());
//		System.out.println("jdbc:  " + dsc.jointJdbcURL());
//		TransactionFactory transactionFactory = new JdbcTransactionFactory();
//		Environment environment = new Environment("development", transactionFactory, dataSource);
//		Configuration configuration = new Configuration(environment);
//		/*------------------------特性配置------------------------*/
//		configuration.setLazyLoadingEnabled(true);	//启用懒加载
//		configuration.setMapUnderscoreToCamelCase(true); //启用驼峰式规则映射属性
//		
//		configuration.getTypeAliasRegistry().registerAlias(Author.class);	//注册别名   仅示例使用
//		//注册mapper
//		configuration.addMappers("dbcol.app.database.tableList.mapper");
//		configuration.addMapper(AuthorMapper.class);	
//		configuration.addMapper(BlogMapper.class);
//		
//		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);
//		return factory;
//	}

}
