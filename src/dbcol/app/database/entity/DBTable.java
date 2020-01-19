package dbcol.app.database.entity;

import java.util.List;

public class DBTable {
	
	private String tableName;
	
	
	private List<String> columnNames;

	public DBTable(String tableName) {
		super();
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}
	
}
