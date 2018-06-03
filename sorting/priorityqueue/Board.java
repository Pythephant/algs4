package sorting.priorityqueue;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.StdRandom;

public class Board {
	private int[][] board; // the tile of the Board, two dimension int
							// array
	private int n; // the demension of the array, number of the row or column

	/**
	 * Constructor
	 * 
	 * @param board
	 */
	public Board(int[][] board) {
		this.n = board.length;
		this.board = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				this.board[i][j] = board[i][j];
	}

	/**
	 * 
	 * @return the demension of the Board
	 */
	public int dimension() {
		return n;
	}

	/**
	 * 
	 * @return,the hamming integer of the board, thus the error number of the
	 *             item in the wrong position
	 */
	public int hamming() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int supposeNumber = i * n + j + 1;
				if (supposeNumber < n * n && supposeNumber != board[i][j])
					count++;
			}
		}
		return count;
	}

	/**
	 * 
	 * @return:the manhattan number, whitch is the sum distance of rows and
	 *             columns of each item
	 */
	public int manhattan() {
		int distance = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] != 0) {
					int row = row(board[i][j]);
					int col = col(board[i][j]);
					distance += Math.abs(i - row) + Math.abs(j - col);
				}
			}
		}
		return distance;
	}

	/**
	 * the number's corrected row , ect. in 3x3 Board, 4 is supposed in row 1
	 * 
	 * @param num
	 * @return
	 */
	private int row(int num) {
		return (num - 1) / n;
	}

	/**
	 * private function
	 * 
	 * @param num
	 * 			@return, the number's correcter column, ect. in 3x3 Board, 4
	 *            is supposed in column 0;
	 */
	private int col(int num) {
		return (num - 1) % n;
	}

	/**
	 * 
	 * @return, true if the Board is corrected, otherwise false;
	 */
	public boolean isGoal() {
		return hamming() == 0;
	}

	/**
	 * 
	 * @return, the twin brother of the Board , which is switch any pair of
	 * items(both are not 0)
	 */
	public Board twin() {
		int[][] t = boardCopy();
		if (t[0][0] != 0 && t[0][1] != 0)
			swap(t, 0, 0, 0, 1);
		else
			swap(t, n - 1, n - 2, n - 1, n - 1);
		return new Board(t);
	}

	/**
	 * 
	 * @return, the copy of the board tile
	 */
	private int[][] boardCopy() {
		int[][] t = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				t[i][j] = board[i][j];
		return t;
	}

	/**
	 * switch the two items in the tile depending on the given rows and columns
	 * 
	 * @param t,
	 *            the tile of the board
	 * @param row1,
	 *            the row number of the item1 to switch.
	 * @param col1,
	 *            the column number of the item1 to switch.
	 * @param row2,
	 *            the row number of the item2 to switch.
	 * @param col2,
	 *            the column number of the item2 to switch.
	 */
	private void swap(int[][] t, int row1, int col1, int row2, int col2) {
		int temp = t[row1][col1];
		t[row1][col1] = t[row2][col2];
		t[row2][col2] = temp;
	}

	/**
	 * return true if the tiles of the two Boards are the same, otherwise false;
	 */
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (this.getClass() != other.getClass())
			return false;
		Board that = (Board) other;
		if (this.n != that.n)
			return false;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (this.board[i][j] != that.board[i][j])
					return false;
		return true;
	}

	/**
	 * 
	 * @return, all the neighbors of the Board, which are the 0 item to move up,
	 * down, left and right if the moves are possible
	 */
	public Iterable<Board> neighbors() {
		Stack<Board> neighbors = new Stack<>();
		int row = 0, col = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (board[i][j] == 0) {
					row = i;
					col = j;
					break;
				}
		int[][] t = null;
		if (row - 1 >= 0) {
			t = boardCopy();
			swap(t, row - 1, col, row, col);
			neighbors.push(new Board(t));
		}
		if (row + 1 < n) {
			t = boardCopy();
			swap(t, row + 1, col, row, col);
			neighbors.push(new Board(t));
		}
		if (col - 1 >= 0) {
			t = boardCopy();
			swap(t, row, col - 1, row, col);
			neighbors.push(new Board(t));
		}
		if (col + 1 < n) {
			t = boardCopy();
			swap(t, row, col + 1, row, col);
			neighbors.push(new Board(t));
		}
		return neighbors;
	}

	/**
	 * return the String of the tile of the Board
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(n + "\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				s.append(String.format("%2d ", board[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	/**
	 * test main function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		In in = new In(args[0]);
		int n = in.readInt();
		int[][] blocks = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);
		StdOut.println(initial);
		StdOut.println(initial.dimension());
		StdOut.println("Hamming:" + initial.hamming());
		StdOut.println("Manhattan:" + initial.manhattan());
		StdOut.println("IsGoal:" + initial.isGoal());
		StdOut.println("Twin:");
		StdOut.println(initial.twin());
		StdOut.println("Neighbors:");
		Iterable<Board> neighbors = initial.neighbors();
		for (Board neighbor : neighbors) {
			StdOut.println(neighbor);
		}
	}
}
