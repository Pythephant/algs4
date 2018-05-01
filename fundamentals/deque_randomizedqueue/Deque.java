package fundamentals.deque_randomizedqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

	private int N; // the size of the Deque
	private Node first; // reference to first node
	private Node last; // reference to last node

	/**
	 * inner class of the node
	 * 
	 * @author wujiakun
	 *
	 */
	private class Node {
		Item item;
		Node previous;
		Node next;
	}

	/**
	 * constructor
	 */
	public Deque() {
		N = 0;
		first = null;
		last = null;
	}

	/**
	 * 
	 * @return,true if the Deque is empty
	 */
	public boolean isEmpty() {
		return N == 0;
	}

	/**
	 * return the size of the Deque
	 * 
	 * @return
	 */
	public int size() {
		return N;
	}

	/**
	 * add the item to the first of th Deque
	 * 
	 * @param item
	 */
	public void addFirst(Item item) {
		if (item == null)
			throw new IllegalArgumentException();

		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		if (isEmpty()) {
			last = first;
		} else {
			oldFirst.previous = first;
		}
		N++;
	}

	/**
	 * add the item to the last of the Deque
	 * 
	 * @param item
	 */
	public void addLast(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.previous = oldLast;
		if (isEmpty()) {
			first = last;
		} else {
			oldLast.next = last;
		}
		N++;
	}

	/**
	 * remove the first item of the Deque
	 * 
	 * @return
	 */
	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException();
		Item item = first.item;
		if (first.next == null) {
			// only one node, set last to null and recollect the memory
			first = null;
			last = null;
		} else {
			first.next.previous = null;
			first = first.next;
		}
		N--;
		return item;
	}

	/**
	 * remove the last item of the Deque
	 * 
	 * @return
	 */
	public Item removeLast() {
		if (isEmpty())
			throw new NoSuchElementException();
		Item item = last.item;
		if (last.previous == null) {
			// only one node, set first to null
			first = null;
			last = null;
		} else {
			last.previous.next = null;
			last = last.previous;
		}
		N--;
		return item;

	}

	/**
	 * return the Iterator of the Deque
	 */
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	/**
	 * Iterator class of the Deque
	 * 
	 * @author wujiakun
	 *
	 */
	private class DequeIterator implements Iterator<Item> {

		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (current == null)
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public static void main(String[] args) {
		Deque<String> ds = new Deque<>();
		ds.addFirst("jason");
		ds.addFirst("jason2");
		StdOut.println(ds.removeLast());
		ds.addLast("jason1000");
		StdOut.println(ds.size() + "," + ds.isEmpty());
		for (String s : ds) {
			StdOut.print(s + " ");
		}
		StdOut.println();
		ds.removeFirst();
		ds.removeFirst();
		ds.addLast("jason-last");
		for (String s : ds) {
			StdOut.print(s + " ");
		}
		ds.removeLast();

	}
}
