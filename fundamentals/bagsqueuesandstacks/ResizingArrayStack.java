package fundamentals.bagsqueuesandstacks;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {

	private Item[] a = (Item[]) new Object[1];
	private int N = 0;
	
	public boolean inEmpty() {
		return N==0;
	}
	
	public int size() {
		return N;
	}
	
	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0 ;i<N;i++) {
			temp[i] = a[i];
		}
		a = temp;
	}
	
	public void push(Item item) {
		if (N == a.length)
			resize(2*a.length);
		a[N] = item;
		N++;
	}
	
	public Item pop() {
		Item item = a[--N];
		a[N] = null;
		if (N>0 && N == a.length/4)
			resize(a.length/2);
		return item;
	}
	
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Item>{
		
		private int i = N;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return i > 0;
		}

		@Override
		public Item next() {
			i--;
			return a[i];
		}
		
		public void remove() {}
	}

}
