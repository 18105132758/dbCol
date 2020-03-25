package dbcol.app.ui.cache;
/**
 * 数据表缓存
 * @author zyj
 *
 */

import java.util.HashMap;
import java.util.Map;

import dbcol.app.database.entity.DBTable;

public class TableCache {
	
	private static Map<String, DBTable> tableCache = new HashMap<String, DBTable>();
	
	/**
	 * 清理缓存表
	 */
	public static void clearCache() {
		tableCache.clear();
		tableCache = new HashMap<String, DBTable>();
	}
	/**
	 * 是否被缓存
	 * @param table
	 * @return
	 */
	public static boolean isCached(DBTable table) {
		if(table == null) {
			return true;
		}
		return tableCache.containsKey(table.getTableName());
	}
	
	/**
	 * 缓存DBTable,使用tableName作为key
	 * @param table
	 * @return
	 */
	public static void cacheTable(DBTable table) {
		if(table != null) {
			tableCache.put(table.getTableName(), table);
		}
	}
	
	public static DBTable getTable(String tableName) {
		return tableCache.get(tableName);
	}
	
}
