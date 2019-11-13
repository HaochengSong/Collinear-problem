import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
public class FastCollinearPoints {
	private Point[] array;
	private int size;
	private int number;
	private ArrayList<LineSegment> line;
	public FastCollinearPoints(Point[] points) {
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
		line = new ArrayList<LineSegment>();
		Point[] aux = new Point[size];
		for (int i = 0; i < size; i++) {
			for (int a = 0; a < points.length; a++)
				aux[a] = array[a];
			Point origin = array[i];
			Arrays.sort(aux, origin.slopeOrder());
			int count = 0;
			for (int j = 2; j < size; j++) {			
				if (origin.slopeTo(aux[j - 1]) == origin.slopeTo(aux[j])){
					count++;
					if (count >= 2 && j == size - 1 && origin.compareTo(aux[j - count]) < 0) {
						line.add(new LineSegment(origin, aux[j]));
						number++;
						count = 0;
					}
				}
				else if (count >= 2 && origin.compareTo(aux[j - count - 1]) < 0) {
					line.add(new LineSegment(origin, aux[j - 1]));
					number++;
					count = 0;
				}
				else {
					count = 0;
				}
			}
		}
	}
    public int numberOfSegments() {
    	return number;
    }
    public LineSegment[] segments() {
        LineSegment[] seg = new LineSegment[number];
        for (int i = 0; i < number; i++) {
        	seg[i] = line.get(i);
        }
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            segment.draw();
        }
        StdDraw.show();
        StdOut.println("Final print:");
        for (LineSegment segment : collinear.segments())
        	StdOut.println(segment);
    }*/
}