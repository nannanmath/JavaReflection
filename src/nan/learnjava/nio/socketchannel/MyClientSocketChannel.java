package nan.learnjava.nio.socketchannel;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class MyClientSocketChannel {
	public static void main(String[] args) throws Exception {
		// selector.
		Selector sel = Selector.open();
		// SocketChannel.
		SocketChannel sc = SocketChannel.open();
		InetSocketAddress addr = new InetSocketAddress("localhost", 8888);
		sc.connect(addr);
		sc.configureBlocking(false); // unblocking.
		// regist to selector.
		sc.register(sel, SelectionKey.OP_READ);
		
		// send by new thread
		new sender(sc).start();
		
		while(true) {
			// select.
			sel.select(); 
			// read bytes from SocketChannel.
			ByteBuffer buf = ByteBuffer.allocate(1024);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while(sc.read(buf) != 0) {
				buf.flip();
				baos.write(buf.array(), 0, buf.limit());
				buf.clear();
			}
			System.out.println(new String(baos.toByteArray()));
			baos.close();
		}
		
	}
	
}
