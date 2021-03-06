package decorator.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class IODecoratorTest {
	@Test
	public void toLowerCase() {
		InputStream is = null;
		try {
			is = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream(new File("G:\\uppercase.txt"))));
			int c;
			while ((c = is.read()) != -1) {
				System.out.println((char) c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
