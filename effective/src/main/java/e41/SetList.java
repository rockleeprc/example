package e41;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetList {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		Set<Integer> set = new TreeSet<Integer>();
		for (int i = -3; i < 3; i++) {
			list.add(i);
			set.add(i);
		}
		for (int i = 0; i < 3; i++) {
			/**
			 * 	重载+自动装箱引起的问题： E remove(int index);
			 *  根据索引删除
			 *  解决：
			 *  	list.remove(Integer.valueOf(i));
			 *  调用：
			 *  	boolean remove(Object o);
			 */
			list.remove(i);
			// boolean remove(Object o);
			set.remove(i);
		}
		System.out.println(list);
		System.out.println(set);
	}

}
