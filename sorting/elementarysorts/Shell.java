package sorting.elementarysorts;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Shell {
	public static void sort(Comparable[] a) {
		int h = 1;
		int N = a.length;
		while (h < N / 3)
			h = 3 * h + 1;
		while (h >= 1) {
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && Example.less(a[j], a[j - h]); j -= h)
					Example.exch(a, j, j - h);
			}
			h = h / 3;
		}
	}
	
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		Integer[] a = new Integer[N];
		for(int i =0;i<N;i++)
			a[i] = StdRandom.uniform(N);
		StdOut.println(Arrays.toString(a));
		Shell.sort(a);
		StdOut.println(Arrays.toString(a));
		
	}
}
