package searching.symbletables;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class SequentialSearchST<Key, Value> {
	private Node first;
	private int n;

	public SequentialSearchST() {

	}

	public void put(Key key, Value value) {
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.value = value;
				return;
			}
		}
		// search the key failed, add it
		// before the first;
		first = new Node(key, value, first);
		n++;
	}

	public Value get(Key key) {
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key))
				return x.value; // search hit
		}
		return null; // search miss
	}

	public void delete(Key key) {
		if (isEmpty())
			return;
		if (key.equals(first.key)) {
			first = first.next;
			n--;
		} else {
			for (Node x = first; x.next != null; x = x.next) {
				if (key.equals(x.next.key)) {
					x.next = x.next.next;
					n--;
				}
			}
		}

	}

	public boolean contains(Key key) {
		for (Node x = first; x != null; x = x.next)
			if (key.equals(x.key))
				return true;
		return false;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	public Iterable<Key> keys() {
		MinPQ<Key> keys = new MinPQ<>();
		for (Node x = first; x != null; x = x.next)
			keys.insert(x.key);
		return keys;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Node x = first; x != null; x = x.next) {
			s.append("(" + x.key + "," + x.value + ")->");
		}
		return s.toString();
	}

	private class Node {
		private Key key;
		private Value value;
		private Node next;

		public Node(Key key, Value value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
		StdOut.println("contains A?" + st.contains("A"));
		System.out.println("get A:" + st.get("A"));
		st.delete("A");
		System.out.println("st:" + st);
		st.put("A", 1);
		st.put("a", 11);
		System.out.println(st);
		st.put("A", 2);
		System.out.println("st:" + st.size() + " items:" + st);
		st.delete("a");
		System.out.println("st:" + st.size() + " items:" + st);
	}
}
