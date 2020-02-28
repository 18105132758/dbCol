package dbcol.app.ui.listeners.dsEditor;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import dbcol.app.database.conn.DBConnector;
import dbcol.app.database.entity.DataSourceConfig;
import dbcol.app.database.exceptions.BusinessException;
import dbcol.app.ui.contents.DataSourceEditorComponts;
/**
 * 保存按钮监听
 * @author zyj
 *
 */
public class TestConnBtnListener extends MouseAdapter{
	
	private DataSourceEditorComponts uiComponents;
	
	public TestConnBtnListener(DataSourceEditorComponts uiComponents) {
		super();
		this.uiComponents = uiComponents;
	}

	@Override
	public void mouseUp(MouseEvent e) {
		System.out.println("测试连接： 释放");
		if(!uiComponents.connParamCheck()) {
			return;
		}
		DataSourceConfig dsCfg = uiComponents.extractParamsForTest();
		try {
			boolean result = DBConnector.connTest(dsCfg);
			if(result) {
				uiComponents.getInfoLabel().setText("连接成功!");
			}
		} catch (BusinessException be) {
			uiComponents.getInfoLabel().setText("连接失败：" + be.getMessage());
		}
	}

	@Override
	public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseDown(e);
		System.out.println("测试连接： 按下");
	}
}
