package dbcol.app.database.tableList;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;

import dbcol.app.AppContext;
import dbcol.app.database.entity.DBTable;
import dbcol.app.database.showTableData.DataTableFactory;
import dbcol.app.ui.TableData;
import dbcol.app.ui.consts.AppConsts;

public class TableDoubleClickListenser implements IDoubleClickListener{

	@Override
	public void doubleClick(DoubleClickEvent event) {
		//获取选中的数据对象
		DBTable dbTable = (DBTable) ((IStructuredSelection)event.getSelection()).getFirstElement();
		//获取视图ViewPart：二维表数据视图
		TableData tableDataView = (TableData)AppContext.getView(AppConsts.TABLE_DATA_UI);
		boolean isOpened = tableDataView.isTableOpened(dbTable.getTableName());
		if(isOpened) {
			tableDataView.selectTable(dbTable.getTableName());
			return;
		}
		
		//获取标签容器：
		CTabFolder folder = ((TableData)AppContext.getView(AppConsts.TABLE_DATA_UI)).getTabFolder();
		//创建新的ViewTable，并在其中加载DBTable对应的数据
		Composite composite = DataTableFactory.createDataTableControl(dbTable, folder);
		//将composite添加到标签容器中
		tableDataView.addItem(dbTable.getTableName(), composite, SWT.NONE);
		
	}

}
