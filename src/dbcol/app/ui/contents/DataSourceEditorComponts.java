package dbcol.app.ui.contents;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import dbcol.app.database.entity.DataSourceConfig;
import dbcol.app.database.enums.DBType;

public class DataSourceEditorComponts {
	private Text dsNameText;
	private CCombo dbType;
	private Text urlText;
	private Text hostText;
	private Text portText;
	private Text dataBaseText;
	private Text userNameText;
	private Text passwordText;
	private Label infoLabel;
	public Text getDsNameText() {
		return dsNameText;
	}
	public void setDsNameText(Text dsNameText) {
		this.dsNameText = dsNameText;
	}
	public CCombo getDbType() {
		return dbType;
	}
	public void setDbType(CCombo dbType) {
		this.dbType = dbType;
	}
	public Text getUrlText() {
		return urlText;
	}
	public void setUrlText(Text urlText) {
		this.urlText = urlText;
	}
	public Text getHostText() {
		return hostText;
	}
	public void setHostText(Text hostText) {
		this.hostText = hostText;
	}
	public Text getPortText() {
		return portText;
	}
	public void setPortText(Text portText) {
		this.portText = portText;
	}
	public Text getDataBaseText() {
		return dataBaseText;
	}
	public void setDataBaseText(Text dataBaseText) {
		this.dataBaseText = dataBaseText;
	}
	public Text getUserNameText() {
		return userNameText;
	}
	public void setUserNameText(Text userNameText) {
		this.userNameText = userNameText;
	}
	public Text getPasswordText() {
		return passwordText;
	}
	public void setPasswordText(Text passwordText) {
		this.passwordText = passwordText;
	}
	public Label getInfoLabel() {
		return infoLabel;
	}
	public void setInfoLabel(Label infoLabel) {
		this.infoLabel = infoLabel;
	}
	
	/**
	 * 提取参数信息
	 * @return
	 */
	public DataSourceConfig extractParamsForTest() {
		DataSourceConfig dsCfg = new DataSourceConfig();
		
		String userName = this.userNameText.getText();
		dsCfg.setUserName(userName);
		
		String password = this.passwordText.getText();
		dsCfg.setPassword(password);
		
		String dbTypeStr = this.dbType.getText();
		DBType dbType = DBType.convert(dbTypeStr);
		dsCfg.setDbType(dbType);
		
		String jdbcURL = this.urlText.getText();
		if(StringUtils.isNotBlank(jdbcURL)) {
			dsCfg.setJdbcURL(jdbcURL);
			return dsCfg;
		}
		
		extractBaseDsParam(dsCfg);
		return dsCfg;
	}
	
	
	/**
	 * 进行保存时设置参数
	 * @return
	 */
	public boolean paramsCheckForSaving() {
		String dsName = this.dsNameText.getText();
		if(StringUtils.isBlank(dsName)) {
			this.infoLabel.setText("请输入用户名");
			return false;
		}
		return connParamCheck();
	}
	
	/**
	 * 连接测试参数校验: JDBCURL和普通参数二选一进行设置，JDBCURL优先级高
	 * @return
	 */
	public boolean connParamCheck() {
		String dbTypeStr = this.dbType.getText();
		if(!DBType.isValidType(dbTypeStr)) {
			this.infoLabel.setText("请选择数据库类型");
			return false;
		}
		
		String userName = this.userNameText.getText();
		if(StringUtils.isBlank(userName)) {
			this.infoLabel.setText("请填写用户名");
			return false;
		}
		String password = this.passwordText.getText();
		if(StringUtils.isBlank(password)) {
			this.infoLabel.setText("请填写密码");
			return false;
		}
		
		String jdbcURL = this.urlText.getText();
		if(StringUtils.isNotBlank(jdbcURL)) {
			return true;
		}

		String host = this.hostText.getText();
		if(StringUtils.isBlank(host)) {
			this.infoLabel.setText("请填写服务器地址");
			return false;
		}
			
		String portStr = this.portText.getText();
		if(StringUtils.isBlank(portStr)) {
			this.infoLabel.setText("请填写端口");
			return false;
		}
		
		
		String dbName = this.dataBaseText.getText();
		if(StringUtils.isBlank(dbName)) {
			this.infoLabel.setText("请指定数据库名");
			return false;
		}
		return true;
	}
	
	/**
	 * 提取参数，用于保存数据
	 * @return
	 */
	public DataSourceConfig extractParamForSave() {
		DataSourceConfig dsCfg = new DataSourceConfig();
		
		String userName = this.userNameText.getText();
		dsCfg.setUserName(userName);
		
		String password = this.passwordText.getText();
		dsCfg.setPassword(password);
		
		String dbTypeStr = this.dbType.getText();
		DBType dbType = DBType.convert(dbTypeStr);
		dsCfg.setDbType(dbType);
		
		String jdbcURL = this.urlText.getText();
		dsCfg.setJdbcURL(jdbcURL);
		
		
		extractBaseDsParam(dsCfg);
		return dsCfg;
	}
	/**
	 * 提取基础数据源参数
	 * @param dsCfg
	 */
	public void extractBaseDsParam(DataSourceConfig dsCfg) {
		String dsName = this.dsNameText.getText();
		dsCfg.setDsName(dsName);
		
		String host = this.hostText.getText();
		dsCfg.setHost(host);
		
		String portStr = this.portText.getText();
		dsCfg.setPort(Integer.parseInt(portStr));
		
		String dbName = this.dataBaseText.getText();
		dsCfg.setDbName(dbName);
	}
}
