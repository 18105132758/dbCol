package dbcol.app.ui;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import dbcol.app.database.conn.DBConnector;
import dbcol.app.database.entity.DataSourceConfig;
import dbcol.app.database.enums.DBType;

public class DataSourceEditor extends Dialog{

	private Text dsNameText;
	private CCombo dbType;
	private Text urlText;
	private Text hostText;
	private Text portText;
	private Text dataBaseText;
	private Text userNameText;
	private Text passwordText;
	private Label infoLabel;
	
	public DataSourceEditor(Shell parent) {
		super(parent);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout layout = new GridLayout(2, false);
		layout.marginRight = 6;
		container.setLayout(layout);
		
		
		//创建用户名：标签、输入框
		Label dsNameLabel = new Label(container, SWT.NONE);
		dsNameLabel.setText("名称：");
		//水平右对齐，垂直居中，水平方向不填充空白，垂直方向不填充空白， 跨1行，跨1列
		dsNameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		dsNameLabel.setImage(CacheImage.getAppImage(ImagePath.LOGIN_USERNAME_ICO));
		
		dsNameText = new Text(container, SWT.BORDER);
		dsNameText.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		
		//数据库类型 
		Label dbTypeLabel = new Label(container, SWT.NONE);
		dbTypeLabel.setText("数据库类型：");
		dbTypeLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		dbType = new CCombo(container, SWT.BORDER | SWT.READ_ONLY);
		dbType.setItems(new String[] {"oracle", "mysql", "sqlserver"});
		dbType.setLayoutData(new GridData(GridData.FILL, SWT.CENTER, false, false, 1, 1));
		
		//jdbc URL： 供专业人士使用
		Label urlLable = new Label(container, SWT.NONE);
		urlLable.setText("JDBC_URL：");
		urlLable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		urlText = new Text(container, SWT.BORDER);
		//水平方向填充整个区域，垂直居中
		urlText.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		
		/****************普通配置组： 用于一般用户进行数据库连接配置*********************/
		createCommonDSConfigGroup(container);

		infoLabel = new Label(container, SWT.NONE);
		infoLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
//		infoLabel.setText(" ");
		
		return super.createDialogArea(parent);
	}
	
	
	
	
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
//		super.createButtonsForButtonBar(parent);
		/**
		 * Dialog预定义了一些按钮，可以通过IDialogConstants中定义的类型来指定
		 */
//		Composite container = new Composite(parent, SWT.BORDER);
//		parent.setLayout(new RowLayout());
		//测试连接
		Button testConnBtn = createButton(parent, IDialogConstants.NEXT_ID, "测试连接1", true);
//		testConnBtn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		//创建登录按钮
		Button saveBtn = createButton(parent, IDialogConstants.OK_ID, "保存1", true);
//		saveBtn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		//创建退出按钮
		Button cancleBtn = createButton(parent, IDialogConstants.CANCEL_ID, "取消1", true);
//		cancleBtn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		
		testConnBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				System.out.println("测试连接： 释放");
				if(!connParamCheck()) {
					return;
				}
				DataSourceConfig dsCfg = extractParams();
				boolean result = DBConnector.connTest(dsCfg);
				if(result) {
					infoLabel.setText("连接成功!");
				}
			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDown(e);
				System.out.println("测试连接： 按下");
			}
			
		});
	}
	
	
	/**
	 * 配置窗口标题、图标等信息
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("编辑数据源信息");	//标题
		newShell.setImage(null);	//后续设置图标
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 500);
	}
	
	

	@Override
	protected void okPressed() {
		super.okPressed();
	}

	/**
	 * 提取参数信息
	 * @return
	 */
	private DataSourceConfig extractParams() {
		DataSourceConfig dbCfg = new DataSourceConfig();
		
		String dsName = this.dsNameText.getText();
		dbCfg.setDsName(dsName);
		
		String dbTypeStr = this.dbType.getText();
		DBType dbType = DBType.convert(dbTypeStr);
		dbCfg.setDbType(dbType);
		
		String jdbcURL = this.urlText.getText();
		dbCfg.setJdbcURL(jdbcURL);
		
		String host = this.hostText.getText();
		dbCfg.setHost(host);
		
		String portStr = this.portText.getText();
		dbCfg.setPort(Integer.parseInt(portStr));
		
		String dbName = this.dataBaseText.getText();
		dbCfg.setDbName(dbName);
		
		String userName = this.userNameText.getText();
		dbCfg.setUserName(userName);
		
		String password = this.passwordText.getText();
		dbCfg.setPassword(password);
		
		return dbCfg;
	}
	
	
	/**
	 * 进行保存时设置参数
	 * @return
	 */
	private boolean saveParamsCheck() {
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
	private boolean connParamCheck() {
		String dbTypeStr = this.dbType.getText();
		if(!DBType.isValidType(dbTypeStr)) {
			this.infoLabel.setText("请选择数据库类型");
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
//		if(StringUtils.isBlank(dbName)) {
//			this.infoLabel.setText("请指定数据库名");
//			return false;
//		}
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
		
		return true;
	}
	
	/**
	 * 创建普通的数据库配置 控件(分组)
	 */
	private void createCommonDSConfigGroup(Composite parent) {
		Group commonGroup = new Group(parent, SWT.BORDER_SOLID);
		commonGroup.setLayoutData(new GridData(GridData.FILL, SWT.CENTER, false, false, 2, 5));
		commonGroup.setLayout(new GridLayout(2, false));
		//IP地址
		Label hostLabel = new Label(commonGroup, SWT.NONE);
		hostLabel.setText("服务器地址：");
		hostLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		hostText = new Text(commonGroup, SWT.BORDER);
		hostText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		//端口
		Label portLabel = new Label(commonGroup, SWT.NONE);
		portLabel.setText("端口：");
		portLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		portText = new Text(commonGroup, SWT.BORDER);
		portText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		//数据库
		Label dataBaseLabel = new Label(commonGroup, SWT.NONE);
		dataBaseLabel.setText("数据库：");
		dataBaseLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		dataBaseText = new Text(commonGroup, SWT.BORDER);
		dataBaseText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		//用户名
		Label userNameLabel = new Label(commonGroup, SWT.NONE);
		userNameLabel.setText("用户名：");
		userNameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		userNameText = new Text(commonGroup, SWT.BORDER);
		userNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		//密码
		Label passwordLabel = new Label(commonGroup, SWT.NONE);
		passwordLabel.setText("密码：");
		passwordLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		passwordText = new Text(commonGroup, SWT.BORDER);
		passwordText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}
}
