package dbcol.app.database.tableList;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.actions.ActionGroup;

import dbcol.app.database.entity.DBTable;
/**
 * 菜单组，主要作用就是在内部创建一组菜单项，并使用菜单管理器、菜单项创建菜单
 * @author zyj
 *
 */
public class TableListActionGroup extends ActionGroup{
	
	private TableViewer tv;

	
	public TableListActionGroup(TableViewer tv) {
		super();
		this.tv = tv;
	}

	@Override
	public void fillContextMenu(IMenuManager menuManager) {
		//菜单管理器
		MenuManager manager = (MenuManager) menuManager;
		//将菜单项添加到菜单管理器
		manager.add(new OpenAction());
		manager.add(new RefereshAction());
		//获取Table
		org.eclipse.swt.widgets.Table table = tv.getTable();
		//为table创建菜单
		Menu menu = manager.createContextMenu(table);
		//将menu设置到table
		table.setMenu(menu);
	}

	
	
	/**
	 * 刷新功能项
	 * @author zyj
	 *
	 */
	private final class RefereshAction extends Action{
		
		public RefereshAction() {
			super();
			setText("刷新");
		}

		@Override
		public void run() {
			super.run();
			tv.refresh();
			System.out.println("shuaixn.....");
		}
		
	}
	
	/**
	 * 打开功能项
	 * @author zyj
	 *
	 */
	private final class OpenAction extends Action{
		
		public OpenAction() {
			setText("打开");
		}

		@Override
		public void run() {
			super.run();
			IStructuredSelection selection = tv.getStructuredSelection();
			DBTable table = (DBTable) selection.getFirstElement();
			if(table == null) {
				MessageDialog.openInformation(null, "提示", "选择记录");
			}else {
				MessageDialog.openConfirm(null, "详情", table.getTableName());
				
			}
		}
		
	}
	
}
