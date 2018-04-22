package fundamentals.assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private final int n;
	private final int trials;
	private final double[] p; // the posibilities of the percolation
	private final double mean;
	private final double stddev;

	public PercolationStats(int n, int trials) {
		if (trials < 1)
			throw new IllegalArgumentException("Illegal trials.");
		this.n = n;
		this.trials = trials;
		p = new double[trials];
		for (int i = 0; i < trials; i++) {
			Percolation perc = new Percolation(n);
			int[] index = StdRandom.permutation(n * n);
			for (int ix : index) {
				perc.open(toRow(ix), toCol(ix));
				if (perc.percolates()) {
					p[i] = (double) perc.numberOfOpenSites() / (n * n);
					break;
				}
			}
		}
		mean = StdStats.mean(p);
		stddev = StdStats.stddev(p);
	}

	private int toRow(int index) {
		return (index / n) + 1;
	}

	private int toCol(int index) {
		return (index % n) + 1;
	}

	public double mean() {
		return mean;
	}

	public double stddev() {
		if (trials == 1)
			return Double.NaN;
		return stddev;
	}

	public double confidenceLo() {
		return mean - 1.96 * stddev / Math.sqrt(trials);
	}

	public double confidenceHi() {
		return mean + 1.96 * stddev / Math.sqrt(trials);
	}

	public static void main(String[] args) {
		if (args.length != 2)
			throw new IllegalArgumentException("java PercolationStats [N] [Trials]");
		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(n, t);
		StdOut.println("mean\t=" + ps.mean());
		StdOut.println("stddve\t=" + ps.stddev());
		StdOut.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
	}
}
