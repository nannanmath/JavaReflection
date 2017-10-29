package nan.learnjava.nio.socketchannel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class MyServerSocketChannel {

	public static void main(String[] args) {
		// selector.
		Selector sel = null;
		try {
			sel = Selector.open();
			// ServerSocketChannel.
			ServerSocketChannel ssc = ServerSocketChannel.open();
			InetSocketAddress addr = new InetSocketAddress("localhost", 8888);
			ssc.bind(addr); // bind to IP + port.
			ssc.configureBlocking(false); // unblocking.
			// regist to selector.
			ssc.register(sel, SelectionKey.OP_ACCEPT);
			System.out.println("Server start!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			// select
			try {
				sel.select();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// get keys in selection set.
			Set<SelectionKey> keys = sel.selectedKeys();
			for (SelectionKey k : keys) {
				try {
					if (k.isAcceptable()) { // accept.
						// get serversocketchannel in key.
						ServerSocketChannel ssc0 = (ServerSocketChannel) (k
								.channel());
						// accept for socketchannel.
						SocketChannel sc = null;
						sc = ssc0.accept();
						// regist to selector.
						sc.configureBlocking(false);
						sc.register(sel, SelectionKey.OP_READ);
					} else if (k.isReadable()) {
						// get SocketChannel.
						SocketChannel sc0 = (SocketChannel) (k.channel());
						// get info from SocketChannel.
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ByteBuffer buf = ByteBuffer.allocate(1024);
						while (sc0.read(buf) != 0) {
							buf.flip();
							baos.write(buf.array(), 0, buf.limit());
							buf.clear();
						}
						String strRead = new String(baos.toByteArray());
						System.out.println("Client:"
								+ getSocketInfo(sc0.socket()) + "says: "
								+ strRead);
						// write info to SocketChannel.
						String strWrite = "Server forward: " + strRead;
						byte[] bytes = strWrite.getBytes();
						buf = ByteBuffer.allocate(bytes.length);
						buf.put(bytes);
						buf.flip();
						sc0.write(buf);
						buf.clear();
					}
				} catch (IOException e) {
					k.cancel();
					if (k.channel() instanceof SocketChannel) {
						Socket s = (((SocketChannel)k.channel()).socket());
					System.out.println("Client:[" + getSocketInfo(s) +  "] was getting off line." );
					}
				}
			}
			keys.clear();
		}
	}

	private static String getSocketInfo(Socket socket) {
		InetSocketAddress addr = (InetSocketAddress) (socket
				.getRemoteSocketAddress());
		String ip = addr.getAddress().getHostAddress();
		String port = addr.getPort() + "";
		return "[" + ip + ":" + port + "]";
	}

}
