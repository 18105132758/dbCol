package dbcol.app.ui.listeners.tableList;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public class TableSelectedListener implements ISelectionChangedListener{

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		System.out.println("single click...........");
//		TableItem tableItem = event.
//		TableViewer tableViewer = (TableViewer) event.getSource();
//		ISelection s = tableViewer.getSelection();
//		Table table = tableViewer.getTable();
//		TableItem[] items =	table.getItems();
//		for (TableItem item : items) {
//			System.out.println(item.getChecked());
//			Object o = item.getData();
//		}
//		tableViewer.getSelection();
		
	}

}
