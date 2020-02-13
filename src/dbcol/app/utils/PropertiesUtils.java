package dbcol.app.utils;

import java.util.ResourceBundle;

public class PropertiesUtils {
	
	public static void main(String[] args) {
		loadPro();
	}
	
	public static void loadPro() {
		ResourceBundle resource = ResourceBundle.getBundle("properties/dbtype");
		System.out.println(resource.getString("oracle"));	//不存在会报错
	}

}
