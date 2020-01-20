package dbcol.app.database.tableList;
/**
 * 数据工厂，临时创建要展示的数据
 * @author zyj
 *
 */

import java.util.ArrayList;
import java.util.List;

import dbcol.app.database.entity.DBTable;

public class DataFactory {
	
	public static List<DBTable> getTableList(){
		List<DBTable> tableList = new ArrayList<DBTable>();
		for (int i = 0; i < 12; i++) {
			tableList.add(createOneTableInfo(i));
			
		}
		return tableList;
	}
	private static DBTable createOneTableInfo(int ordNum){
		DBTable table = new DBTable("table_" + ordNum);
		List<String> columnNames = new ArrayList<String>();
		for(int i = 0; i < 6; i++) {
			columnNames.add("column_" + i);
		}
		table.setColumnNames(columnNames);
		return table;
	}
	
}
