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
		private int count;
		private boolean color;

		public Node(Key key, Value value, int count, boolean color) {
			this.key = key;
			this.value = value;
			this.count = count;
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
			return x.count;
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("Key is null!");
		return get(root, key);

	}
	
	private Value get(Node x, Key key) {
		
	}

	public void put(Key key, Value value) {

	}

	public boolean isEmpty() {

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
