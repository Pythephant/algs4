package sorting.elementarysorts;

public class Selection {
	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int i=0;i<N;i++) {
			int min = i;
			for(int j=i; j<N; j++) {
				if(Example.less(a[j],a[min]))
					min = j;
			}
			Example.exch(a, min, i);
		}
	}
}
