package dbcol.app.database.tableList;

import java.util.Collection;

import org.eclipse.jface.viewers.IStructuredContentProvider;

/**
 * 数据表 表格 的内容器提供器
 * @author zyj
 *
 */
public class TableListContentProvider implements IStructuredContentProvider{

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof Collection) {
			return ((Collection<?>) inputElement).toArray();
		}
		return new Object[0];
	}

}
