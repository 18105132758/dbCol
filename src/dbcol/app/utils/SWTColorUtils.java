package dbcol.app.utils;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class SWTColorUtils {
	
	/**
	 * 获取指定颜色对应的Color实例
	 * @param swtColor
	 * @return
	 */
	public static Color getColor(int swtColor) {
		return Display.getCurrent().getSystemColor(swtColor);
	}
	
}
