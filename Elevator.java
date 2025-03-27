
/*************************************************************************
 *  Compilation:  javac Elevator.java
 *  Execution:    java Elevator 'number of floors' 'floor requests' 'number of restricted floors' 'optional passcode'
 *
 *  @author Pooja Kedia
 *  @author Vidushi Jindal
 *
 *************************************************************************/
 public class Elevator {
    
    public static void main ( String[] args ) {
	// WRITE YOUR CODE HERE
        int floors = Integer.parseInt(args[0]);
        int requests = Integer.parseInt(args[1]);
        int restrictedFloors = Integer.parseInt(args[2]);
        int passcode = 0;
        

        if (restrictedFloors != 0) {
            passcode = Integer.parseInt(args[3]);
        }

        int elevator1 = 1;
        int elevator2 = 1;

        while (requests > 0) {
            if (Math.abs((requests % 10) - elevator1) <= Math.abs((requests % 10) - elevator2)) {
                elevator1 = requests % 10;
                System.out.println("1 " + requests % 10);
            }
            else {
                elevator2 = requests % 10;
                System.out.println("2 " + requests % 10);
            }
            if (restrictedFloors != 0) {
                if ((requests % 10) > (floors - restrictedFloors)) {
                    if ((passcode % floors) == (requests % 10) || ((passcode % floors == 0) && (requests % 10) == floors)) {
                        System.out.println("Granted");
                    }
                    else {
                        System.out.println("Denied");
                    }
                }
            }
            requests = requests / 10;
        }
    }
}