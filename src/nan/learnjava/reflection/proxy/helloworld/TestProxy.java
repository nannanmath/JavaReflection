package nan.learnjava.reflection.proxy.helloworld;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class TestProxy {
	@Test
	public void test1() {
		// Target object.
		HelloWorldServiceImpl s = new HelloWorldServiceImpl();
		// Handler.
		MyInvocationHandler h = new MyInvocationHandler(s);
		// Interface list.
		Class[] clazzes = {HelloWorldService.class};
		// Proxy object.
		HelloWorldService hws =
				(HelloWorldService)Proxy.newProxyInstance(
						HelloWorldServiceImpl.class.getClassLoader(),
						clazzes,
						h);
		hws.sayHello("Hello world!");
	}
	
	// Proxy handler.
	class MyInvocationHandler implements InvocationHandler {
		// Target object.
		private Object target;
		public MyInvocationHandler(Object target) {
			this.target = target;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			System.out.println("Hello Proxy!");
			return method.invoke(target, args);
		}
		
		
	}
}
