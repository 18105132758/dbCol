package test.mybatis.data.mapper;

import org.apache.ibatis.annotations.Param;

import test.mybatis.Blog;

public interface BlogMapper {
	
	/**
	 * 根据ID、title查询一条记录，方法名称与对应的SQL语句一致
	 * @param title
	 * @param id
	 * @return
	 */
	public Blog selectById(@Param("title")String title, @Param("id")int id);
	
	
}
