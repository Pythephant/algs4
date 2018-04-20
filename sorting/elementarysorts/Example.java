package sorting.elementarysorts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Example {
	public static void sort(Comparable[] a) {
		Selection.sort(a);
	}
	
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w)<0;
	}
	
	public static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void show(Comparable[] a) {
		for (int i=0; i<a.length; i++) {
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
	}
	
	public static boolean isSorted(Comparable[] a) {
		for(int i=1; i<a.length; i++) {
			if(less(a[i], a[i-1]))
				return false;
		}
		return true;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("data/tiny.txt"));
		String[] a = sc.nextLine().split(" ");
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
