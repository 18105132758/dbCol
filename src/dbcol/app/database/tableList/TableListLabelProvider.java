package dbcol.app.database.tableList;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import dbcol.app.database.entity.Table;
/**
 * 数据表 表格 标签提供器
 * @author zyj
 *
 */
public class TableListLabelProvider implements ITableLabelProvider{

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getColumnText(Object element, int columnIndex) {
		//按序号获取对应列的内容,从好从0开始
		Table table = (Table) element;
		System.out.println("label provider: " + element + "   "  + columnIndex);
		if(columnIndex == 0) {
			return table.getTableName();
		}
		
		return "";
	}

}
