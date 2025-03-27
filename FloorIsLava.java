
/*************************************************************************
 *  Compilation:  javac FloorIsLava.java
 *  Execution:    java FloorIsLava n
 *
 *  @author Shane Haughton, Maaz Mansuri
 *
 **************************************************************************/

public class FloorIsLava {
    public static void main (String[] args ) {
	// WRITE YOUR CODE HERE

    int count = 0;
        int num = Integer.parseInt(args[0]);
        
        for (count = 1; count <= num; count++) {
            if (count % 2 == 0) {
                System.out.print(count + " ");
            }
        }
        
        count = num;
        
        while (count > 0) {
            if (count % 2 == 1) {
                System.out.print(count + " ");
            }
            count--;
        }
    }
}