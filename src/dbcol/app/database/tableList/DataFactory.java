package dbcol.app.database.tableList;
/**
 * 数据工厂，临时创建要展示的数据
 * @author liubaohua
 *
 */

import java.util.ArrayList;
import java.util.List;

import dbcol.app.database.entity.Table;

public class DataFactory {
	
	public static List<Table> getTableList(){
		List<Table> tableList = new ArrayList<Table>();
		tableList.add(new Table("table_1"));
		tableList.add(new Table("table_2"));
		tableList.add(new Table("table_3"));
		tableList.add(new Table("table_4"));
		tableList.add(new Table("table_5"));
		tableList.add(new Table("table_6"));
		tableList.add(new Table("table_7"));
		tableList.add(new Table("table_8"));
		tableList.add(new Table("table_9"));
		tableList.add(new Table("table_10"));
		tableList.add(new Table("table_11"));
		tableList.add(new Table("table_12"));
		
		return tableList;
	}
	
	
}
