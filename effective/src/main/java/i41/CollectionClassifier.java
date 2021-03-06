package i41;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionClassifier {

	public static String classify(Set<?> set) {
		return "Set";
	}

	public static String classify(List<?> list) {
		return "List";
	}

	public static String classify(Collection<?> collection) {
		return "Collection";
	}

	public static void main(String[] args) {
		Collection<?>[] collections = { new HashSet<String>(), new ArrayList<String>(),
				new HashMap<String, String>().values() };
		//重载参数类型在编译时就确认了，为Collection类型
		for (Collection<?> c : collections) {
			System.out.println(classify(c));
		}
	}

}
