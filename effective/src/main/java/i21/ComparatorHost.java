package i21;

import java.io.Serializable;
import java.util.Comparator;

public class ComparatorHost {

	public static final Comparator<String> STRING_LENGTH_COMPARATOR = new StrLen();

	private static class StrLen implements Comparator<String>,Serializable {
		@Override
		public int compare(String o1, String o2) {
			return o1.length() - o2.length();
		}
	}
}
