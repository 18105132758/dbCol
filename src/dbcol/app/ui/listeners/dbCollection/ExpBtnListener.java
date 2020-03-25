package dbcol.app.ui.listeners.dbCollection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;

import dbcol.app.AppContext;
import dbcol.app.database.entity.DBTable;
import dbcol.app.database.mapper.tableList.DBTableMapper;
import dbcol.app.ui.TableList;
import dbcol.app.ui.consts.AppConsts;
import dbcol.app.utils.ClosableUtils;

public class ExpBtnListener implements MouseListener{

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public void mouseUp(MouseEvent e) {
		/*
		 * 导出:
		 * （1）获取选中的表
		 * （2）选择导出目录
		 * （2）导出csv
		 */
		TableList tableListView = (TableList) AppContext.getView(AppConsts.TABLE_LIST_UI);
		List<DBTable> checkedTables = tableListView.getCheckedTables();
		if(CollectionUtils.isEmpty(checkedTables)) {
			return;
		}
		
		//选择路径
//		FileDialog fileDialog= new FileDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
		DirectoryDialog directoryDialog = new DirectoryDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
		String directory = directoryDialog.open();	//(返回选择文件的路径)
		System.out.println(directory);
		//遍历表
		SqlSessionFactory sessionFactory = AppContext.CURR_OUTER_DS_SESSION_FACTORY;
		SqlSession sqlSession = sessionFactory.openSession();
		DBTableMapper tableMapper = (DBTableMapper) sqlSession.getMapper(AppContext.CURR_OUTER_DS.getDbType().getMapperClass());
		for (DBTable dbTable : checkedTables) {
			//查询表数据
			List<Map<String, Object>> data = tableMapper.selectTableData(dbTable.getTableName(), dbTable.getColumnNames());
			Iterator<Map<String, Object>> iterator = data.iterator();
			String filePath = directory + File.separator + dbTable.getTableName() + ".csv";
			exportTableDataToFile(dbTable, filePath, iterator);
			
		}
		
		//导出csv
		
		
	}

	private void exportTableDataToFile(DBTable dbTable, String filePath, Iterator<Map<String, Object>> iterator) {
		FileWriter writer = null;
		try {
			StringBuilder lineData = new StringBuilder();
			List<String> columns = dbTable.getColumnNames(); 
			String header = String.join(",", columns);
			writer = new FileWriter(filePath);
			writer.write(header + "\n\r");
			while(iterator.hasNext()) {
				Map<String, Object> line = iterator.next();
				for (String column : columns) {
					lineData.append(line.get(column));
					lineData.append(",");
				}
				String lineStr = lineData.substring(0, lineData.length() - 1);
				writer.append(lineStr +  "\n\r");
				writer.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ClosableUtils.close(writer);
		}
	}

}
