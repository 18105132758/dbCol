package dbcol.app.database.showTableData;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import dbcol.app.database.entity.DBTable;

/**
 * 生成数据表
 * @author zyj
 *
 */
public class DataTableFactory {
	
	public Composite createDataTableControl(DBTable dbTable, Composite parent) {
		//因为parent这里
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new TableColumnLayout());
		//创建tableViewer
		TableViewer tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION
				| SWT.HORIZONTAL | SWT.V_SCROLL | SWT.H_SCROLL);
		tableViewer.setUseHashlookup(true);
		//获取table，并设置样式信息、布局管理器
		Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayout(new TableLayout());
		//创建列信息
		createColumns(table, dbTable); 
		//设置内容提供器、标签提供器
		
		//填充表格数据
		
		//获取焦点
		
		//注册事件源
		
		
		//增加事件处理
		
		
		return composite;
		
	}
	
	
	/**
	 * 创建列信息
	 * @param table
	 * @param dbTable
	 */
	private void createColumns(Table table, DBTable dbTable) {
		List<String> columns = dbTable.getColumnNames();
		if (CollectionUtils.isEmpty(columns)) {
			return;
		}
		for (String col : columns) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setWidth(200);
			column.setText(col);
		}
	}
	
	
}
