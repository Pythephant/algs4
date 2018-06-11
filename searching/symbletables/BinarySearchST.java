package searching.symbletables;

import edu.princeton.cs.algs4.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private Key[] keys;
	private Value[] values;
	private int n;

	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Object[capacity];
	}

	public BinarySearchST() {
		this(1);
	}

	public void put(Key key, Value value) {
		int i = rank(key);
		// key is in the table
		if (i < n && key.compareTo(keys[i]) == 0) {
			values[i] = value;
			return;
		}
		for (int j = n; j > i; j--) {
			keys[j] = keys[j - 1];
			values[j] = values[j - 1];
		}
		keys[i] = key;
		values[i] = value;
		n++;
		if (n >= keys.length)
			resize(2 * n + 1);
	}

	private void resize(int capacity) {
		Key[] tempKeys = (Key[]) new Comparable[capacity];
		Value[] tempValues = (Value[]) new Object[capacity];
		for (int i = 0; i < n; i++) {
			tempKeys[i] = keys[i];
			tempValues[i] = values[i];
		}
		keys = tempKeys;
		values = tempValues;
	}

	public Value get(Key key) {
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < n && key.compareTo(keys[i]) == 0)
			return values[i];
		else
			return null;
	}

	public void delete(Key key) {
		if (isEmpty())
			return;
		else {
			int i = rank(key);
			if (size() > 1 && key.compareTo(keys[i]) == 0) {
				for (int j = i; j < n; j++) {
					keys[j - 1] = keys[j];
					values[j - 1] = values[j];
				}
			}
			keys[n - 1] = null;
			values[n - 1] = null;
			n--;
			if (n < keys.length / 4)
				resize(keys.length / 2);
		}

	}

	public boolean contains(Key key) {
		int i = rank(key);
		if (i < n && key.compareTo(keys[i]) == 0)
			return true;
		else
			return false;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	public Key min() {
		return keys[0];
	}

	public Key max() {
		return keys[n - 1];
	}

	public Key floor(Key key) {
		int i = rank(key);
		if (i == 0)
			return null;
		else if (i != n && key.compareTo(keys[i]) == 0)
			return keys[i];
		else
			return keys[i - 1];
	}

	public Key ceil(Key key) {
		int i = rank(key);
		if (i == n || isEmpty())
			return null;
		else
			return keys[i];

	}

	public int rank(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to rank() is null");
		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp > 0)
				lo = mid + 1;
			else if (cmp < 0)
				hi = mid - 1;
			else
				return mid;
		}
		return lo;
	}

	public Key select(int k) {
		if (k < 0 || k > n - 1)
			return null;
		else
			return keys[k];
	}

	public void deleteMin() {
		delete(min());
	}

	public void deleteMax() {
		delete(max());
	}

	public int size(Key lo, Key hi) {
		if (lo.compareTo(hi) > 0)
			return 0;
		if (contains(hi))
			return rank(hi) - rank(lo) + 1;
		else
			return rank(hi) - rank(lo);
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> q = new Queue<>();
		int loRank = rank(lo);
		int hiRank = rank(hi);
		for (int i = loRank; i < hiRank; i++) {
			q.enqueue(keys[i]);
		}
		if (contains(hi))
			q.enqueue(keys[hiRank]);
		return q;
	}

	public Iterable<Key> keys() {
		Queue<Key> q = new Queue<>();
		for (int i = 0; i < n; i++)
			q.enqueue(keys[i]);
		return q;
	}

	public static void main(String[] args) {
		BinarySearchST<String, Integer> obst = new BinarySearchST<>();
	}
}
