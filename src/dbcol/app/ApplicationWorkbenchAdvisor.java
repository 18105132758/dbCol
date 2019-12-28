package dbcol.app;

import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "dbCol.perspective";

    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }
    
    public void initialize(IWorkbenchConfigurer configurer) {
        super.initialize(configurer);
        //警用缓存
        configurer.setSaveAndRestore(false);
        System.out.println("警用缓存");
    }

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
}
