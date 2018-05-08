package sorting.assignment;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private LineSegment[] lines;
	private int N;

	public BruteCollinearPoints(Point[] points) {
		N = 0;
		Arrays.sort(points);
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

	public int numberOfSegments() {
		return N;
	}

	public LineSegment[] segments() {
		LineSegment[] segs = new LineSegment[N];
		for(int i=0;i<N;i++)
			segs[i] = lines[i];
		return segs;
	}
	
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
