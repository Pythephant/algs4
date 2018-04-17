package fundamentals.bagsqueuesandstacks;

import java.util.Iterator;



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
}
