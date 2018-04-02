package i01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Item1 {
	@Test
	public void t2() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<String,List<String>> m = MapUtils.newInstance();
	}

	@Test
	public void t1() {
		Boolean t1 = Boolean.valueOf(true);
		Boolean t2 = Boolean.valueOf(true);
		System.out.println(t1.hashCode());
		System.out.println(t2.hashCode());
		System.out.println(t1 == t2);

	}
}
