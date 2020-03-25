package dbcol.app.ui.listeners.dbCollection;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import dbcol.app.AppContext;
import dbcol.app.database.entity.DBTable;
import dbcol.app.database.entity.DataSourceConfig;
import dbcol.app.database.mapper.dbCollection.DataSourceConfigMapper;
import dbcol.app.database.mapper.tableList.DBTableMapper;
import dbcol.app.database.mybatis.SqlSessionFactoryInitor;
import dbcol.app.ui.TableList;
import dbcol.app.ui.consts.AppConsts;

public class ConnBtnListener implements MouseListener{
	/**
	 * 可用数据源
	 */
	private CCombo usableDataSource;
	
	public ConnBtnListener(CCombo usableDataSource) {
		super();
		this.usableDataSource = usableDataSource;
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void mouseUp(MouseEvent e) {
		String dsName = usableDataSource.getItem(usableDataSource.getSelectionIndex());
		
		DataSourceConfigMapper configMapper = AppContext.getAppMybatisMapper(DataSourceConfigMapper.class);
		DataSourceConfig dataSource = configMapper.selectByDsName(dsName);
		if(dataSource == null) {
			return;
		}
		//缓存当前数据源
		SqlSessionFactory sessionFactory = null;
		if(dataSource.getDsName().equals(AppContext.CURR_OUTER_DS.getDsName())) {
			sessionFactory = AppContext.CURR_OUTER_DS_SESSION_FACTORY;
		}else {
			//创建新库的数据源
			sessionFactory = SqlSessionFactoryInitor.initSqlSessionFactory(dataSource);
			AppContext.CURR_OUTER_DS = dataSource;
			AppContext.CURR_OUTER_DS_SESSION_FACTORY = sessionFactory;
		}
		SqlSession session = sessionFactory.openSession();
		//查询表列表
		DBTableMapper tableMapper = (DBTableMapper) session.getMapper(dataSource.getDbType().getMapperClass());
		List<DBTable> tableList = tableMapper.selectTableList(dataSource.getDbName());

		//刷新界面
		TableList tableListUI = (TableList) AppContext.getView(AppConsts.TABLE_LIST_UI);
		tableListUI.refereshTableList(tableList);
		
	}
	
}
