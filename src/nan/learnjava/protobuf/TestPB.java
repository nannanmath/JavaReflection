package nan.learnjava.protobuf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import com.example.tutorial.AddressBookProtos.Person;
import com.example.tutorial.AddressBookProtos.Person.PhoneNumber;
import com.example.tutorial.AddressBookProtos.Person.PhotoType;

public class TestPB {
	@Test
	public void TestProtobuf() throws Exception {
		PhoneNumber number = PhoneNumber.newBuilder()
				.setNumber("12345678")
				.setType(PhotoType.MOBILE)
				.build();
		
		Person p = Person.newBuilder()
		.setName("tom")
		.setId(100)
		.setEmail("abc@hotmail.com")
		.addPhone(number)
		.build();
		
		// serialize.
		// Protobuf.
		FileOutputStream fos1 = new FileOutputStream("TestSePB.dat");
		long start = System.nanoTime();
		p.writeTo(fos1);
		System.out.println("" + (System.nanoTime() - start));
		fos1.close();
		
		// Java build-in.
		FileOutputStream fos2 = new FileOutputStream("TestSeJbuid-in.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos2);
		start = System.nanoTime();
		oos.writeObject(p);
		System.out.println("" + (System.nanoTime() - start));
		oos.close();
		fos2.close();
		
		// Deserialize.
		Person pp = Person.parseFrom(new FileInputStream("TestSePB.dat"));
	}
}
