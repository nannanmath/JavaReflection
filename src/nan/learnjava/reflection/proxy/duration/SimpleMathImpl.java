package nan.learnjava.reflection.proxy.duration;

public class SimpleMathImpl implements SimpleMath {

	@Override
	public int add(int a, int b) {
		return a + b;
	}

	@Override
	public int sub(int a, int b) {
		return a - b;
	}

}
