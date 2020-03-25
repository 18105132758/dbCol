package dbcol.app.database.mapper.tableList;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import dbcol.app.database.entity.DBTable;

public interface DBTableMapper {
	/**
	 * 查询数据库中的所有表
	 * @param dbName
	 * @return
	 */
	List<DBTable> selectTableList(@Param("dbName") String dbName);
	
	List<DBTable> selectTableList2(@Param("dbName") String dbName);
	
	
	/**
	 * 查询数据表信息，主要查询表中的数据列
	 * @param tableName	表名称
	 * @param dbName	所在库名
	 * @return
	 */
	DBTable selectDBTableByName(String tableName, String dbName);
	
	/**
	 * 查询表数据
	 * @param tableName	表名
	 * @param columnNames	表列
	 * @return
	 */
	List<Map<String, Object>> selectTableData(String tableName, List<String> columnNames);
}
