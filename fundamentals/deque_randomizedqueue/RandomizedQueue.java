package fundamentals.deque_randomizedqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int N; // the total number in the Queue;
	private Item[] arr;

	/**
	 * constructor, initialize N to 0, and array size to 8 for the very
	 * beginning
	 */
	public RandomizedQueue() {
		arr = (Item[]) new Object[8];
		N = 0;
	}

	/**
	 * 
	 * @return,true if queue is empty, otherwise false
	 */
	public boolean isEmpty() {
		return N == 0;
	}

	/**
	 * 
	 * @return, the size of the array
	 */
	public int size() {
		return N;
	}

	/**
	 * 
	 * @param item,
	 *            the item to enQueue; thows IllegalArgumentException if input
	 *            is null if the array is full than resize to double size of the
	 *            current
	 */
	public void enqueue(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		if (N == arr.length)
			resize(2 * N);
		arr[N] = item;
		N++;
	}

	/**
	 * 
	 * @param max:
	 *            private method, resize the array to [max] size
	 */
	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) {
			temp[i] = arr[i];
		}
		arr = temp;
	}

	/**
	 * throw NoSuchElementException if the queue is empty pick up a random
	 * number returnIdx for [0,N) uniformly switch the last item and returnIdx
	 * item, at last , return the last item minus the N by 1, if N less than a
	 * quarter of array length then resize the length to half
	 * 
	 * @return a random item for the queue and remove it
	 */
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();

		int returnIdx = StdRandom.uniform(N);
		Item item = arr[returnIdx];
		arr[returnIdx] = arr[N - 1];
		arr[N - 1] = null;
		N--;
		if (N > 0 && N < arr.length / 4)
			resize(arr.length / 2);
		return item;
	}

	/**
	 * 
	 * @return a random item from the queue , but not remove it
	 */
	public Item sample() {
		if (isEmpty())
			throw new NoSuchElementException();

		int index = StdRandom.uniform(N);
		return arr[index];
	}

	@Override
	/**
	 * return the iterator of the queue
	 */
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	/**
	 * private class of the iterator
	 * 
	 * @author wujiakun
	 *
	 */
	private class RandomizedQueueIterator implements Iterator<Item> {

		private Item[] items;
		private int current;

		/**
		 * iterator constructor
		 */
		public RandomizedQueueIterator() {
			current = 0;
			items = (Item[]) new Object[N];
			for (int i = 0; i < N; i++) {
				items[i] = arr[i];
			}
			StdRandom.shuffle(items);
		}

		@Override
		/**
		 * return true if array has the next item
		 */
		public boolean hasNext() {
			return current < items.length;
		}

		@Override
		/**
		 * return the next item
		 */
		public Item next() {
			if (current == items.length)
				throw new NoSuchElementException();
			Item item = items[current];
			current++;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * 
	 * test method
	 * 
	 * @param args
	 *            : command line arguments
	 * 
	 */
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<>();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			rq.enqueue(s);
		}

		Iterator<String> it = rq.iterator();
		for (int i = 0; i < n; i++) {
			StdOut.println(it.next());
		}

	}

}
