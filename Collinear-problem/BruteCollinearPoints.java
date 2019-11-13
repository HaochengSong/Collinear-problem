import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
public class BruteCollinearPoints {
	private Point[] array;
	private int size;
	private int number;
	private LineSegment[] line;
	public BruteCollinearPoints(Point[] points) {
		if (points == null)
			throw new IllegalArgumentException("Cannot input null!");
		for (int i = 0; i < points.length; i++)
			if (points[i] == null)
				throw new IllegalArgumentException("There is null value inside array!");	
		array = new Point[points.length];
		for (int i = 0; i < points.length; i++) 
			array[i] = points[i];
		Arrays.sort(array);
		for (int i = 0; i < array.length - 1; i++)
			if (array[i].compareTo(array[i + 1]) == 0)
				throw new IllegalArgumentException("There are repeat value inside array!");
		size = points.length;
		number = 0;
		Point[] arrayStart = new Point[size];
		Point[] arrayEnd = new Point[size];
		// Can check slope every loops to save time
		for (int i = 0; i < size - 3; i++)
			for (int j = i + 1; j < size - 2; j++) {
				double slopeIJ = array[i].slopeTo(array[j]);
				for ( int k = j + 1; k < size - 1; k++) {
					double slopeJK = array[j].slopeTo(array[k]);
					if (slopeIJ != slopeJK)
						continue;
					for (int l = k + 1; l < size; l++) {
						double slopeKL = array[k].slopeTo(array[l]);
						if (slopeJK == slopeKL) {
							arrayStart[number] = array[i];
							arrayEnd[number] = array[l];
							number++;
						}
					}
				}
			}
		line = new LineSegment[number];
		for (int i = 0; i < number; i++)
			line[i] = new LineSegment(arrayStart[i], arrayEnd[i]);
	}
	public int numberOfSegments() {
		return number;
	}
	public LineSegment[] segments() {
        LineSegment[] seg = new LineSegment[number];
        for (int i = 0; i < number; i++)
        	seg[i] = line[i];
        return seg;
	}
    /*public static void main(String[] args) {

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
            segment.draw();
        }
        StdDraw.show();
        for (LineSegment segment : collinear.segments())
        	StdOut.println(segment);
    }*/
}
