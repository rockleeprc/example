package i18;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;

public class IntAsArrays {
	public static List<Integer> intAsList(int[] a) {
		return new AbstractList<Integer>() {
			@Override
			public Integer set(int index, Integer element) {
				Integer old = a[index];
				a[index] = element;
				return old;
			}

			@Override
			public Integer get(int index) {
				return a[index];
			}

			@Override
			public int size() {
				return a.length;
			}
		};
	}

	public static void main(String[] args) {
		int[] a = new int[10];
		for (int i = 0; i < a.length; i++)
			a[i] = i;
		List<Integer> list = intAsList(a);

		Collections.shuffle(list);
		System.out.println(list);
	}
}
