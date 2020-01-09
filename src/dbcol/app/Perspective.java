package dbcol.app;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewLayout;

import dbcol.app.ui.consts.AppConsts;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		System.out.print("透视图布局..................");
		
		String editAreaId = layout.getEditorArea();	//获得编辑区id
		layout.setEditorAreaVisible(false);		//隐藏编辑区
		layout.setFixed(true);					//固定布局
		
		/**
		 * 数据库编辑、表列表、表数据 视图排版
		 */
		layout.addStandaloneView(AppConsts.DB_COLLECT_UI, false, IPageLayout.TOP, 0.2F, editAreaId);
		setViewCloseableAndMoveable(layout.getViewLayout(AppConsts.DB_COLLECT_UI), false, false);
		
		layout.addStandaloneView(AppConsts.TABLE_LIST_UI, false, IPageLayout.BOTTOM, 0.2F, AppConsts.DB_COLLECT_UI);
		setViewCloseableAndMoveable(layout.getViewLayout(AppConsts.TABLE_LIST_UI), false, false);

		layout.addStandaloneView(AppConsts.TABLE_DATA_UI, false, IPageLayout.RIGHT, 0.35F, AppConsts.TABLE_LIST_UI);
		setViewCloseableAndMoveable(layout.getViewLayout(AppConsts.TABLE_DATA_UI), false, false);
	}
	
	/**
	 * 	设置视图是否可以关闭、是否可以移动
	 * @param layout
	 * @param closable
	 * @param movable
	 */
	private void setViewCloseableAndMoveable(IViewLayout layout, boolean closeable, boolean moveable) {
		layout.setCloseable(closeable);
		layout.setMoveable(moveable);
	}
}
