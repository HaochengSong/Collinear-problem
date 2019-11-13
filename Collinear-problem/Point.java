import java.util.Comparator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
public class Point implements Comparable<Point> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw() {
        StdDraw.point(x, y);
    }
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    public int compareTo(Point that) {
    	if (this.y < that.y)
    		return -1;
    	else if (this.y == that.y)
    		if (this.x < that.x)
    			return -1;
    		else if (this.x == that.x)
    			return 0;
    		else 
    			return 1;
    	else 
    		return 1;
    }
    public double slopeTo(Point that) {
    	if (this.y == that.y) {
    		if (this.x == that.x)
    			return Double.NEGATIVE_INFINITY;
    		return 0.0;
    	}
    	else if (this.x == that.x)
    		return Double.POSITIVE_INFINITY;
    	else 
    		return (double)(that.y - this.y) / (that.x - this.x);
    }
    public Comparator<Point> slopeOrder() {
    	return new Comparator<Point>() {
    		public int compare(Point a, Point b) {
    			Double slopeA = slopeTo(a);
    			Double slopeB = slopeTo(b);
    			return slopeA.compareTo(slopeB);
    		}
    	};
    }
}
