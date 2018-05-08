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
		int i = lo+1;
		int j = hi;
		while (true) {
			while (Example.less(a[++i], pivot)) {
				if (i == hi)
					break;
			}
			while (Example.less(pivot, a[--j])) {
				if (j == lo)
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
			a[i] = (double) StdRandom.uniform(a.length);
		}
		StdOut.println(Arrays.toString(a));
		sort(a);
		StdOut.println(Arrays.toString(a));
		assert Example.isSorted(a) == true;
	}
}
