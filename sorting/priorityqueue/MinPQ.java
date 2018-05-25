package sorting.priorityqueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

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
		n = keys.length;
		pq = (Key[]) new Object[n + 1];
		for (int i = 0; i < keys.length; i++) {
			pq[i + 1] = keys[i];
		}
		for (int k = n / 2; k >= 1; k--) {
			sink(k);
		}
		assert isMinHeap();
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	public Key min() {
		if (isEmpty())
			throw new NoSuchElementException("The priority Queue is empty!");
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
		if (n == pq.length - 1)
			resize(2 * n);
		n++;
		pq[n] = key;
		swim(n);
		assert isMinHeap();
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
		while (2*k <= n) {
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

	private boolean isMinHeap() {
		return isMinHeap(1);
	}

	private boolean isMinHeap(int k) {
		if (k > n)
			return true;
		int left = 2 * k;
		int right = 2 * k + 1;
		if (left <= n && greater(k, left))
			return false;
		if (right <= n && greater(k, right))
			return false;
		return isMinHeap(left) && isMinHeap(right);
	}

	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return new HeapIterator();
	}
	private class HeapIterator implements Iterator<Key>{

		private MinPQ<Key> copy;
		
		public HeapIterator() {
			if (comparator==null)
				copy = new MinPQ(size());
			else
				copy = new MinPQ(size(), comparator);
			for(int k=1;k<size()+1;k++)
				copy.insert(pq[k]);
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return !copy.isEmpty();
		}

		@Override
		public Key next() {
			// TODO Auto-generated method stub
			return copy.delMin();
		}
		
	}
	
	public static void main(String[] args) {
		Double[] arr = new Double[10];
		MinPQ<Double> pq = new MinPQ<>(new Comparator<Double>() {

			@Override
			public int compare(Double o1, Double o2) {
				// TODO Auto-generated method stub
				return -o1.compareTo(o2);
			}
		}) ;
		
		MinPQ<Double> pqMin = new MinPQ<>();
		
		for(int i=0;i<arr.length;i++) {
			arr[i] = (double) StdRandom.uniform(arr.length);
			pq.insert(arr[i]);
			pqMin.insert(arr[i]);
		}
		StdOut.println(Arrays.toString(arr));
		Iterator<Double> it = pq.iterator();
		while(it.hasNext()) {
			StdOut.print(it.next()+" ");
		}
		StdOut.println();
		Iterator<Double> itMin = pqMin.iterator();
		while(itMin.hasNext()) {
			StdOut.print(itMin.next()+" ");
		}
		
		
	}
}
