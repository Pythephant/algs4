package fundamentals.bagsqueuesandstacks;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfString {
	private String[] a;
	private int N;
	
	public FixedCapacityStackOfString(int cap) {
		a = new String[cap];
	}
	
	public boolean isEmpty(){
		return N==0;
	}
	
	public void push(String item) {
		a[N] = item;
		N++;
	}
	
	public String pop() {
		N--;
		return a[N];
	}
	
	public int size() {
		return N;
	}
	
	
	//test client
	public static void main(String[] args) {
		FixedCapacityStackOfString s;
		s = new FixedCapacityStackOfString(100);
		while(!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) {
				s.push(item);
			}else if(!s.isEmpty()) {
				StdOut.print(s.pop() + " ");
			}
		}
		StdOut.print("(" + s.size() + " left on stack)");
	}
}
