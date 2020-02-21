package dbcol.app.database.tableList.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MysqlTableMapper {
	
	@Select("select table_name from information_schema.tables where table_schema=#{dbName} and table_type='BASE TABLE'")
	List<String> selectTableList(@Param("dbName") String dbName);
	
	
	
}
