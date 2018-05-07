package algs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {

	private Node first;
	private int N;

	private static class Node<T> {
		String item;
		Node<T> next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}

	public void push(String item) {
		Node oldNode = first;
		first = new Node();
		first.next = oldNode;
		first.item = item;
		N++;
	}

	public T pop() {
		T item = (T) first.item;
		first = first.next;
		N--;
		return item;
	}

	public Iterator<T> iterator() {
		return new ListIterator<T>(first);
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<T> implements Iterator<T> {
		private Node<String> current;

		public ListIterator(Node<String> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			T item = (T) current.item;
			current = current.next;
			return item;
		}
	}

	public static void main(String[] args) {
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
