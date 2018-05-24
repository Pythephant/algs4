package sorting.priorityqueue;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPQ<Key> implements Iterable<Key> {

	private Key[] pq;
	private int n;
	private Comparator<Key> comparator;

	public MinPQ(int capacity) {
		pq = (Key[]) new Object[capacity + 1];
		n = 0;
		comparator = null;
	}

	public MinPQ() {
		this(1);
	}

	public MinPQ(int capacity, Comparator<Key> comparator) {
		this(capacity);
		this.comparator = comparator;
	}

	public MinPQ(Comparator<Key> comparator) {
		this(1, comparator);
	}

	public MinPQ(Key[] keys) {

	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	public Key min() {
		return pq[1];
	}

	public Key delMin() {
		if (isEmpty())
			throw new NoSuchElementException("The Priority Queue is already empty!");
		Key temp = pq[1];
		exch(1, n);
		pq[n] = null;
		n--;
		sink(1);
		if (n > 0 && n < pq.length / 4)
			resize(pq.length / 2);
		assert isMinHeap();
		return temp;
	}

	public void insert(Key key) {

	}

	private void resize(int capacity) {
		assert capacity > n;
		Key[] temp = (Key[]) new Object[capacity + 1];
		for (int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}

	private void swim(int k) {
		while (k > 1 && greater(k / 2, k)) {
			exch(k / 2, k);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (k <= n) {
			int j = 2 * k;
			if (j < n && greater(j, j + 1))
				j++;
			if (greater(k, j) == false)
				break;
			exch(k, j);
			k = j;
		}
	}

	private boolean greater(int x, int y) {
		if (comparator == null) {
			return ((Comparable<Key>) pq[x]).compareTo(pq[y]) > 0;
		} else {
			return comparator.compare(pq[x], pq[y]) > 0;
		}
	}

	private void exch(int x, int y) {
		Key temp = pq[x];
		pq[x] = pq[y];
		pq[y] = temp;
	}

	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
