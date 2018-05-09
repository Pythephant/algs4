package sorting.assignment;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private LineSegment[] lines; // all the LineSegments contain by the points
	private int N; // the Number of the lineSegments;
	
	/**
	 * construct the collinearPoints lines
	 * @param points
	 */
	public BruteCollinearPoints(Point[] points) {
		if (points == null)
			throw new java.lang.IllegalArgumentException();
		Arrays.sort(points);
		for (int i = 0; i < points.length; i++) {
			if(points[i]==null)
				throw new IllegalArgumentException();
			if(i>0 && points[i].compareTo(points[i-1])==0)
				throw new IllegalArgumentException();
		}
		N = 0;
		lines = new LineSegment[points.length];
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				for (int k = j + 1; k < points.length; k++) {
					for (int l = k + 1; l < points.length; l++) {
						double slopeIJ = points[i].slopeTo(points[j]);
						double slopeIK = points[i].slopeTo(points[k]);
						double slopeIL = points[i].slopeTo(points[l]);
						if (slopeIJ == slopeIK && slopeIJ == slopeIL) {
							lines[N] = new LineSegment(points[i], points[l]);
							N++;
						}
					}
				}
			}
		}
	}

	/**
	 * the Number of Segments of the lines consisted by the points;
	 * @return, the Number
	 */
	public int numberOfSegments() {
		return N;
	}

	/**
	 * return the copy of the linesegments
	 * @return
	 */
	public LineSegment[] segments() {
		LineSegment[] segs = new LineSegment[N];
		for (int i = 0; i < N; i++)
			segs[i] = lines[i];
		return segs;
	}

	/**
	 * Unit test;
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
