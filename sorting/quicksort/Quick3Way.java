package sorting.quicksort;

import edu.princeton.cs.algs4.StdRandom;
import sorting.elementarysorts.Example;

public class Quick3Way {
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	public static void sort(Comparable[] a, int lo, int hi) {
		if (lo >= hi)
			return;
		int lt = lo;
		int i = lo + 1;
		int gt = hi;
		Comparable v = a[lo];
		while (i <= gt) {
			int cmp = v.compareTo(a[i]);
			if (cmp < 0) {
				Example.exch(a, gt, i);
				gt--;
			} else if (cmp > 0) {
				Example.exch(a, i, lt);
				lt++;
				i++;
			} else {
				// equals
				i++;
			}
		}
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}
}
