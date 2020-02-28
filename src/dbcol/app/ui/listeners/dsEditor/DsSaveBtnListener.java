package dbcol.app.ui.listeners.dsEditor;

import org.apache.ibatis.session.SqlSession;

import dbcol.app.AppContext;
import dbcol.app.database.entity.DataSourceConfig;
import dbcol.app.database.mapper.dbCollection.DataSourceConfigMapper;
import dbcol.app.ui.contents.DataSourceEditorComponts;
/**
 * 保存按钮事件监听处理
 * @author 周昱君
 *
 */
public class DsSaveBtnListener{
	
	private DataSourceEditorComponts uiComponents;
	
	public DsSaveBtnListener(DataSourceEditorComponts uiComponents) {
		super();
		this.uiComponents = uiComponents;
	}
	
	public void saveDataSourceConfig() {
		if(uiComponents.paramsCheckForSaving()) {
			DataSourceConfig dsc = uiComponents.extractParamForSave();
			SqlSession session = AppContext.getAppSqlSessionFactory().openSession();
			DataSourceConfigMapper dsMapper = session.getMapper(DataSourceConfigMapper.class);
			int i = dsMapper.saveLine(dsc);
//			List<DataSourceConfig> list = dsMapper.selectAllDsCfg();
//			for (DataSourceConfig dataSourceConfig : list) {
//				System.out.println(dataSourceConfig);
//			}
			session.flushStatements();
			session.commit();
			session.close();
		}
	}
}
