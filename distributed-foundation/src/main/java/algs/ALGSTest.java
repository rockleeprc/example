package algs;

import org.junit.Test;

public class ALGSTest {

	@Test
	public void testStack() {
		Stack<String> stack = new Stack<String>();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		// System.out.println(stack.size());
		for (String s : stack) {
			System.out.println(s);
		}
		System.out.println("pop");
		stack.pop();
		// System.out.println(stack.size());
		for (String s : stack) {
			System.out.println(s);
		}
	}
}
