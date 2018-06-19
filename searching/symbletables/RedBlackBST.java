package searching.symbletables;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;

	private class Node {
		private Key key;
		private Value value;
		private Node left;
		private Node right;
		private int size;
		private boolean color;

		public Node(Key key, Value value, int size, boolean color) {
			this.key = key;
			this.value = value;
			this.size = size;
			this.color = color;
		}
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.size;
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("Key is null!");
		return get(root, key);

	}

	private Value get(Node x, Key key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp > 0)
				x = x.right;
			else if (cmp < 0)
				x = x.left;
			else
				return x.value;
		}
		return null;
	}

	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException("Key is null!");
		return get(key) != null;
	}

	public void put(Key key, Value value) {
		if (key == null || value == null)
			throw new IllegalArgumentException("the key or value is null!");
		put(root, key, value);
		root.color = BLACK;
	}

	private Node put(Node h, Key key, Value value) {
		if (h == null)
			return new Node(key, value, 1, RED);
		int cmp = key.compareTo(key);
		if (cmp < 0) {
			h.left = put(h.left, key, value);
		} else if (cmp > 0) {
			h.right = put(h.right, key, value);
		} else {
			h.value = value;
		}
		return balance(h);
	}

	private Node balance(Node h) {
		if (isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))
			flipColors(h);
		h.size = 1 + size(h.left) + size(h.right);
		return h;
	}

	private void flipColors(Node h) {
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}

	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		return x;
	}

	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		return x;
	}

	private boolean isRed(Node x) {
		if (x == null)
			return false;
		return x.color == RED;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public Key min() {
		if (isEmpty())
			throw new NoSuchElementException("calls min() with empty symbol table");
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null)
			return x;
		return min(x.left);
	}

	public Key max() {
		if (isEmpty())
			throw new NoSuchElementException("calls max with empty symbol table");
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null)
			return x;
		else
			return max(x.right);
	}

	public Key floor(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument key is null");
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
		if (cmp < 0) {
			return floor(x.left, key);
		} else if (cmp == 0) {
			return x;
		} else {
			Node t = floor(x.right, key);
			if (t == null)
				return x;
			else
				return t;
		}
	}

	public Key ceiling(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument is null");
		Node x = ceiling(root, key);
		if (x == null)
			return null;
		else
			return x.key;
	}

	private Node ceiling(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			Node t = ceiling(x.left, key);
			if (t == null)
				return x;
			else
				return t;
		} else if (cmp == 0) {
			return x;
		} else {
			return ceiling(x.right, key);
		}

	}

	public Key select(int k) {
		if (k < 0 || k >= size()) {
			throw new IllegalArgumentException("argument to select is illegal");
		}
		Node x = select(root, k);
		return x.key;
	}

	private Node select(Node x, int k) {
		if (x == null)
			return null;
		int t = size(x.left);
		if (k > t)
			return select(x.left, k);
		else if (k < t)
			return select(x.right, k - t - 1);
		else
			return x;
	}

	public int rank(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to rank is null");
		return rank(root, key);
	}

	private int rank(Node x, Key key) {
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return rank(x.left, key);
		else if (cmp > 0)
			return 1 + size(x.left) + rank(x.right, key);
		else
			return size(x.left);
	}

	public void deleteMin() {
		if (isEmpty())
			throw new NoSuchElementException("Tree is already empty");
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = deleteMin(root);
		if (!isEmpty())
			root.color = BLACK;
	}

	private Node deleteMin(Node h) {
		if (h.left == null)
			return null;

		if (!isRed(h.left) && !isRed(h.left.left))
			h = moveRedLeft(h);

		h.left = deleteMin(h.left);
		return balance(h);
	}

	private Node moveRedLeft(Node h) {
		// assume that the h is red! h.left is black, and h.left.left is black
		flipColors(h);
		if (isRed(h.right.left)) {
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}

	public void deleteMax() {
		if (isEmpty())
			throw new NoSuchElementException("Tree is already empty");
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = deleteMax(root);
		if (!isEmpty())
			root.color = BLACK;
	}

	private Node deleteMax(Node h) {
		if (isRed(h.left))
			h = rotateLeft(h);
		if (h.right == null)
			// h.left must be black,because if it's red i has been rotated left
			// if h.right is null ,than h.left must be null. (balanced limitition)
			return null;
		if (!isRed(h.right) && !isRed(h.right.left))
			moveRedRight(h);
		h.right = deleteMax(h.right);
		return balance(h);
	}

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("Argument key must not be null");
		if (!contains(key))
			return;
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = delete(root, key);
		if (!isEmpty())
			root.color = BLACK;

	}

	private Node delete(Node h, Key key) {
		if (key.compareTo(h.key) < 0) {
			if (!isRed(h.left) && !isRed(h.left.left))
				moveRedLeft(h);
			h.left = delete(h.left, key);
		} else {
			if (isRed(h.left))
				rotateLeft(h);
			if (key.compareTo(h.key) == 0 && h.right == null) {
				return null;
			}
			if (!isRed(h.right) && !isRed(h.right.left))
				h = moveRedRight(h);
			if (key.compareTo(h.key) == 0) {
				Node x = min(h.right);
				h.key = x.key;
				h.value = x.value;
				h.right = deleteMin(h.right);
			} else {
				h.right = delete(h.right, key);
			}
		}
		return balance(h);
	}

	private Node moveRedRight(Node h) {
		// assume that the h is red, and h.right, h.right.left is black
		// which is h.left is a 2 node
		flipColors(h);
		if (isRed(h.left.left)) {
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}

	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		if (lo == null || hi == null)
			throw new IllegalArgumentException("key of lo or hi is null");
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
		Queue<Node> queue = new Queue<>();
		Queue<Key> levelKeys = new Queue<>();
		queue.enqueue(root);
		while(!queue.isEmpty()) {
			Node x = queue.dequeue();
			if(x == null)
				continue;
			levelKeys.enqueue(x.key);
			queue.enqueue(x.left);
			queue.enqueue(x.right);
		}
		return levelKeys;
	}

	
	public static void main(String[] args) {
		
	}
}
