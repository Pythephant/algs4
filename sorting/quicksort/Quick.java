package sorting.quicksort;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import sorting.elementarysorts.Example;

public class Quick {
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	public static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	public static int partition(Comparable[] a, int lo, int hi) {
		Comparable pivot = a[lo];
		int i = lo;
		int j = hi + 1;
		while (true) {
//			while (Example.less(a[++i], pivot)) {
			while (Example.less(pivot, a[++i]) == false) {
				if (i >= j-1)
					break;
			}
//			while (Example.less(pivot, a[--j])) {
			while (Example.less(a[--j], pivot) == false) {
				if (j <= i)
					break;
			}
			if (i >= j)
				break;
			Example.exch(a, i, j);
		}
		Example.exch(a, lo, j);
		return j;
	}

	public static void main(String[] args) {
		Double[] a = new Double[Integer.parseInt(args[0])];
		for (int i = 0; i < a.length; i++) {
			a[i] = (double) StdRandom.uniform(3);
		}
		StdOut.println(Arrays.toString(a));
		sort(a);
		StdOut.println(Arrays.toString(a));
		assert Example.isSorted(a) == true;
	}
}
