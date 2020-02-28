package dbcol.app.database.showTableData;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import dbcol.app.AppContext;
import dbcol.app.database.entity.DBTable;
import dbcol.app.database.mapper.tableList.DBTableMapper;
import dbcol.app.ui.consts.AppConsts;

/**
 * 生成数据表
 * @author zyj
 *
 */
public class DataTableFactory {
	
	/**
	 * 创建数据表对应的TableViewer控件
	 * @param dbTable
	 * @param parent
	 * @return
	 */
	public static Composite createDataTableControl(DBTable dbTable, Composite parent) {
		//因为parent这里
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		//创建tableViewer
		TableViewer tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION
				| SWT.HORIZONTAL | SWT.V_SCROLL | SWT.H_SCROLL);
		tableViewer.setUseHashlookup(true);
		//获取table，并设置样式信息、布局管理器
		Table table = tableViewer.getTable();
		tableConfig(table);
		//创建列信息
		createColumns(table, dbTable); 
		//设置内容提供器、标签提供器
		tableViewer.setLabelProvider(new TDLabelProvider(dbTable.getColumnNames()));
		tableViewer.setContentProvider(new TDContentProvider());
		//查询表数据，并填充到表格
//		----------------------------------------
		List<Map<String, Object>> data = queryData(dbTable);
		tableViewer.setInput(data);
		
//		tableViewer.setInput(AppContext.getQuery().queryData(dbTable));
		//获取焦点
		table.setFocus();
		//注册为选中事件源
		AppContext.getView(AppConsts.TABLE_DATA_UI).getViewSite().setSelectionProvider(tableViewer);
		//增加事件处理
			/*暂时不需要事件处理*/
		return composite;
		
	}
	
	/**
	 * 查询数据
	 * @param dbTableName
	 * @return
	 */
	private static List<Map<String, Object>> queryData(DBTable dbTable){
		SqlSession session = AppContext.CURR_DS_SESSION_FACTORY.openSession();
		DBTableMapper mapper = (DBTableMapper) session.getMapper(AppContext.CURR_DS.getDbType().getMapperClass());
		//查询表数据
		List<Map<String, Object>> data = mapper.selectTableData(dbTable.getTableName(), dbTable.getColumnNames());
		return data;
	}
	
	/**
	 * 配置Table控件
	 * @param table
	 */
	private static void tableConfig(Table table) {
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayout(new TableLayout());
	}
	
	
	/**
	 * 创建列信息
	 * @param table
	 * @param dbTable
	 */
	private static void createColumns(Table table, DBTable dbTable) {
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
