package dbcol.app.database.dao;

import java.util.ArrayList;
import java.util.List;

import dbcol.app.database.entity.DBTable;
import dbcol.app.database.entity.OneLineData;
/**
 * 数据查询，后续使用MyBaties
 * @author zyj
 *
 */
public class Query {
	/**
	 * 查询数据
	 * @param tableInfo
	 * @return
	 */
	public List<OneLineData> queryData(DBTable tableInfo){
		List<OneLineData> data = new ArrayList<OneLineData>();
		String tableName = tableInfo.getTableName();
		for (int i = 0; i < 20; i++) {
			OneLineData line = loadOneLineColumnData(tableName);
			data.add(line);
		}
		return data;
	}

	private OneLineData loadOneLineColumnData(String tableName) {
		OneLineData line = new OneLineData();
		for(int i = 1; i < 11; i++) {
			line.addOneColumnData(tableName + "_data_" + i);
		}
		return line;
	}
	
}
