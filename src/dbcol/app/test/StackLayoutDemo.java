package dbcol.app.test;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

public class StackLayoutDemo implements LayoutDemo{

	@Test
	public void test() {
		DisplayDemo demo = new DisplayDemo("StackLayout Demo", 240, 180, this);
		demo.show();
	}

	@Override
	public void layout(Shell shell) {
		shell.setLayout(new FillLayout());
		//创建容器 lc，位于界面左侧
		Composite lc = new Composite(shell, SWT.NONE);
		StackLayout layout = new StackLayout();
		lc.setLayout(layout);
		//在lc中 创建2个容器，分别容纳 2个堆栈页面，用于切换展示
		Composite lc_1 = createLc_1(lc);
		Composite lc_2 = createLc_2(lc);
		//默认展示lc_1
		layout.topControl = lc_1;	
		
		//创建容器 lr，位于界面右侧，内置按钮，手动触发lc切换内容
		Composite lr = new Composite(shell, SWT.NONE);
		lr.setLayout(new FillLayout(SWT.VERTICAL));
		Button b1 = new Button(lr, SWT.NONE);
		b1.setText("展示堆栈1");
		b1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				layout.topControl = lc_1;
				lc.layout();
			}
		});
		
		Button b2 = new Button(lr, SWT.NONE);
		b2.setText("展示堆栈2");
		b2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				layout.topControl = lc_2;
				lc.layout();
			}
		});
		
	}
	
	
	/**
	 * 	创建堆栈界面2
	 * @param parent
	 * @return
	 */
	public Composite createLc_2(Composite parent) {
		Composite lc_2 = new Composite(parent, SWT.BORDER);
		RowLayout layout = new RowLayout();
		layout.marginTop = 5;
		layout.spacing = 5;
		lc_2.setLayout(layout);
		
		Button b1 = new Button(lc_2, SWT.NONE);
		b1.setText("按钮1");
		b1.setLayoutData(new RowData(80, 40));
		Button b2 = new Button(lc_2, SWT.NONE);
		b2.setText("按钮2");
		b2.setLayoutData(new RowData(new Point(90, 40)));
		new Button(lc_2, SWT.NONE).setText("按钮3");;
		new Button(lc_2, SWT.NONE).setText("按钮4");;
		new Button(lc_2, SWT.NONE).setText("按钮5");;
		return lc_2;
	}
	
	/**
	 * 堆栈1
	 * @param parent
	 * @return
	 */
	public Composite createLc_1(Composite parent) {
		Composite lc_1 = new Composite(parent, SWT.BORDER);
		GridLayout layout = new GridLayout(2, true);
		layout.marginTop = 10;
		layout.marginBottom = 10;
		layout.marginRight = 10;
		layout.horizontalSpacing = 10;
		lc_1.setLayout(layout);
		
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 60;
		gd.heightHint = 100;
		gd.verticalSpan = 2;
		Button b1 = new Button(lc_1, SWT.NONE);
		b1.setText("按钮1");
		b1.setLayoutData(gd);
		Button b2 = new Button(lc_1, SWT.NONE);
		b2.setText("按钮2");
		b1.setLayoutData(new GridData());
		Button b3 = new Button(lc_1, SWT.NONE);
		b3.setText("按钮3");
		b3.setLayoutData(new GridData());
		
		return lc_1;
	}
}	