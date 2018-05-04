package sorting.elementarysorts;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class DutchNationalFlag {
	public int countColor = 0;
	public int countSwap = 0;
	public int[] a;

	public DutchNationalFlag(int n) {
		a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = StdRandom.uniform(3);
		}
		StdOut.println("Construct:" + Arrays.toString(a));
	}

	public DutchNationalFlag(int[] a) {
		this.a = a;
		StdOut.println("Construct:" + Arrays.toString(a));
	}

	public int color(int i) {
		countColor++;
		return a[i];
	}

	public void swap(int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		countSwap++;
	}

	public void sort() {
		int one = 0;
		int two = a.length;
		int i = 0;
		while (i < two) {
			int c = color(i);
			if (c == 0) {
				swap(i,one);
				one++;
				i++;
			}else if(c==1){
				i++;
			}else{
				two--;
				swap(i,two);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] a = new int[10];
		DutchNationalFlag dnf = new DutchNationalFlag(a);
		dnf.sort();
		StdOut.println(Arrays.toString(dnf.a));
		StdOut.println(dnf.countColor+","+dnf.countSwap);
	}
}
