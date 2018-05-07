package algs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<String> implements Iterable<String> {

	private Node first;
	private int N;

	private static class Node<String> {
		String item;
		Node next;
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

	public String pop() {
		String item = (String) first.item;
		first = first.next;
		N--;
		return item;
	}

	public Iterator<String> iterator() {
		return new ListIterator<String>(first);
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<String> implements Iterator<String> {
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

		public String next() {
			if (!hasNext())
				throw new NoSuchElementException();
			String item = current.item;
			current = current.next;
			return item;
		}
	}

}
