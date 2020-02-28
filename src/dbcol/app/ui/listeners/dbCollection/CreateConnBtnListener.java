package dbcol.app.ui.listeners.dbCollection;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import dbcol.app.ui.DataSourceEditor;
/**
 * 新建连接按钮监听
 * @author 
 *
 */
public class CreateConnBtnListener implements MouseListener{
	@Override
	public void mouseUp(MouseEvent e) {
	}
	
	@Override
	public void mouseDown(MouseEvent e) {
		DataSourceEditor dataSourceEditor = new DataSourceEditor(null);
		int resultCode = dataSourceEditor.open();
		if(resultCode == Window.OK) {
			System.out.println("正常关闭.....");
		}
	}
	
	@Override
	public void mouseDoubleClick(MouseEvent e) {
	}
}
