/*
 * Write the Palindrome program inside the main method
 * according to the assignment description.
 * 
 * To compile:
 *        javac Palindrome.java
 * To execute:
 *        java Palindrome 5 4 6 6 4 5
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */

public class Palindrome {
    public static void main(String[] args) {
       
        // WRITE YOUR CODE HERE
        System.out.print("Enter integer 1: ");
        int int1 = Integer.parseInt(args[0]);

        System.out.print("Enter integer 2: ");
        int int2 = Integer.parseInt(args[1]);

        System.out.print("Enter integer 3: ");
        int int3 = Integer.parseInt(args[2]);

        System.out.print("Enter integer 4: ");
        int int4 = Integer.parseInt(args[3]);

        System.out.print("Enter integer 5: ");
        int int5 = Integer.parseInt(args[4]);

        System.out.print("Enter integer 6: ");
        int int6 = Integer.parseInt(args[5]);

        System.out.println((int1 == int6) && (int2 == int5) && (int3 == int4));
    }
}