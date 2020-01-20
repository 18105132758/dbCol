package dbcol.app;
/**
 * 系统上下文，缓存系统中需要使用的类
 * @author zyjliubaohua
 *
 */

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

import dbcol.app.database.dao.Query;

public class AppContext {
	private static Query query = new Query();
	
	public static Query getQuery() {
		return query;
	}
	
	
	
	/**
	 * 获取视图
	 * @param viewId
	 * @return
	 */
	public static IViewPart getView(String viewId) {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(viewId);
	}
	
	
}
