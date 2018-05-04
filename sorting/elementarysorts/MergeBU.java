package sorting.elementarysorts;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class MergeBU {
	private static Comparable[] aux;

	public static void merge(Comparable[] a, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				// first half subarray has already merged
				a[k] = aux[j];
				j++;
			}else if(j>hi) {
				//second half subarray has already merged
				a[k] = aux[i];
				i++;
			}else if(Example.less(aux[i],aux[j])) {
				//a[i] is smaller, so merge a[i] into a
				a[k] = aux[i];
				i++;
			}else {
				a[k] = aux[j];
				j++;
			}
		}
	}
	
	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		for (int sz = 1; sz < a.length; sz += sz) {
			for (int lo = 0; lo < a.length - sz; lo += 2 * sz) {
				int mid = lo + sz - 1;
				merge(a, lo, mid, Math.min(lo + 2 * sz - 1, a.length - 1));
			}
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
