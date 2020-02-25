package test.mybatis.data.mapper;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import test.mybatis.data.entity.Author;

public interface AuthorMapper {
	
	@Results(id = "authorResult", value = {
			@Result(property = "age", column = "age"),
			@Result(property = "sex", column = "sex")
	})
	@ConstructorArgs({
		@Arg(column = "id", javaType = int.class, id = true),
		@Arg(column = "name", javaType = String.class)
	})
	@Select("select * from AUTHOR WHERE id=#{id}")
	Author selectById(int id);
	
}
