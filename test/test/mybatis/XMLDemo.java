package test.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dbcol.app.database.entity.DataSourceConfig;
import dbcol.app.database.mapper.dbCollection.DataSourceConfigMapper;
import test.mybatis.data.entity.Author;
import test.mybatis.data.mapper.AuthorMapper;
import test.mybatis.data.mapper.BlogMapper;

public class XMLDemo {
	
	
	public static void main(String[] args) {
		
		
		String resource = "myconfig/mybatis-config-sqlite.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory =
					new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			
//			blogMapperTest(session);
			
//			authorMapperTest(session);
			
//			MysqlTableMapper tableMapper = session.getMapper(MysqlTableMapper.class);
//			List<String> tables = tableMapper.selectTableList("zyj");
//			System.out.println(String.join(",", tables));
			
			DataSourceConfigMapper dsConfigMapper = session.getMapper(DataSourceConfigMapper.class);
			DataSourceConfig dsc = dsConfigMapper.selectByDsName("a");
			System.out.println(dsc);
			
			int i = dsConfigMapper.saveLine(TestResource.mysqlDS);
			System.out.println(i);
//			
			session.commit();
			
//			System.out.println(DBType.MYSQL.name());
//			System.out.println(	DBType.valueOf("MYSQL1"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void authorMapperTest(SqlSession session) {
		AuthorMapper authorMapper = session.getMapper(AuthorMapper.class);
		Author author = authorMapper.selectById(4);
		System.out.println(author);
	}

	private static void blogMapperTest(SqlSession session) {
		BlogMapper bmap = session.getMapper(BlogMapper.class);
		Blog blog = bmap.selectById("aaa", 1);
//			Blog blog = session.selectOne("test.mybatis.Blog.selectById",2);
		
		System.out.println(blog.toString());
	}
	
	
}
