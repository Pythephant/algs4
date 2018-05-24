package sorting.priorityqueue;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import sorting.elementarysorts.Example;

public class HeapSort {
	public static void sort(Comparable[] a) {
		// construct the a to heap-construction
		int N = a.length - 1;
		for (int k = N / 2; k >= 0; k--) {
			sink(a, k, N);
		}
		// sort the heap, by swiching the topMax to the tail of the array
		while (N > 0) {
			Example.exch(a, 0, N);
			N--;
			sink(a, 0, N);
		}
	}

	public static void sink(Comparable[] a, int lo, int hi) {
		while (2 * lo + 1 <= hi) {
			int j = 2 * lo + 1;
			if (j < hi && Example.less(a[j], a[j + 1]))
				j++;
			if (Example.less(a[lo], a[j]) == false)
				break;
			Example.exch(a, lo, j);
			lo = j;
		}
	}
	
	public static void main(String[] args) {
		Double[] a = new Double[Integer.parseInt(args[0])];
		for(int i =0;i<a.length;i++) {
			a[i] = (double)StdRandom.uniform(a.length);
		}
		StdOut.println(Arrays.toString(a));
		sort(a);
		StdOut.println(Arrays.toString(a));
	}
}
