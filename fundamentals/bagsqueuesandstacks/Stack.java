package fundamentals.bagsqueuesandstacks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class Stack<Item> implements Iterable<Item> {

	private Node first;
	private int N;
	
	private class Node{
		Item item;
		Node next;
	}
	public boolean isEmpty() {
		return first==null;
	}
	
	public int size() {
		return N;
	}
	
	public void push(Item item) {
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		N++;
	}
	
	public Item pop() {
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		
		private Node current = first;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current!=null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = "[";
		for (Item i : this){
			s += i + " ";
		}
		return s+"]";
	}
	
	//test main
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("data//tobe.txt"));
		Stack<String> s = new Stack<>();
		while (sc.hasNext()) {
			String item = sc.next();
			if(item.equals("-")==false)
				s.push(item);
			else if (s.isEmpty()==false)
				System.out.print(s.pop() + " ");
		}
		System.out.print("(" + s.size() + " left on stack)");
		sc.close();
		
		
	}
	
}
