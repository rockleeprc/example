package exam.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SynMap {

	@Test
	public void t(){
		Map<Integer, Integer> synMap = Collections.synchronizedMap(new HashMap<Integer,Integer>());
	}
}
