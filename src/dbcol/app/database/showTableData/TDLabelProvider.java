package dbcol.app.database.showTableData;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TDLabelProvider implements ITableLabelProvider{
	/**
	 * 要展示的列名称
	 */
	private List<String> columnNameList;
	
	public TDLabelProvider(List<String> columnNameList) {
		super();
		this.columnNameList = columnNameList;
	}

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
		if(element instanceof Map) {
			String column = this.columnNameList.get(columnIndex);
			Object value = ((Map)element).get(column);
			if(column != null) {
				return value.toString();
			}
		}
		return null;
	}


}
