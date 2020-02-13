package dbcol.app.ui;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class DbCollection extends ViewPart {
	
	private Text dbURLText;
	/**
	 * 创建数据库连接按钮
	 */
	private Button createConnBtn;
	
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
		
		CCombo savedDbLink = new CCombo(container, SWT.BORDER);
		savedDbLink.setItems(new String[] {"db1--", "db2", "db3", "db4"});
		savedDbLink.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		savedDbLink.setSize(1000, 20);
		
		Button connBtn = new Button(container, SWT.NONE);
		connBtn.setText("连接");
		connBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		
		createConnBtn = new Button(container, SWT.NONE);
		createConnBtn.setText("新建连接");
		createConnBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		
		Button expBtn = new Button(container, SWT.NONE);
		expBtn.setText("导出");
		expBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		
		//数据源配置分组
//		Group dsCfgGroup = new Group(container, SWT.BORDER);
//		dsCfgGroup.setLayout(new GridLayout(6, false));
//		
//		Label dbTypeLabel = new Label(dsCfgGroup, SWT.NONE);
//		dbTypeLabel.setText("数据库类型：");
//		dbTypeLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		
//		CCombo dbType = new CCombo(dsCfgGroup, SWT.BORDER);
//		dbType.setItems(new String[] {"oracle", "mysql", "sqlserver"});
//		dbType.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		
//		CLabel dbURLLabel = new CLabel(dbType, SWT.NONE);
//		dbURLLabel.setText("URL:");
//		dbURLLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
//		
//		dbURLText = new Text(dsCfgGroup, SWT.BORDER);
//		dbURLText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
//		
//		Button connTestBtn = new Button(dsCfgGroup, SWT.NONE);
//		connTestBtn.setText("测试连接");
//		new Label(dsCfgGroup, SWT.NONE);
		
//		Group collGroup = new Group(gp, SWT.BORDER);
//		collGroup.setLayout(new GridLayout(6, false));
		
//		setPartName("数据库连接配置视图");
		
		registBtnEvent();
	}
	
	/**
	 * 注册相关按钮的事件监听
	 */
	private void registBtnEvent() {
		createConnBtn.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				System.out.println("抬起......");
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("按下......");
				DataSourceEditor dataSourceEditor = new DataSourceEditor(null);
				int resultCode = dataSourceEditor.open();
				if(resultCode == Window.OK) {
					System.out.println("正常关闭.....");
				}
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("双击......");
			}
		});
	}
	

	@Override
	public void setFocus() {
	}
}
