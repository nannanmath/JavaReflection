package nan.learnjava.copyproperties;

import java.lang.reflect.Method;

import org.junit.Test;

public class TestPropCopy {
	@Test
	public void testCopyProperty() {
		Person a = new Person();
		a.setName("tom");
		a.setAge(14);
		Person b = new Person();
		copyProperty(a, b);
	}
	
	
	/**
	 * Copy properties of a to b.
	 */
	public static void copyProperty(Object a, Object b) {
		Method[] ms = a.getClass().getDeclaredMethods();
		Class bclazz = b.getClass();
		for (Method m : ms) {
			String mname = m.getName();
			Class[] paramTypes = m.getParameterTypes();
			Class retType = m.getReturnType();
			
			if (mname.startsWith("get")
					&& (paramTypes == null || paramTypes.length == 0)
					&& retType != void.class) {
				String bmname = mname.replace("get", "set");
				try {
					Method bm = bclazz.getDeclaredMethod(bmname, retType);
					Object retVal = m.invoke(a);
					bm.invoke(b, retVal);
				}  catch (Exception e) {
					continue;
				}
				
			}
		}
	}
}
