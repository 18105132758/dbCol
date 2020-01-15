package dbcol.app.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;

public class TableData extends ViewPart {

	public TableData() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		ListViewer listViewer = new ListViewer(scrolledComposite, SWT.BORDER | SWT.V_SCROLL);
		List list = listViewer.getList();
		scrolledComposite.setContent(list);
		scrolledComposite.setMinSize(list.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		setPartName("表数据视图");

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
