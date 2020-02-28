package dbcol.app.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
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

import dbcol.app.AppContext;
import dbcol.app.database.enums.DBType;
import dbcol.app.ui.contents.DataSourceEditorComponts;
import dbcol.app.ui.listeners.dsEditor.DsSaveBtnListener;
import dbcol.app.ui.listeners.dsEditor.TestConnBtnListener;

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
	
	/**
	 * 组件
	 */
	private DataSourceEditorComponts uiComponents;
	
	private DsSaveBtnListener dsSaveBtnListener;
	
	public DataSourceEditor(Shell parent) {
		super(parent);
		uiComponents = new DataSourceEditorComponts();
		dsSaveBtnListener = new DsSaveBtnListener(uiComponents);
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
		uiComponents.setDsNameText(dsNameText);
		
		
		//数据库类型 
		Label dbTypeLabel = new Label(container, SWT.NONE);
		dbTypeLabel.setText("数据库类型：");
		dbTypeLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		dbType = new CCombo(container, SWT.BORDER | SWT.READ_ONLY);
//		dbType.setItems(new String[] {"oracle", "mysql", "sqlserver"});
		dbType.setItems(DBType.getElementsNameArr());
		dbType.setLayoutData(new GridData(GridData.FILL, SWT.CENTER, false, false, 1, 1));
		uiComponents.setDbType(dbType);
		
		//jdbc URL： 供专业人士使用
		Label urlLable = new Label(container, SWT.NONE);
		urlLable.setText("JDBC_URL：");
		urlLable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		urlText = new Text(container, SWT.BORDER);
		//水平方向填充整个区域，垂直居中
		urlText.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		uiComponents.setUrlText(urlText);
		
		/****************普通配置组： 用于一般用户进行数据库连接配置*********************/
		createCommonDSConfigGroup(container);

		infoLabel = new Label(container, SWT.NONE);
		infoLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
//		infoLabel.setText(" ");
		uiComponents.setInfoLabel(infoLabel);
		
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
		
		testConnBtn.addMouseListener(new TestConnBtnListener(uiComponents));
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
		System.out.println(AppContext.getAppPath());
		this.dsSaveBtnListener.saveDataSourceConfig();
		System.out.println("保存数据源配置................");
		super.okPressed();
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
		uiComponents.setHostText(hostText);
		//端口
		Label portLabel = new Label(commonGroup, SWT.NONE);
		portLabel.setText("端口：");
		portLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		portText = new Text(commonGroup, SWT.BORDER);
		portText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		uiComponents.setPortText(portText);
		//数据库
		Label dataBaseLabel = new Label(commonGroup, SWT.NONE);
		dataBaseLabel.setText("数据库：");
		dataBaseLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		dataBaseText = new Text(commonGroup, SWT.BORDER);
		dataBaseText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		uiComponents.setDataBaseText(dataBaseText);
		//用户名
		Label userNameLabel = new Label(commonGroup, SWT.NONE);
		userNameLabel.setText("用户名：");
		userNameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		userNameText = new Text(commonGroup, SWT.BORDER);
		userNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		uiComponents.setUserNameText(userNameText);
		//密码
		Label passwordLabel = new Label(commonGroup, SWT.NONE);
		passwordLabel.setText("密码：");
		passwordLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		passwordText = new Text(commonGroup, SWT.BORDER);
		passwordText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		uiComponents.setPasswordText(passwordText);
	}
}
