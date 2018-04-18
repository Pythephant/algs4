package fundamentals.bagsqueuesandstacks;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;



public class Queue<Item> implements Iterable<Item> {
	
	private Node first;
	private Node last;
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
	
	public void enQueue(Item item){
		Node oldLast = last;
		 last = new Node();
		 last.item = item;
		 if (isEmpty())
			 first = last;
		 else
			 oldLast.next = last;
		 N++;
	}
	
	public Item deQueue(){
		Item item = first.item;
		first = first.next;
		if(isEmpty())
			last = null;
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
	
	public static void main(String[] args) {
		Queue<String> q = new Queue<>();
		while (!StdIn.isEmpty()){
			String item = StdIn.readString();
			if(!item.equals("-"))
				q.enQueue(item);
			else if (q.isEmpty()==false)
				StdOut.print(q.deQueue() + " ");
			
		}
		StdOut.println("("+q.size()+" left on queue!)");
	}
	
}
