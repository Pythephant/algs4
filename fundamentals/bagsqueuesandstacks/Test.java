package fundamentals.bagsqueuesandstacks;

import java.util.Scanner;

import edu.princeton.cs.algs4.StdIn;

public class Test {
	public static void main(String[] args) {
		ResizingArrayStack<Integer> ints = new ResizingArrayStack<>();
		ints.push(5);
		ints.push(3);
		ints.push(1);
		ints.push(10);
		for(int i :ints)
			System.out.println(i);
		
		System.out.println("pop:"+ints.pop());
		System.out.println("pop:"+ints.pop());
		for(int i :ints)
			System.out.println(i);
		
	}
}
