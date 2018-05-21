package sorting.priorityqueue;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import sorting.elementarysorts.Example;

public class MaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N;

	public MaxPQ(int maxN) {
		N = 0;
		pq = (Key[]) new Comparable[maxN + 1];
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public void insert(Key key) {

		ensureCapability();
		N++;
		pq[N] = key;
		swim(N);

	}

	public Key delMax() {
		Key max = pq[1];
		Example.exch(pq, 1, N);
		pq[N] = null;
		N--;
		sink(1);
		return max;
	}

	public void swim(int k) {
		while (k > 1 && Example.less(pq[k / 2], pq[k])) {
			Example.exch(pq, k / 2, k);
			k = k / 2;
		}
	}

	public void sink(int k) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && Example.less(pq[j], pq[j + 1]))
				j++;
			if (!Example.less(pq[k], pq[j]))
				break;
			Example.exch(pq, k, j);
			k = j;
		}
	}

	private void ensureCapability() {
		if (N >= pq.length - 1) {
			Key[] temp = (Key[]) new Comparable[2 * N + 1];
			for (int i = 0; i < pq.length; i++) {
				temp[i] = pq[i];
			}
			pq = temp;
		}
	}
	
	public static void main(String[] args) {
		int n = 10;
		Integer[] arr = new Integer[n];
		MaxPQ pq = new MaxPQ<Integer>(n);
		for(int i=0;i<arr.length;i++){
			arr[i] = StdRandom.uniform(n);
			pq.insert(arr[i]);
		}
		StdOut.println(Arrays.toString(arr));
		while(pq.isEmpty()==false){
			StdOut.print(pq.delMax()+"  ");
		}
		
	}
	
}
