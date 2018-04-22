package fundamentals.assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	// uf, the union find object to contain the grid sites and two virtual
	// sites.
	private WeightedQuickUnionUF ufTop;
	private WeightedQuickUnionUF ufBottom;
	private boolean[] siteStatus; // record the site is open or blocked
	private int numberOpened; // the number of sites that have opened
	private int n; // N*N is the shape

	/**
	 * Initialize the Percolation
	 * 
	 * @param n
	 *            n*n is the shape of the grid sites
	 * 
	 */
	public Percolation(int n) {
		if (n <= 0)
			throw new IllegalArgumentException("n should be greater than 0!!!");
		this.n = n;
		int length = n * n + 2;
		// plus two virtual site, index of 0 and n*n+1
		ufTop = new WeightedQuickUnionUF(length);
		ufBottom = new WeightedQuickUnionUF(length);
		siteStatus = new boolean[length];
		// connected the ufTop's and ufBottom's first row to the top virtual
		// site
		for (int i = 1; i < n + 1; i++) {
			ufTop.union(0, i);
			ufBottom.union(0, i);
		}
		// connected the ufBottom's last row to the bottom virtual
		// site
		for (int i = length - 2; i > length - 2 - n; i--) {
			ufBottom.union(i, length - 1);
		}
		numberOpened = 0;
	}

	/**
	 * open the site of the indices(row, col) conencted to its left, right, up,
	 * down sites if they are opened numberOpened plus 1
	 * 
	 * @param row:
	 *            the row number of the site
	 * @param col:
	 *            the column number of the site
	 * 
	 */
	public void open(int row, int col) {
		int index = xyTo1D(row, col);
		if (isOpen(row, col)) {
			return;
		}
		siteStatus[index] = true;
		numberOpened++;
		// connected to the left if left site existed and opened
		if (col - 1 >= 1) {
			if (isOpen(row, col - 1)) {
				ufTop.union(index, xyTo1D(row, col - 1));
				ufBottom.union(index, xyTo1D(row, col - 1));
			}
		}
		// connected to the right if right site existed and opened
		if (col + 1 <= n) {
			if (isOpen(row, col + 1)) {
				ufTop.union(index, xyTo1D(row, col + 1));
				ufBottom.union(index, xyTo1D(row, col + 1));
			}
		}
		// connected to the up if up site existed and opened
		if (row - 1 >= 1) {
			if (isOpen(row - 1, col)) {
				ufTop.union(index, xyTo1D(row - 1, col));
				ufBottom.union(index, xyTo1D(row - 1, col));
			}
		}
		// connected to the down if down site existed and opened
		if (row + 1 <= n) {
			if (isOpen(row + 1, col)) {
				ufTop.union(index, xyTo1D(row + 1, col));
				ufBottom.union(index, xyTo1D(row + 1, col));
			}
		}

	}

	/**
	 * private method: turn the indices to the index of siteStatus array and the
	 * index of union-find array;
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private int xyTo1D(int row, int col) {
		validateIndices(row, col);
		return (row - 1) * n + col;
	}

	/**
	 * judge the indices row and column are valid if not throw Exceptions
	 * 
	 * @param row
	 * @param col
	 */
	private void validateIndices(int row, int col) {
		if (row < 1 || row > n)
			throw new IllegalArgumentException("row index out of bounds");
		if (col < 1 || col > n)
			throw new IllegalArgumentException("column index out of bounds");

	}

	/**
	 * site(row, col) is opened or not
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean isOpen(int row, int col) {
		int index = xyTo1D(row, col);
		return siteStatus[index];
	}

	/**
	 * the specific site(row , col) is connected to the top
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean isFull(int row, int col) {
		int index = xyTo1D(row, col);
		if (!isOpen(row, col))
			return false;
		return ufTop.connected(0, index);
	}

	/**
	 * return the number of the open sites
	 * 
	 * @return
	 */
	public int numberOfOpenSites() {
		return numberOpened;
	}

	/**
	 * judge the percolation is percolated or not
	 * 
	 * @return
	 */
	public boolean percolates() {
		if (n == 1)
			return isOpen(1, 1);
		else
			return ufBottom.connected(0, n * n + 1);
	}

	/**
	 * unit test for percolation
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Percolation perc = new Percolation(3);
		for (int i = 1; i <= 3; i++) {
			perc.open(i, 1);
			StdOut.println("open(" + i + ",1):" + perc.percolates());
		}
	}
}
