package dbcol.app.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

public class TableData extends ViewPart {
	
	private CTabFolder tabFolder;
	
	private Map<String, CTabItem> itemsMap = new HashMap<String, CTabItem>();
	
	private Table table;
	
	
	public TableData() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setSelectionBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		
//		CTabItem tableDataItem_2 = new CTabItem(tabFolder, SWT.NONE);
//		tableDataItem_2.setText("New Item");
//		
//		CTabItem tabItem = new CTabItem(tabFolder, SWT.CLOSE);
//		tabItem.setText("New Item");
//		
//		Composite composite = new Composite(tabFolder, SWT.NONE);
//		tabItem.setControl(composite);
//		composite.setLayout(new TableColumnLayout());
//		TableViewer tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
//		table = tableViewer.getTable();
//		table.setHeaderVisible(true);
//		table.setLinesVisible(true);
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 增加新的页签
	 * @param title  标题
	 * @param content	内容
	 * @param style		样式，不需要样式则设置
	 */
	public void addItem(String title, Control content, Integer style) {
		if(itemsMap.containsKey(title)) {
			//之前已经打开了
			CTabItem tableDataItem = itemsMap.get(title);
			tabFolder.setSelection(tableDataItem);
			return;
		}
		CTabItem tableDataItem = new CTabItem(tabFolder, style == null ? SWT.NONE : style, tabFolder.getItemCount());
		tableDataItem.setText(title);
		tableDataItem.setControl(content);
		tableDataItem.setShowClose(true);	//支持关闭功能
		tableDataItem.addDisposeListener((e)->{
			//从缓存中移除CTabItem，调用关闭方法
			itemsMap.remove(title).getControl().dispose();
		});
		itemsMap.put(title, tableDataItem);
		tabFolder.setSelection(tableDataItem);	//打开新增页签
		
	}


	public CTabFolder getTabFolder() {
		return tabFolder;
	}
	
}
