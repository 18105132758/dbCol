package dbcol.app.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class DbCollection extends ViewPart {
	private Text dbURLText;

	public DbCollection() {
		// TODO Auto-generated constructor stub
	}

		
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new  Composite(parent, SWT.BORDER_DOT);
		container.setLayout(new GridLayout(1, true));
		
		Group collGroup = new Group(container, SWT.BORDER);
		collGroup.setLayout(new GridLayout(6, false));
		Label savedDBLabel = new Label(collGroup, SWT.NONE);
		savedDBLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1));
		savedDBLabel.setText("已有数据源：");
		
		CCombo savedDbLink = new CCombo(collGroup, SWT.BORDER);
		savedDbLink.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		savedDbLink.setItems(new String[] {"db1--", "db2", "db3", "db4"});
		savedDbLink.setSize(1000, 20);
		
		Button connBtn = new Button(collGroup, SWT.NONE);
		connBtn.setText("连接");
		connBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		
		Button expBtn = new Button(collGroup, SWT.NONE);
		expBtn.setText("导出");
		expBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		
		//数据源配置分组
		Group dsCfgGroup = new Group(container, SWT.BORDER);
		dsCfgGroup.setLayout(new GridLayout(6, false));
		
		Label dbTypeLabel = new Label(dsCfgGroup, SWT.NONE);
		dbTypeLabel.setText("数据库类型：");
		dbTypeLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		CCombo dbType = new CCombo(dsCfgGroup, SWT.BORDER);
		dbType.setItems(new String[] {"oracle", "mysql", "sqlserver"});
		dbType.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		CLabel dbURLLabel = new CLabel(dbType, SWT.NONE);
		dbURLLabel.setText("URL:");
		dbURLLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		
		dbURLText = new Text(dsCfgGroup, SWT.BORDER);
		dbURLText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Button connTestBtn = new Button(dsCfgGroup, SWT.NONE);
		connTestBtn.setText("测试连接");
		
//		Group collGroup = new Group(gp, SWT.BORDER);
//		collGroup.setLayout(new GridLayout(6, false));
		
//		setPartName("数据库连接配置视图");
	}


	@Override
	public void setFocus() {
	}
}
