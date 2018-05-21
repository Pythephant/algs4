package sorting.quicksort;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;
import sorting.elementarysorts.Example;

public class SelectionInTwoSortedArray {
	public static Comparable kLargestIndex(Comparable[] a, Comparable[] b, int k){
		return kLargestIndex(a, 0, a.length-1, b, 0, b.length-1, k);
		
	}
	
	public static Comparable kLargestIndex(Comparable[] a,int la,int ha, Comparable[] b,int lb, int hb, int k){
		if(la>ha)
			return b[hb-k+1];
		if(lb>hb)
			return a[ha-k+1];
		if(k==1)
			if(Example.less(a[ha], b[hb]))
				return b[hb];
			else
				return a[ha];
		
		int bi = hb - (k-1)/2 > lb? hb - (k-1)/2:lb;
		int ai = ha - (k-1)/2 > la? ha - (k-1)/2:la;
		if(Example.less(a[ai], b[bi]))
			return kLargestIndex(a, la, ha, b, lb, bi-1, k-(hb-bi+1));
		else
			return kLargestIndex(a, la, ai-1, b, lb, hb, k-(ha-ai+1));
	}
	
	public static void main(String[] args) {
		Comparable[] a = new Integer[10];
		for(int i=0;i<a.length;i++){
			a[i] = StdRandom.uniform(10);
		}
		Integer[] b = {100};
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		System.out.println(kLargestIndex(a, b, 11));
	}
	
}
