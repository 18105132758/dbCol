package dbcol.app.ui.listeners.dbCollection;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
/**
 * 可用数据源选中事件
 * @author zyj
 *
 */
public class UsableDataSourceSelecteListener extends SelectionAdapter{
	@Override
	public void widgetSelected(SelectionEvent e) {
		CCombo source = (CCombo) e.widget;
		String datasource = source.getItem(source.getSelectionIndex());
		System.out.println("选中："  + datasource);
	}
}
