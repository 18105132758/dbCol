package dbcol.app.test;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

public class TabFolderDemo implements LayoutDemo{

	@Override
	public void layout(Shell shell) {
		shell.setLayout(new FillLayout());
		CTabFolder folder = new CTabFolder(shell, SWT.NONE);
		CTabItem item1 = new CTabItem(folder, SWT.NONE);
		item1.setShowClose(true);
		item1.setText("查看资料");
		Label l1 = new Label(folder, SWT.NONE);
		l1.setText("查看资料");
		item1.setControl(l1);	//为页面设置内容
		CTabItem item2 = new CTabItem(folder, SWT.CLOSE);
		item2.setText("查看文档");
	}

	@Test
	public void start() {
		new  DisplayDemo("ToolBar demo", 600, 600, this).show();;
	}
}