package test.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dbcol.app.database.tableList.mapper.MysqlTableMapper;
import test.mybatis.data.entity.Author;
import test.mybatis.data.mapper.AuthorMapper;
import test.mybatis.data.mapper.BlogMapper;

public class XMLDemo {
	
	
	public static void main(String[] args) {
		
		
		String resource = "myconfig/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory =
					new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			
//			blogMapperTest(session);
			
			authorMapperTest(session);
			
			MysqlTableMapper tableMapper = session.getMapper(MysqlTableMapper.class);
			List<String> tables = tableMapper.selectTableList("zyj");
			System.out.println(String.join(",", tables));
			
			
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
