package dbcol.app.database.mapper.dbCollection;

import java.util.List;

import dbcol.app.database.entity.DataSourceConfig;

public interface DataSourceConfigMapper {
	
	/**
	 * 查询一条记录
	 * @param dsName
	 * @return
	 */
	public DataSourceConfig selectByDsName(String dsName);
	
	/**
	 * 查询所有数据源配置信息 
	 * @return
	 */
	public List<DataSourceConfig> selectAllDsCfg();
	
	/**
	 * 新增记录
	 * @param dsc
	 */
	public int saveLine(DataSourceConfig dsc);
	
	/**
	 * 查询所有数据源名称
	 * @return
	 */
	public String[] selectDsNames();
}
