package dbcol.app.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Text;

public class DbCollection_back extends ViewPart {
	private Text dbURLText;

	public DbCollection_back() {
		// TODO Auto-generated constructor stub
	}

		
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new  Composite(parent, SWT.BORDER_DOT);
		container.setLayout(new FillLayout());
		Group gp = new Group(container, SWT.BORDER);
		gp.setLayout(new GridLayout(6, false));
		
		Label savedDBLabel = new Label(gp, SWT.NONE);
		savedDBLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		savedDBLabel.setText("已有数据源");
		
		CCombo savedDbLink = new CCombo(gp, SWT.BORDER);
		savedDbLink.setItems(new String[] {"db1", "db2", "db3", "db4"});
		new Label(gp, SWT.NONE);
		
		Button connBtn = new Button(gp, SWT.NONE);
		connBtn.setText("连接");
		new Label(gp, SWT.NONE);
		new Label(gp, SWT.NONE);
		
		Label dbTypeLabel = new Label(gp, SWT.NONE);
		dbTypeLabel.setText("数据库类型：");
		
		CCombo dbType = new CCombo(gp, SWT.BORDER);
		dbType.setItems(new String[] {"oracle", "mysql", "sqlserver"});
		new Label(gp, SWT.NONE);
		
		CLabel dbURLLabel = new CLabel(gp, SWT.NONE);
		dbURLLabel.setText("URL");
		
		dbURLText = new Text(gp, SWT.BORDER);
		dbURLText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button connTestBtn = new Button(gp, SWT.NONE);
		connTestBtn.setText("测试连接");
		
//		setPartName("数据库连接配置视图");
	}


	@Override
	public void setFocus() {
	}
}
