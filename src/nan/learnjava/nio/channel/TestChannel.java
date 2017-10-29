package nan.learnjava.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class TestChannel {
	@Test
	public void copyFile() {
		try {
			FileInputStream fis = new FileInputStream("TestChannelSrc.txt");
			FileChannel fcin = fis.getChannel();
			FileOutputStream fos = new FileOutputStream("TestChannelDst.txt");
			FileChannel fcout = fos.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(1024 * 50);
			while(fcin.read(buf) != -1) {
				buf.flip();
				fcout.write(buf);
				buf.clear();
			}
			fcin.close();
			fis.close();
			fcout.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
