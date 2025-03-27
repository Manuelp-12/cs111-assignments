




/**
 * Compilation: javac EgyptianPyramid.java
 * Execution:   java EgyptianPyramid 'size of grid' 'number of initial blocks'
 * 
 * @author Ayla Muminovic
 * @author Kushi Sharma
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 */
public class EgyptianPyramid {
    
    public static void main(String[] args) {

	// WRITE YOUR CODE HERE

        char[][] arr = new char[Integer.parseInt(args[0])][Integer.parseInt(args[0])];
        int bricks = Integer.parseInt(args[1]);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = '=';
            }
        }
        /* 
        for (int i = arr.length - 1; i >= 0; i++) {
            for (int j = arr.length - 1 - i; j < arr[0].length - 2*(arr.length - 1 - i); j++) {
                if (bricks <= 0) {
                    break;
                }
                else {
                    arr[i][j] = 'X';
                    bricks--;
                }
            }
        }
        */

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = arr.length - 1 - i; j <= i; j++) {
                if (bricks <= 0 || j < 0) {
                    break;
                }
                else {
                    arr[i][j] = 'X';
                    bricks--;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.print(bricks + " Bricks Remaining");
    }
}