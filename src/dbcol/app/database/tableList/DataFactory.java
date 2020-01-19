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
		tableList.add(new DBTable("table_1"));
		tableList.add(new DBTable("table_2"));
		tableList.add(new DBTable("table_3"));
		tableList.add(new DBTable("table_4"));
		tableList.add(new DBTable("table_5"));
		tableList.add(new DBTable("table_6"));
		tableList.add(new DBTable("table_7"));
		tableList.add(new DBTable("table_8"));
		tableList.add(new DBTable("table_9"));
		tableList.add(new DBTable("table_10"));
		tableList.add(new DBTable("table_11"));
		tableList.add(new DBTable("table_12"));
		
		return tableList;
	}
	
	
}
