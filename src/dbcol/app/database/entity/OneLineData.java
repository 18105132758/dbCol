package dbcol.app.database.entity;
/**
 * 一行数据
 * @author liubaohua
 *
 */

import java.util.ArrayList;
import java.util.List;

public class OneLineData {
	
	private List<Object> data;

	public OneLineData() {
		super();
		data = new ArrayList<Object>();
	}
	
	/**
	 * 增加一列数据
	 * @param columnData
	 */
	public void addOneColumnData(Object columnData) {
		data.add(columnData);
	}
	
	/**
	 * 获取一列数据
	 * @param index
	 * @return
	 */
	public Object getColumnData(int index) {
		if(index >= data.size()) {
			throw new IndexOutOfBoundsException("只有：" + data.size() + "列数据, 当前请求列号：" + index);
		}
		return data.get(index);
	}
	
	/**
	 * 获取列数
	 * @return
	 */
	public int getColumnCount() {
		return data.size();
	}
}
