package algs;

import java.util.NoSuchElementException;


public class Queue<String> {
	private Node first;
	private Node last;
	private int n;

	private static class Node<String> {
		String item;
		Node<String> next;
	}

	public void enqueue(String item) {
		Node<String> oldlast = last;
		last = new Node<String>();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last;
		n++;
	}

	public String dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		String item = (String) first.item;
		first = first.next;
		n--;
		if (isEmpty())
			last = null; // to avoid loitering
		return item;
	}

	public boolean isEmpty() {
		return first == null;
	}

}
