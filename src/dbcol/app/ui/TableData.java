package dbcol.app.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

public class TableData extends ViewPart {
	private Table table;

	public TableData() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		
		table = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		setPartName("表数据视图");

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
