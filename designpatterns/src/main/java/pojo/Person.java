package pojo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person implements Serializable, Cloneable {
	private static final long serialVersionUID = -2202583623823238756L;
	private String name;
	private int age;
	private Book book;

	public Person() {
		super();
	}

	public Person(String name, int age, Book book) {
		super();
		this.name = name;
		this.age = age;
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * 浅clone
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Object deepClone() throws IOException, ClassNotFoundException {
		// 先写入
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);
		// 后读取
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		
		return ois.readObject();

	}

}
