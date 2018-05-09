package sorting.assignment;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

	private LineSegment[] lines; // the lineSegments consisted by more than 4 points
	private int N; // the number of the lineSegments

	/**
	 * construct the line segments, if there are more than 4 points
	 * 
	 * @param points
	 */
	public FastCollinearPoints(Point[] points) {
		Arrays.sort(points);
		lines = new LineSegment[points.length];
		N = 0;
		Point[] sortedPoints = new Point[points.length];
		for (int i = 0; i < points.length; i++)
			sortedPoints[i] = points[i];
		for (int i = 0; i < points.length; i++) {
			Comparator<Point> cmp = points[i].slopeOrder();
			Arrays.sort(sortedPoints, cmp);
			Point pivot = sortedPoints[0];
			Point minPoint = pivot;
			Point maxPoint = pivot;
			double orgSlope = pivot.slopeTo(pivot);
			int count = 0;
			for (int j = 1; j < sortedPoints.length; j++) {
				Point thisPoint = sortedPoints[j];
				double thisSlope = pivot.slopeTo(thisPoint);
				if (thisSlope != orgSlope) {
					// if previous slope has more than 4 points
					if (count >= 3 && minPoint == pivot) {
						lines[N] = new LineSegment(minPoint, maxPoint);
						N++;
					}
					count = 1;
					orgSlope = thisSlope;
					minPoint = pivot;
					maxPoint = pivot;
				} else {
					count++;
				}
				if (minPoint.compareTo(thisPoint) > 0)
					minPoint = sortedPoints[j];
				if (maxPoint.compareTo(thisPoint) < 0)
					maxPoint = sortedPoints[j];
			}
			// the last line
			if (count >= 3 && minPoint == pivot) {
				lines[N] = new LineSegment(minPoint, maxPoint);
				N++;
			}
		}
	}

	public int numberOfSegments() {
		return N;
	}

	public LineSegment[] segments() {
		LineSegment[] segs = new LineSegment[N];
		for (int i = 0; i < N; i++) {
			segs[i] = lines[i];
		}
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
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}
