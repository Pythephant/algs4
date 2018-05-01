package fundamentals.bagsqueuesandstacks;

import edu.princeton.cs.algs4.StdOut;

public class QueueWithTwoStacks<Item> {
	private Stack<Item> inStack;
	private Stack<Item> outStack;

	public QueueWithTwoStacks() {
		inStack = new Stack<>();
		outStack = new Stack<>();
	}

	public void enQueue(Item item) {
		inStack.push(item);
	}

	public Item deQueue() {
		if (outStack.isEmpty()) {
			while (inStack.isEmpty() == false)
				outStack.push(inStack.pop());
		}
		return outStack.pop();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return inStack.toString() + "\n" + outStack.toString();
	}

	/**
	 * main for the test
	 */
	public static void main(String[] args) {
		QueueWithTwoStacks<String> q = new QueueWithTwoStacks<>();
		q.enQueue("Jaosn");
		q.enQueue("canddi");
		q.enQueue("catti");
		q.enQueue("kenny");
		StdOut.println(q);
		StdOut.println(q.deQueue());
		StdOut.println(q);
		q.enQueue("lalala");
		q.enQueue("bababa");
		StdOut.println(q.deQueue());
		StdOut.println(q);
	}
}
