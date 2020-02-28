package dbcol.app.ui;

import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import dbcol.app.database.entity.DBTable;
import dbcol.app.database.tableList.TableDoubleClickListenser;
import dbcol.app.database.tableList.TableListActionGroup;
import dbcol.app.database.tableList.TableListContentProvider;
import dbcol.app.database.tableList.TableListLabelProvider;

public class TableList extends ViewPart {
	private Table table;
	private TableViewer tableViewer;
	
	public TableList() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		tableViewer = new TableViewer(parent, SWT.BORDER | 
					SWT.CHECK |		//启用复选框
					SWT.FULL_SELECTION |  	//全行选中
//					SWT.MULTI | 	//多选
					SWT.V_SCROLL | 	//垂直滚动条
					SWT.H_SCROLL);	//水平滚动条
		tableViewer.setUseHashlookup(true);		//提高SWT组件与数据元素的映射效率
		table = tableViewer.getTable();
		table.setHeaderVisible(true);	//显示表头
		table.setLinesVisible(true);	//显示边框线
//		setPartName("表列表视图");
		
		//设置布局管理器
		TableLayout tableLayout = new TableLayout();	//专门用于表格的布局管理器
		table.setLayout(tableLayout);
//		table.setLayoutData(new GridData());

		//创建列
		TableColumn tableNameColumn = new TableColumn(table, SWT.NONE);
		//设置列宽，2000像素，因为TableViewer右侧部分有空白列，影响视觉效果，所以最后一列设置的很宽，避免出现空白列，影响视觉效果
		tableNameColumn.setWidth(200);	
		tableNameColumn.setText("表名");
		tableNameColumn.setResizable(false);  //禁止调整列宽
		
		//设置内容提供器
		tableViewer.setContentProvider(new TableListContentProvider());
		//设置标签提供器
		tableViewer.setLabelProvider(new TableListLabelProvider());
		
		//设置表格数据
//		tableViewer.setInput(DataFactory.getTableList());
		
		
		//选中第一条
//		table.setSelection(0);
		table.setFocus();		//获取焦点
		
		//将TableViewer注册成事件源，因为后续需要处理选中事件
		getViewSite().setSelectionProvider(tableViewer);
		
		//增加监听
		addListeners();
		
		//设置右键菜单
		TableListActionGroup actionGroup = new TableListActionGroup(tableViewer);
		actionGroup.fillContextMenu(new MenuManager());
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/**
	 * 增加表格事件处理
	 */
	private void addListeners() {
		//双击事件监听
		tableViewer.addDoubleClickListener(new TableDoubleClickListenser());
		//单击、复选框选中监听
		tableViewer.addSelectionChangedListener(
				(event)->System.out.println("single click..........."));
	}
	
	public void refereshTableList(List<DBTable> tableList) {
		tableViewer.setInput(tableList);
	}
}
