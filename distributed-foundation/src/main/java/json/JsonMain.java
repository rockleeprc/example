package json;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;
import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import entity.Person;

public class JsonMain {
	private static final int LOOP_SIZE = 900000; 
	
	private static Person init() {
		Person person = new Person();
		person.setName("mic");
		person.setAge(18);
		return person;
	}

	public static void main(String[] args) throws IOException {
		excuteWithJack();

		excuteWithFastJson();

		excuteWithProtoBuf();

		excuteWithHessian();
	}

	private static void excuteWithJack() throws IOException {
		Person person = init();

		ObjectMapper mapper = new ObjectMapper();
		byte[] writeBytes = null;
		
		Long start = System.currentTimeMillis();
		for (int i = 0; i < LOOP_SIZE; i++) {
			writeBytes = mapper.writeValueAsBytes(person);
		}
		System.out.println("jackson序列化：" + (System.currentTimeMillis() - start) + "ms : " + "总大小->" + writeBytes.length);

		Person person1 = mapper.readValue(writeBytes, Person.class);
		System.out.println(person1);
	}

	private static void excuteWithFastJson() throws IOException {
		Person person = init();
		String text = null;
		
		Long start = System.currentTimeMillis();
		for (int i = 0; i < LOOP_SIZE; i++) {
			text = JSON.toJSONString(person);
		}
		System.out.println(
				"fastjson序列化：" + (System.currentTimeMillis() - start) + "ms : " + "总大小->" + text.getBytes().length);

		Person person1 = JSON.parseObject(text, Person.class);
		System.out.println(person1);
	}

	private static void excuteWithProtoBuf() throws IOException {
		Person person = init();
		Codec<Person> personCodec = ProtobufProxy.create(Person.class, false);

		Long start = System.currentTimeMillis();
		byte[] bytes = null;
		for (int i = 0; i < LOOP_SIZE; i++) {
			bytes = personCodec.encode(person);
		}
		System.out.println("protobuf序列化：" + (System.currentTimeMillis() - start) + "ms : " + "总大小->" + bytes.length);

		Person person1 = personCodec.decode(bytes);
		System.out.println(person1);
	}

	private static void excuteWithHessian() throws IOException {
		Person person = init();

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HessianOutput ho = new HessianOutput(os);
		
		Long start = System.currentTimeMillis();
		for (int i = 0; i < LOOP_SIZE; i++) {
			ho.writeObject(person);
			if (i == 0) {
				System.out.println(os.toByteArray().length);
			}
		}
		System.out.println(
				"Hessian序列化：" + (System.currentTimeMillis() - start) + "ms : " + "总大小->" + os.toByteArray().length);

		HessianInput hi = new HessianInput(new ByteArrayInputStream(os.toByteArray()));
		Person person1 = (Person) hi.readObject();
		System.out.println(person1);
	}

}
