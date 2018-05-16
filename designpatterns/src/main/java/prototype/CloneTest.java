package prototype;

import java.io.IOException;

import org.junit.Test;

import pojo.Book;
import pojo.Person;

public class CloneTest {
	
	@Test
	public void testDeepClone() throws CloneNotSupportedException, ClassNotFoundException, IOException {
		Book b = new Book("Java", 14L);
		Person p = new Person("A", 13, b);

		Person clone = (Person) p.deepClone();
		System.out.println(p == clone);
		System.out.println(p.getBook() == clone.getBook());
	}
	@Test
	public void testClone() throws CloneNotSupportedException {
		Book b = new Book("Java", 14L);
		Person p = new Person("A", 13, b);

		Person clone = (Person) p.clone();
		System.out.println(p == clone);
		System.out.println(p.getBook() == clone.getBook());
	}
}
