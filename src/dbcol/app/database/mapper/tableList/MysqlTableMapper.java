package dbcol.app.database.mapper.tableList;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import dbcol.app.database.entity.DBTable;

public interface MysqlTableMapper extends DBTableMapper{
	
	@ResultType(DBTable.class)
	@Results(id = "dbTableNameResult", value = {
			@Result(property = "tableName", column = "table_name")
	})
	@Select("select table_name from information_schema.tables where table_schema=#{dbName} and table_type='BASE TABLE'")
	List<DBTable> selectTableList(@Param("dbName") String dbName);

	/**
	 * 根据表名、数据库 查询表中的所有column，查询逻辑在MysqlTableMapper.xml中配置，必须和当前接口在相同目录
	 */
	DBTable selectDBTableByName(@Param("tableName") String tableName, @Param("dbName") String dbName);
	
//	@Select("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = #{tableName} AND TABLE_SCHEMA = #{dbName}")
//	List<String> selectTableColumns(@Param("tableName")String tableName, @Param("dbName")String dbName);
	
//	@ResultType(OneLineData.class)
//	@Select("SELECT * FROM #{tableInfo.tableName}")
	List<Map<String, Object>> selectTableData(@Param("tableName")String tableName, @Param("columns") List<String> columnNames);
	
		
}
