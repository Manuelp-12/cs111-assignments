/*
 * Write your program inside the main method to find the order
 * which the bus the student needs to take will arrive
 * according to the assignemnt description. 
 *
 * To compile:
 *        javac BusStop.java
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */
public class BusStop {

    public static void main(String[] args) {

        char[] arr = new char[args.length - 1];

        for (int i = 0; i < args.length - 1; i++) {
            arr[i] = args[i].charAt(0);
        }

        char bus = args[args.length - 1].charAt(0);
        boolean inArray = false;

        for (int i = 0; i < args.length - 1; i++) {
            if (arr[i] == bus) {
                System.out.print(i + 1);
                inArray = true;
                break;
            }
        }

        if (inArray == false) {
            System.out.print(-1);
        }

        // WRITE YOUR CODE HERE

    }
}
