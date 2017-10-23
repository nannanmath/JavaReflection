package nan.learnjava.reflection.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import org.junit.Test;

import nan.learnjava.reflection.copyproperties.Person;

public class TestIntrospector {
	@Test
	public void test1() throws Exception {
		// Get bean info.
		BeanInfo b1 = Introspector.getBeanInfo(Person.class);
		// Get property descriptor.
		PropertyDescriptor[] pds = b1.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			System.out.println(pd.getName());
			System.out.println(pd.getReadMethod());
			System.out.println(pd.getWriteMethod());
		}
	}
}
