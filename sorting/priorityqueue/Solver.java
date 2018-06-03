package sorting.priorityqueue;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
	private Stack<Board> solution; // the solution
	private int moves; // the least moves of the solution
	private boolean isSolvable; // true if the inital is able to go the goal,
								// otherwise false

	/**
	 * Constructor of the Solver
	 * 
	 * @param initial
	 */
	public Solver(Board initial) {
		if (initial == null)
			throw new IllegalArgumentException();
		SearchNode node = new SearchNode(initial, null, 0);
		// twin is used to judge if the initial is solvable
		SearchNode twin = new SearchNode(initial.twin(), null, 0);
		MinPQ<SearchNode> nodePQ = new MinPQ<SearchNode>();
		MinPQ<SearchNode> twinPQ = new MinPQ<SearchNode>();
		nodePQ.insert(node);
		twinPQ.insert(twin);
		while (!node.isGoal() && !twin.isGoal()) {
			node = nodePQ.delMin();
			twin = twinPQ.delMin();
			SearchNode nodeParent = node.getParent();
			SearchNode twinParent = twin.getParent();
			for (Board neighbor : node.getNeighbors()) {
				// if the neighbor is equals to the parent, then not push it
				// into the Heap
				if (nodeParent != null && nodeParent.getSelf().equals(neighbor))
					continue;
				nodePQ.insert(new SearchNode(neighbor, node, node.getMoves() + 1));
			}
			for (Board neighbor : twin.getNeighbors()) {
				// if the neighbor is equals to the parent, then not push it
				// into the Heap
				if (twinParent != null && twinParent.getSelf().equals(neighbor))
					continue;
				twinPQ.insert(new SearchNode(neighbor, twin, twin.getMoves() + 1));
			}
		}

		// if the initial is solvable, construct the solution and take note of
		// the moves
		if (node.isGoal()) {
			isSolvable = true;
			moves = node.moves;
			solution = new Stack<>();
			solution.push(node.getSelf());
			while (node.getParent() != null) {
				node = node.getParent();
				solution.push(node.getSelf());
			}
		} else {
			// if initial is not solvable, return the required value of the
			// moves, solution and isSolvable
			isSolvable = false;
			moves = -1;
			solution = null;
		}

	}

	/**
	 * 
	 * @return, true if initial is able to move to the goal. otherwise false
	 */
	public boolean isSolvable() {
		return this.isSolvable;
	}

	/**
	 * 
	 * @return the least moves to go to the goal, -1 if initial is not solvable
	 */
	public int moves() {
		return moves;
	}

	/**
	 * return the Iterable instance of the Boards from the initial to the goal
	 * 
	 * @return
	 */
	public Iterable<Board> solution() {
		return solution;
	}

	/**
	 * inner class to help the the solution, SearchNode take the Board tile ,
	 * the parent SearchNode and the moves of the Node so far.
	 * 
	 * @author wujiakun
	 *
	 */
	private class SearchNode implements Comparable<SearchNode> {
		private Board self;
		private SearchNode parent;
		private int moves;
		private int priority;
		private boolean isGoal;

		public SearchNode(Board self, SearchNode parent, int moves) {
			this.parent = parent;
			this.self = self;
			this.priority = self.manhattan();
			this.moves = moves;
			this.isGoal = self.isGoal();
		}

		public boolean isGoal() {
			return this.isGoal;
		}

		public Iterable<Board> getNeighbors() {
			return self.neighbors();
		}

		public SearchNode getParent() {
			return parent;
		}

		public Board getSelf() {
			return self;
		}

		public int getMoves() {
			return moves;
		}

		@Override
		public int compareTo(SearchNode other) {
			if (this.priority + this.moves > other.priority + other.moves)
				return 1;
			else if (this.priority + this.moves < other.priority + other.moves)
				return -1;
			else
				return 0;
		}

	}

	/**
	 * the test main function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// create initial board from file
		In in = new In(args[0]);
		int n = in.readInt();
		int[][] blocks = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

}
