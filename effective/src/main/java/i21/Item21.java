package i21;

import java.util.Arrays;
import java.util.Comparator;

import javax.swing.text.AbstractDocument.LeafElement;

public class Item21 {
	public static void main(String[] args) {
		String[] array = { "1234", "1", "123", "12", "12345" };
		Arrays.sort(array, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// 升序 return o1.length() - o2.length();
				// 降序
				return o2.length() - o1.length();
			}
		});
		System.out.println(Arrays.toString(array));
		Arrays.sort(array,ComparatorHost.STRING_LENGTH_COMPARATOR);
		System.out.println(Arrays.toString(array));
		
	}
}
