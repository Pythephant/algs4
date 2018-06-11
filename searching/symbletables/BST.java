package searching.symbletables;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;

	private class Node {
		private Key key;
		private Value value;
		private Node left;
		private Node right;
		private int count;

		public Node(Key key, Value value, int count) {
			this.key = key;
			this.value = value;
			this.count = count;
		}
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.count;
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("The input key is null!");
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.value;
	}

	public void put(Key key, Value value) {
		root = put(root, key, value);
	}

	private Node put(Node x, Key key, Value value) {
		if (x == null)
			return new Node(key, value, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, value);
		else if (cmp > 0)
			x.right = put(x.right, key, value);
		else
			x.value = value;
		x.count = size(x.left) + size(x.right) + 1;
		return x;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Key min() {
		if (isEmpty())
			throw new NoSuchElementException("the binary search tree is empty!");
		Node x = root;
		while (x.left != null) {
			x = x.left;
		}
		return x.key;
	}

	private Node min(Node x) {
		if (x == null)
			return null;
		if (x.left == null)
			return x;
		else
			return min(x.left);
	}

	public Key max() {
		if (isEmpty())
			throw new NoSuchElementException("the binary tree is empty!");
		Node x = root;
		while (x.right != null) {
			x = x.right;
		}
		return x.key;
	}

	public Key floor(Key key) {
		Node x = floor(root, key);
		if (x == null)
			return null;
		else
			return x.key;
	}

	private Node floor(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		else if (cmp < 0)
			return floor(x.left, key);
		else {
			// find if there is a node smaller than the key
			// true, return the smaller node; false return the x node
			Node t = floor(x.right, key);
			if (t == null)
				return x;
			else
				return t;
		}
	}

	public Key ceil(Key key) {
		Node x = ceil(root, key);
		if (x == null)
			return null;
		else
			return x.key;
	}

	private Node ceil(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		else if (cmp > 0)
			return ceil(x.right, key);
		else {
			// the key less than the node's key, then
			// if there is a node whose key is greater than the key in the left
			// subtree return the node , otherwise return the parent node
			Node t = ceil(x.left, key);
			if (t == null)
				return x;
			else
				return t;

		}
	}

	public Key select(int k) {
		return select(root, k).key;
	}

	private Node select(Node x, int k) {
		if (x == null)
			return null;
		int t = size(x.left);
		if (t > k)
			return select(x.left, k);
		else if (t < k)
			return select(x.right, k - t - 1);
		else
			return x;
	}

	public int rank(Key key) {
		return rank(root, key);
	}

	private int rank(Node x, Key key) {
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp > 0)
			return 1 + size(x.left) + rank(x.right, key);
		else if (cmp < 0)
			return rank(x.left, key);
		else
			return size(x.left);
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x == null)
			return null;
		if (x.left == null)
			return x.right;
		else {
			x.left = deleteMin(x.left);
			x.count = 1 + size(x.left) + size(x.right);
			return x;
		}
	}

	public void deleteMax() {
		root = deleteMax(root);
	}

	private Node deleteMax(Node x) {
		if (x == null)
			return null;
		if (x.right == null)
			return x.left;
		else {
			x.right = deleteMax(x.right);
			x.count = 1 + size(x.left) + size(x.right);
			return x;
		}
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);
		else {
			if (x.left == null)
				return x.right;
			else if (x.right == null)
				return x.left;
			else {
				Node t = x;
				x = min(t.right);
				x.right = deleteMin(t.right);
				x.left = t.left;
			}
		}
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}

	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		if (lo == null)
			throw new IllegalArgumentException("null argument of lo!");
		if (hi == null)
			throw new IllegalArgumentException("null argument of hi!");
		Queue<Key> queue = new Queue<>();
		keys(root, queue, lo, hi);
		return queue;
	}

	private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
		if (x == null)
			return;
		int cmpLo = lo.compareTo(x.key);
		int cmpHi = hi.compareTo(x.key);
		if (cmpLo < 0)
			keys(x.left, queue, lo, hi);
		if (cmpLo <= 0 && cmpHi >= 0)
			queue.enqueue(x.key);
		if (cmpHi > 0)
			keys(x.right, queue, lo, hi);

	}

	public Iterable<Key> levelOrder() {
		Queue<Key> keys = new Queue<>();
		Queue<Node> nodes = new Queue<>();
		nodes.enqueue(root);
		while (!nodes.isEmpty()) {
			Node x = nodes.dequeue();
			if (x == null)
				continue;
			keys.enqueue(x.key);
			nodes.enqueue(x.left);
			nodes.enqueue(x.right);

		}
		return keys;
	}
}
