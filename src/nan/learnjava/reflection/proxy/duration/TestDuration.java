package nan.learnjava.reflection.proxy.duration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestDuration {

	public static void main(String[] args) {
		// Target object.
		SimpleMathImpl smi = new SimpleMathImpl();
		// Handler.
		DurationHandler dh = new DurationHandler(smi);
		// Interface list.
		Class[] clazzes = {SimpleMath.class};
		
		SimpleMath sm = (SimpleMath)Proxy.newProxyInstance(
							DurationHandler.class.getClassLoader(),
							clazzes,
							dh);
		System.out.println("add result: " + sm.add(1, 2));
		System.out.println("sub result: " + sm.sub(4, 2));
	}
	
	public static class DurationHandler implements InvocationHandler {
		private Object target;
		public DurationHandler(Object target) {
			this.target = target;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			long start = System.nanoTime();
			Object ret = method.invoke(target, args);
			long end = System.nanoTime();
			System.out.println(method.getName() + ":" + (end - start) + "ns");
			return ret;
		}
		
	}

}
