package nan.learnjava.nio.socketchannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class sender extends Thread {
	private SocketChannel sc = null;

	public sender(SocketChannel sc) {
		this.sc = sc;
	}

	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String line = null;
			ByteBuffer buf = ByteBuffer.allocate(1024);
			while ((line = br.readLine()) != null) {
				buf.put(line.getBytes());
				buf.flip();
				sc.write(buf);
				buf.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
