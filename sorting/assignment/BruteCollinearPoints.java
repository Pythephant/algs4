package sorting.assignment;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private Node first; // all the LineSegments contain by the points
	private int N; // the Number of the lineSegments;

	private class Node {
		LineSegment line;
		Node next;
	}

	/**
	 * construct the collinearPoints lines
	 * 
	 * @param points
	 */
	public BruteCollinearPoints(Point[] points) {
		if (points == null)
			throw new java.lang.IllegalArgumentException();
		Point[] clonePoints = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null)
				throw new IllegalArgumentException();
			clonePoints[i] = points[i];
		}
		Arrays.sort(clonePoints);
		for (int i = 0; i < clonePoints.length; i++) {
			if (i > 0 && clonePoints[i].compareTo(clonePoints[i - 1]) == 0)
				throw new IllegalArgumentException();
		}
		N = 0;
		first = null;
		for (int i = 0; i < clonePoints.length; i++) {
			for (int j = i + 1; j < clonePoints.length; j++) {
				for (int k = j + 1; k < clonePoints.length; k++) {
					for (int l = k + 1; l < clonePoints.length; l++) {
						double slopeIJ = clonePoints[i].slopeTo(clonePoints[j]);
						double slopeIK = clonePoints[i].slopeTo(clonePoints[k]);
						double slopeIL = clonePoints[i].slopeTo(clonePoints[l]);
						if (slopeIJ == slopeIK && slopeIJ == slopeIL) {
							Node preFirst = first;
							first = new Node();
							first.line = new LineSegment(clonePoints[i], clonePoints[l]);
							first.next = preFirst;
							N++;
						}
					}
				}
			}
		}
	}

	/**
	 * the Number of Segments of the lines consisted by the points; @return, the
	 * Number
	 */
	public int numberOfSegments() {
		return N;
	}

	/**
	 * return the copy of the linesegments
	 * 
	 * @return
	 */
	public LineSegment[] segments() {
		LineSegment[] segs = new LineSegment[N];
		Node current = first;
		for (int i = 0; i < N; i++) {
			segs[i] = current.line;
			current = current.next;
		}
		return segs;
	}

	/**
	 * Unit test;
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// read the n points from a file
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}
