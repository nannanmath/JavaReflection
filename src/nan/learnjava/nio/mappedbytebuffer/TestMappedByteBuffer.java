package nan.learnjava.nio.mappedbytebuffer;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import org.junit.Test;

public class TestMappedByteBuffer {
	@Test
	public void testFileMapping() {
		try {
			RandomAccessFile raf = new RandomAccessFile("TestMappedByteBuffer.txt",
					"rws");
			FileChannel fc = raf.getChannel();
			MappedByteBuffer buf = fc.map(MapMode.READ_WRITE, 
					2, 6);
			buf.put(0, (byte)97);
			buf.put(1, (byte)98);
			buf.put(2, (byte)99);
			fc.close();
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
