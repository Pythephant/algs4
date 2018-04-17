package fundamentals.bagsqueuesandstacks;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

public class Emulate {
	public static void main(String[] args) {
		Stack<String> ops = new Stack<>();
		Stack<Double> vals = new Stack<>();
		//HashSet to include the operator;
		Set<String> hs = new HashSet<String>();
		loadOperators(hs);
		//Scanner scanner = new Scanner(System.in);
		//while(scanner.hasNext()) {
		while(!StdIn.isEmpty()) {
			String word = StdIn.readString();
			System.out.println(word);
			if(word.equals("(")) {
				//Left (, do nothing
			}else if(hs.contains(word)) {
				ops.push(word);
			}else if(word.equals(")")){
				String op = ops.pop();
				double v = vals.pop();
				if(op.equals("+"))
					v = vals.pop() + v;
				else if (op.equals("-"))
					v = vals.pop() - v;
				else if (op.equals("*"))
					v = vals.pop() * v;
				else if (op.equals("/"))
					v = vals.pop() / v;
				else if (op.equals("sqrt"))
					v = Math.sqrt(v);
				vals.push(v);
			}else {
				//value
				vals.push(Double.parseDouble(word));
			}
		}
		System.out.println(vals.pop());
		//scanner.close();
	}
	
	public static void loadOperators(Set s) {
		s.add("+");
		s.add("-");
		s.add("*");
		s.add("/");
		s.add("sqrt");
	}
}