package dbcol.app.database.showTableData;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;

public class TDContentProvider implements IStructuredContentProvider{

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof List) {
			return ((List<?>)inputElement).toArray();
		}
		return null;
	}

}
