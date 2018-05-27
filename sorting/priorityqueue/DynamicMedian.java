package sorting.priorityqueue;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class DynamicMedian<Key extends Comparable<Key>> {
	private MaxPQ<Key> max;
	private MinPQ<Key> min;
	
	public DynamicMedian() {
		max = new MaxPQ<>(1);
		min = new MinPQ<>();
	}
	
	public DynamicMedian(Key[] arr){
		this();
		for(int i=0;i<arr.length;i++){
			this.insert(arr[i]);
		}
	}
	
	public void insert(Key item){
		if(min.size()!=0 && item.compareTo(min.min())<0){
			max.insert(item);
		}else{
			min.insert(item);
		}
		while(min.size()-max.size()>1){
			Key temp = min.delMin();
			max.insert(temp);
		}
		
		while(max.size()-min.size()>0){
			Key temp = max.delMax();
			min.insert(temp);
		}
			
	}
	
	public Key findMedian(){
		return min.min();
	}
	
	public Key delMedian(){
		Key median = min.delMin();
		if(max.size()-min.size()>0){
			Key temp = max.delMax();
			min.insert(temp);
		}
		return median;
	}
	
	public int size(){
		return min.size()+max.size();
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		Integer[] arr = new Integer[n];
		for(int i=0;i<n;i++){
			arr[i] = StdRandom.uniform(n);
		}
		DynamicMedian<Integer> medianPQ = new DynamicMedian<>(arr);
		StdOut.println(Arrays.toString(arr));
		Arrays.sort(arr);
		StdOut.println(Arrays.toString(arr));
		StdOut.println(medianPQ.delMedian());
		StdOut.println(medianPQ.delMedian());
		StdOut.println(medianPQ.delMedian());
		
		
	}
	
}
