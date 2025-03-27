/**
 * FruitCosts 
 * 
 * 1. This program reads in a list of fruits and their costs from a file (args[0]).
 * 2. Then finds the two lowest cost fruits and prints their names and costs
 * along with the total cost.
 * 
 * @author Srimathi Vadivel
 * @author Sarah Benedicto
 */
public class FruitCosts {
    /**
     * Main function to execute the program
     * 
     * @param args command-line arguments, where args[0] is the file name to read from
     */
    public static void main(String[] args) {

	// Do not remove this line, it opens the file for reading.
	StdIn.setFile(args[0]);

	// StdIn.readDouble, StdIn.readString() to read from the file
	
	// WRITE YOUR CODE HERE
    int nbrFruits = StdIn.readInt();
    String[] fruits = new String[nbrFruits];
    double[] costs = new double[nbrFruits];

    for (int i = 0; i < nbrFruits; i++) {
        fruits[i] = StdIn.readString();
        costs[i] = StdIn.readDouble();
    }

    double min = Double.MAX_VALUE;
    double min2 = Double.MAX_VALUE;

    String minfruit = "";
    String minfruit2 = "";

    for (int i = 0; i < costs.length; i++) {
        if (costs[i] < min) {
            min = costs[i];
            minfruit = fruits[i];
        }
    }
    for (int i = 0; i < costs.length; i++) {
        if (costs[i] == min) {
            costs[i] = Double.MAX_VALUE;
            break;
        }
    }
    for (int i = 0; i < costs.length; i++) {
        if (costs[i] < min2) {
            min2 = costs[i];
            minfruit2 = fruits[i];
        }
    }

    StdOut.println(minfruit + " " + min);
    StdOut.println(minfruit2 + " " + min2);
    StdOut.print("Total " + (min + min2));
	
    }
}