package nan.learnjava.reflection.buildfromfile;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class BuildFromFile {
	public static void main(String[] args) throws Exception {
		InputStream is = ClassLoader.getSystemResourceAsStream("objects.info");
		//byte[] bytes = new byte[is.available()];
		//is.read(bytes);
		//System.out.println(new String(bytes));
		
		Properties prop = new Properties();
		prop.load(is);
		is.close();
		//
		String objClass = prop.getProperty("object.class");
		Class clazz = Class.forName(objClass);
		Object obj = clazz.newInstance();
		// name
		String prop1Name = prop.getProperty("object.prop1.name");
		String prop1Value = prop.getProperty("object.prop1.value");
		Field f1 = clazz.getDeclaredField(prop1Name);
		if (f1.getType() == String.class) {
			f1.setAccessible(true);
			f1.set(obj, prop1Value);
		}
		// age
		String prop2Name = prop.getProperty("object.prop2.name");
		String prop2Value = prop.getProperty("object.prop2.value");
		Field f2 = clazz.getDeclaredField(prop2Name);
		if (f2.getType() == int.class) {
			int i = Integer.parseInt(prop2Value);
			f2.setAccessible(true);
			f2.set(obj, i);
		}
	}

}
