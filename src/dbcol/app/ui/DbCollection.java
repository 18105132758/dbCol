package dbcol.app.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import dbcol.app.AppContext;
import dbcol.app.database.mapper.dbCollection.DataSourceConfigMapper;
import dbcol.app.ui.listeners.dbCollection.ConnBtnListener;
import dbcol.app.ui.listeners.dbCollection.CreateConnBtnListener;
import dbcol.app.ui.listeners.dbCollection.UsableDataSourceSelecteListener;

public class DbCollection extends ViewPart {
	
	private Text dbURLText;
	/**
	 * 创建数据库连接按钮
	 */
	private Button createConnBtn;
	/**
	 * 可用数据源
	 */
	private CCombo usableDataSource;
	/**
	 * 连接按钮
	 */
	private Button connBtn;
	
	public DbCollection() {
		// TODO Auto-generated constructor stub
	}

		
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new  Composite(parent, SWT.BORDER_DOT);
		container.setLayout(new GridLayout(7, false));
		
//		Group collGroup = new Group(container, SWT.BORDER);
//		collGroup.setLayout(new GridLayout(7, false));
		Label savedDBLabel = new Label(container, SWT.NONE);
		savedDBLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1));
		savedDBLabel.setText("已有数据源：");
		
		usableDataSource = new CCombo(container, SWT.BORDER);
		usableDataSource.addSelectionListener(new SelectionAdapter() {
		});
		//查询可用数据源
		String[] dsNameArr = AppContext.getMybatisMapper(DataSourceConfigMapper.class).selectDsNames();
		usableDataSource.setItems(dsNameArr);
		usableDataSource.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		usableDataSource.setSize(1000, 20);
		usableDataSource.select(0);		//默认选中第一个
		
		connBtn = new Button(container, SWT.NONE);
		connBtn.setText("连接");
	
		createConnBtn = new Button(container, SWT.NONE);
		createConnBtn.setText("新建连接");
		
		Button expBtn = new Button(container, SWT.NONE);
		expBtn.setText("导出");
		
		registBtnEvent();
	}
	
	/**
	 * 注册相关按钮的事件监听
	 */
	private void registBtnEvent() {
		createConnBtn.addMouseListener(new CreateConnBtnListener());
		usableDataSource.addSelectionListener(new UsableDataSourceSelecteListener());
		connBtn.addMouseListener(new ConnBtnListener(usableDataSource));
	}
	

	@Override
	public void setFocus() {
	}
}
