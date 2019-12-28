package dbcol.app.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class DbCollection extends ViewPart {

	public DbCollection() {
		// TODO Auto-generated constructor stub
	}

		
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new  Composite(parent, SWT.BORDER_DOT);
		container.setLayout(new FillLayout());
		Group gp = new Group(container, SWT.BORDER);
		gp.setLayout(new GridLayout(2, false));
		
		Label label = new Label(gp, SWT.NONE);
		label.setText("数据库类型：");
		
		CCombo combo = new CCombo(gp, SWT.BORDER);
		combo.setItems(new String[] {"oracle", "mysql", "sqlserver"});
		
//		setPartName("数据库连接配置视图");
	}


	@Override
	public void setFocus() {
	}
}
