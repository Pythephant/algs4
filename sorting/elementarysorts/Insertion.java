package sorting.elementarysorts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class Insertion {
	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int i=0; i<N; i++) {
			for (int j=i; j>0 && Example.less(a[j], a[j-1]); j--) {
				Example.exch(a, j, j-1);
			}
		}
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("data/tiny.txt"));
		String[] a = sc.nextLine().split(" ");
		Example.show(a);
		Insertion.sort(a);
		assert Example.isSorted(a);
		Example.show(a);
	}
}
