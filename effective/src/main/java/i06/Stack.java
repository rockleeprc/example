package i06;

import java.util.Arrays;

public class Stack {
	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public Stack() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public Object pop() {
		if (size == 0)
			throw new EmptyStackException();
		// 只改动size的位置，elements[]中被pop的对象不会被gc，引用还存在
		return elements[--size];
	}

	public Object pop2() {
		if (size == 0)
			throw new EmptyStackException();
		Object result = elements[--size];
		//数组元素变成非活动部分的一部分，就手动清空
		elements[size] = null;
		return result;
	}

	/**
	 * Ensure space for at least one more element, roughly doubling the capacity
	 * each time the array needs to grow.
	 */
	private void ensureCapacity() {
		if (elements.length == size)
			elements = Arrays.copyOf(elements, 2 * size + 1);
	}
}
