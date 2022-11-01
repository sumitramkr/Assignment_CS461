public class Main {
    private static final double THRESHOLD = 0.000001;

    public static boolean Intersection(double x0, double y0, double r0, double x1, double y1, double r1, double x2, double y2, double r2) {
        double a, deltaX, deltaY, d, h, rx, ry;
        double p2X, p2Y;

        // Vertical and horizontal distances between the circle centers
        deltaX = x1 - x0;
        deltaY = y1 - y0;

        // Straight-line distance between the centers
        d = Math.sqrt((deltaY * deltaY) + (deltaX * deltaX));

        // Check for solvability
        if (d > (r0 + r1)) {
            return false; // No Solution as ircles do not intersect
        }
        if (d < Math.abs(r0 - r1)) {
            return false; // No solution as one circle is contained in the other
        }
        
        // 'p2' is the point where the line through the circle intersection points crosses the line between the circle centers.

        // The distance from point 0 to point 2
        a = ((r0 * r0) - (r1 * r1) + (d * d)) / (2.0 * d);

        // The coordinates of point 2
        p2X = x0 + (deltaX * a / d);
        p2Y = y0 + (deltaY * a / d);

        // The distance from point 2 to either of the intersection points
        h = Math.sqrt((r0 * r0) - (a * a));

        // The offsets of the intersection points from point 2
        rx = -deltaY * (h / d);
        ry = deltaX * (h / d);

        // The absolute intersection points
        double interP1X = p2X + rx;
        double interP2X = p2X - rx;
        double interP1Y = p2Y + ry;
        double interP2Y = p2Y - ry;

        // If circle 3 intersects at either of the above intersection points
        deltaX = interP1X - x2;
        deltaY = interP1Y - y2;
        double d1 = Math.sqrt((deltaY * deltaY) + (deltaX * deltaX));

        deltaX = interP2X - x2;
        deltaY = interP2Y - y2;
        double d2 = Math.sqrt((deltaY * deltaY) + (deltaX * deltaX));

        if (Math.abs(d1 - r2) < THRESHOLD) {
            System.out.println("Intersection point: " + "(" + interP1X + "," + interP1Y + ")");
        } else if (Math.abs(d2 - r2) < THRESHOLD) {
            System.out.println("Intersection point: " + "(" + interP2X + "," + interP2Y + ")");
        } else {
            System.out.println("No intersection point");
        }
        return true;
    }

    public static void main(String[] args) {
        Intersection(3.0, 0.0, 3.0,
                -1.0, -1.0, Math.sqrt(2),
                1.0, 1.0, Math.sqrt(2));
    }
}