package fundamentals.deque_randomizedqueue;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<>();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			rq.enqueue(s);
		}
		Iterator<String> it = rq.iterator();
		int i = 0;
		while (it.hasNext() && i < num) {
			StdOut.println(it.next());
			i++;
		}
	}
}
