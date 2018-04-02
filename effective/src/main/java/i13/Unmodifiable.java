package i13;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Unmodifiable {

	private static final String[] PRIVATE_VALUES = { "A", "B", "C" };
	public static final List<String> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));

	public static final String[] values(){
		return PRIVATE_VALUES.clone();
	}
	
	public static void main(String[] args) {
		List<String> asList = Arrays.asList(PRIVATE_VALUES);
		for (String string : VALUES) {
			System.out.println(string);
		}
	}
}
