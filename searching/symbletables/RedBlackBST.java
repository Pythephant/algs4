package searching.symbletables;

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

	public void put(Key key, Value value) {
		if (key == null || value == null)
			throw new IllegalArgumentException("the key or value is null!");
		put(root, key, value);
	}
	
	private void put(Node h, Key key, Value value){
		
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

	}

	public Key max() {

	}

	public Key floor(Key key) {

	}

	public Key ceil(Key key) {

	}

	public Key select(int k) {

	}

	public int rank(Key key) {

	}

	public void deleteMin() {

	}

	public void deleteMax() {

	}

	public void delete(Key key) {

	}

	public Iterable<Key> keys() {

	}

	public Iterable<Key> keys(Key lo, Key hi) {

	}

	public Iterable<Key> levelOrder() {

	}

}
