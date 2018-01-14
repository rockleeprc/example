package foo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;

public class AliTest {
	/**
	 * 线城池使用ThreadPoolExecutor实现，控制线程数和请求队列的长度
	 */
	@Test
	public void test29() {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS,
				new LinkedBlockingDeque<Runnable>(1204 * 10));
	}

	/**
	 * list输出
	 */
	@Test
	public void test28() {
		List<String> arrayList = new ArrayList<String>();
		arrayList.add("A");
		arrayList.add("B");
		arrayList.add("C");
		arrayList.add("D");
		arrayList.add("E");

		arrayList.forEach(item -> System.out.println(item));

		arrayList.forEach(System.out::println);

		arrayList.forEach(item -> {
			if ("C".equals(item)) {
				System.out.println(item);
			}
		});

		arrayList.stream().filter(s -> s.contains("B") || s.contains("C")).forEach(System.out::println);

		arrayList.stream().filter(s -> s.contains("E")).findFirst().ifPresent(s -> System.out.println(s));
	}

	/**
	 * Map迭代优先使用Map.Entry<k,v><br/>
	 * jdk1.8使用Map.forEach()
	 */
	@Test
	public void test27() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1, 1);
		map.put(2, 2);
		// map.forEach((k, v) -> System.out.println(k + "-" + v));
		map.forEach((k, v) -> {
			if (1 == k) {
				System.out.println(k + "-" + v);
			}
		});
	}

	/**
	 * foreach时，只能remove第一元素，remove第二个时会ConcurrentModificationException<br/>
	 * 删除使用Iterator
	 */
	@Test
	public void test26() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		System.out.println(list);
		for (String e : list) {
			if ("2".equals(e)) {
				list.remove(e);
			}
		}
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			String item = iter.next();
			if ("2".equals(item)) {
				iter.remove();
			}
		}
		System.out.println(list);
	}

	/**
	 * Arrays.asList生成的List是固定大小的，不能crud
	 */
	@Test
	public void test25() {
		String[] arr = new String[] { "1", "2" };
		List<String> list = Arrays.asList(arr);
		System.out.println(list);
		arr[0] = "11";
		list.remove(1);
		System.out.println(list);
	}

	/**
	 * list.subList 生成的集合还是原来的<br/>
	 * list.subList
	 * 返回的是ArrayList中一个子类SubList，强转ArrayList会ClassCastException<br/>
	 * list.toArray() 只能返回Object[]，其它类型会ClassCastException
	 */
	@Test
	public void test24() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		List<Integer> subList = list.subList(0, 1);
		// System.out.println(subList);
		subList.add(4);
		subList.add(5);
		System.out.println(list);
		System.out.println(subList);
		Integer[] arr = new Integer[list.size()];
		list.toArray(arr);
		System.out.println(arr);
	}

	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
		@Override
		public DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		};
	};

	/**
	 * SimpleDateFormat是线程不安全的，使用ThreadLocal或DateUtils<br/>
	 * jdk1.8 使用DateTimeFormatter
	 */
	@Test
	public void test23() {
		DateFormat format = df.get();
		String date = format.format(new Date());
		System.out.println(date);

		DateTimeFormatter dtfmat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String str = now.format(dtfmat);
		System.out.println(str);

	}

	/**
	 * test commons-lang
	 * TODO、FIXME
	 */
	@Test
	public void test22() {
		// TODO
		// FIXME
		String bookId = ",";
		String[] split = StringUtils.split(bookId, ",");
		System.out.println("len=" + split.length);
		for (int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
		}
		// System.out.println(Arrays.toString(split));
	}

	/**
	 * finally{}中return会忽略到try{}中的return
	 */
	@Test
	public void test21() {
		System.out.println(m1(2));
	}

	public int m1(int i) {
		try {
			if (i == 1)
				return 1;
			if (i == 2)
				return 2;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			return -1;
		}
	}

	/**
	 * 自动拆箱的NPE
	 */
	@Test
	public void test20() {
		System.out.println(m2());
	}

	public int m2() {
		Integer i = null;
		return i;
	}

}
