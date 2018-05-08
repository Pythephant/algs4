package sorting.elementarysorts;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class MergeHalfAux {
	// this version is thread dangerous!
	private static Comparable[] aux;

	public static int mergeAndCount(Comparable[] a, int lo, int mid, int hi) {
		for (int k = lo; k <= mid; k++) {
			aux[k] = a[k];
		}
		int count = 0;
		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				// first half subarray has already merged
				// a[k] = aux[j];
				j++;
			} else if (j > hi) {
				// second half subarray has already merged
				a[k] = aux[i];
				i++;
			} else if (Example.less(a[j], aux[i])) {
				// a[i] is smaller, so merge a[i] into a
				a[k] = a[j];
				count += mid - i + 1;
				j++;
			} else {
				a[k] = aux[i];
				i++;
			}
		}
		return count;
	}

	public static int sortAndCount(Comparable[] a) {
		aux = new Comparable[a.length];
		return sortAndCount(a, 0, a.length - 1);
	}

	public static int sortAndCount(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return 0;
		int mid = lo + (hi - lo) / 2;
		int lowCount = sortAndCount(a, lo, mid);
		int hiCount = sortAndCount(a, mid + 1, hi);
		return mergeAndCount(a, lo, mid, hi)+lowCount + hiCount;
	}

	public static void main(String[] args) {
		Double[] a = new Double[Integer.parseInt(args[0])];
		for (int i = 0; i < a.length; i++) {
			a[i] = (double) StdRandom.uniform(a.length);
		}
		StdOut.println(Arrays.toString(a));
		StdOut.println(sortAndCount(a));
		StdOut.println(Arrays.toString(a));

	}
}
