package dbcol.app.test;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DisplayDemo {
	private String title ;
	private int width, height;
	private LayoutDemo demo;
	
	public DisplayDemo(String title, int width, int height, LayoutDemo demo) {
		super();
		this.title = title;
		this.width = width;
		this.height = height;
		this.demo = demo;
	}

	public void show() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(width, height);
		shell.setText(title);
		
		demo.layout(shell);
		
		shell.open();
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
		shell.dispose();
	}
}