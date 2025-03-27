/*************************************************************************
 * Compilation: javac QuadraticKoch.java
 * Execution: java QuadraticKoch n
 *
 * @author Jeremy Hui
 *
 *************************************************************************/
public class QuadraticKoch {

    /**
     * Gets the set of coordinates to draw one segment of the Quadratic Koch Curve.
     * Returns the coordinates in a 2D array of doubles in the following format:
     * {array of x-coordinates,
     * array of y-coordinates}
     * 
     * @param x0 the x-coordinate of one endpoint
     * @param y0 the y-coordinate of one endpoint
     * @param x5 the x-coordinate of the other endpoint
     * @param y5 the y-coordinate of the other endpoint
     * @return the set of coordinates to draw one segment of the Quadratic Koch
     *         Curve
     */
    public static double[][] getCoords(double x0, double y0, double x5, double y5) {
        // WRITE YOUR CODE HERE

        if (x0 == x5) {
            double distance = (y5 - y0);
    
            double x1 = x0;
            double y1 = y0 + (distance / 3);

            double x2 = x1 - (distance / 3);
            double y2 = y1;

            double x3 = x2;
            double y3 = y2 + (distance / 3);

            //double x4 = x3 + (distance / 3);
            double x4 = x1;
            double y4 = y3;

            double[][] coords = {{x0, x1, x2, x3, x4, x5}, {y0, y1, y2, y3, y4, y5}};
            return coords;
        }
        else if (y0 == y5) {
            double distance = (x5 - x0);
    
            double x1 = x0 + (distance / 3);
            double y1 = y0;

            double x2 = x1;
            double y2 = y1 + (distance / 3);

            double x3 = x2 + (distance / 3);
            double y3 = y2;

            double x4 = x3;
            //double y4 = y3 - (distance / 3);
            double y4 = y0;

            double[][] coords = {{x0, x1, x2, x3, x4, x5}, {y0, y1, y2, y3, y4, y5}};
            return coords;
        }
        else {
            double xdistance = x5 - x0;
            double ydistance = y5 - y0;

            double x1 = x0 + (xdistance / 3);
            double y1 = y0 + (ydistance / 3);
            
            double x2 = x1 - (xdistance / 3);
            double y2 = y1 + (ydistance / 3);

            double x3 = x2 + (xdistance / 3);
            double y3 = y2 + (ydistance / 3);

            double x4 = x3 + (xdistance / 3);
            double y4 = y3 - (ydistance / 3);
        
            double[][] coords = {{x0, x1, x2, x3, x4, x5}, {y0, y1, y2, y3, y4, y5}};
            return coords;
        }
    }

    /**
     * Gets the set of coordinates from getCoords() to draw the snowflake,
     * and calls Koch on two adjacent array indices with n being one less.
     * The method draws a line between the two endpoints if n == 0.
     * 
     * @param x0 the x-coordinate of one endpoint
     * @param y0 the y-coordinate of one endpoint
     * @param x5 the x-coordinate of the other endpoint
     * @param y5 the y-coordinate of the other endpoint
     * @param n  The current order
     */
    public static void koch(double x0, double y0, double x5, double y5, int n) {
        // WRITE YOUR CODE HERE
        if (n == 0) {
            StdDraw.line(x0, y0, x5, y5);
        }
        else {
            double[][] coords = getCoords(x0, y0, x5, y5);
            
            for (int i = 0; i < 5; i++) {
                koch(coords[0][i], coords[1][i], coords[0][i + 1], coords[1][i + 1], n - 1);
            }
        }
    }

    /**
     * Takes an integer command-line argument n,
     * and draws a Quadratic Koch Curve of order n in a 1 x 1 canvas
     * with an initial square side length of 0.5.
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // WRITE YOUR CODE HERE
        int order = Integer.parseInt(args[0]);

        koch(0.25, 0.25, 0.25, 0.75, order);
        koch(0.25, 0.75, 0.75, 0.75, order);
        koch(0.75, 0.75, 0.75, 0.25, order);
        koch(0.75, 0.25, 0.25, 0.25, order);
    }
}