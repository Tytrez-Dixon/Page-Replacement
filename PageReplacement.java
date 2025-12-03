/*----------------------------------------------------------------------------
 * Authors: Tytrez Dixon and Cannon Miles
 * 
 * Date Created: 11/27/2025
 * 
 * Date Updated: 12/03/2025
 * 
 * This Java program simulates the page replacement OS operation using 3, 4,
 * or 5 frames. The FCFS (Cannon Miles) and LRU (Tytrez Dixon) page 
 * replacement polices are used.
 * 
 ----------------------------------------------------------------------------*/
import java.util.Scanner;
public class PageReplacement {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int frameCount = 0;

        //Allow user to select number of frames for resident set.
        System.out.println("How many frames in the set? (enter 3, 4, or 5)");
        
		//Input validation
        while(true) {
            int frameCountChoice = input.nextInt();
                
            if (frameCountChoice < 3 || frameCountChoice > 5) {
                System.out.println("Invalid Input. Please select a valid frame count:");
            }
            else {
                frameCount = frameCountChoice;
                break;
            }
        }
        
        input.nextLine();

        // Make array for resident frames
        Integer[] resident = new Integer[frameCount];

        // Holds index of item that will be replaced next.
        int nextToReplace = 0;

		//Counter for what item in the user input we're on now
        int step = 0;

        //Read in "page requests", split by spaces
        System.out.println("Enter integers (0 through 9) separated by spaces: ");
        String inputString = input.nextLine();
        String[] tokens = inputString.split("\\s+");
        
        //Make the final rows for printout (since we know length)
        String[][] finalRows = new String[frameCount + 1][tokens.length];

        //Run for each number in the input string
        for (int i=0; i<tokens.length; i++) {
            boolean faultHappened = false;
            String tok = tokens[i];
            int num = Integer.parseInt(tok);
            
            //Check if current token is a resident already
            boolean found = false;
            for (int j=0; j<frameCount; j++) {
                if (resident[j] != null && resident[j] == num) {
                    found = true;
                    break;
                }
            }

            //If no match, we need to write something into the set
            if (!found) {

                int emptyIndex = -1;
                
                for (int j=0; j<frameCount; j++) {
                    if (resident[j] == null) {
                        emptyIndex = j;
                        break;
                    }
                }

                //Empty space, no overwriting
                if (emptyIndex != -1) {
                    resident[emptyIndex] = num;
                }
                
				//No empty space, need to overwrite
				else {
                    faultHappened = true;
                    resident[nextToReplace] = num;
                    
					nextToReplace++;
					
					//handle case where it would go past total (3,4,5)
					if (nextToReplace == frameCount) {
						nextToReplace = 0;
					}
                }
            }
            
            // Add updates to the final rows
            for (int j=0; j<frameCount; j++) {
                if (resident[j] == null) {
                    finalRows[j][step] = "-";
                }
                else {
                    finalRows[j][step] = resident[j].toString();
                }
            }
            
            if (faultHappened) {finalRows[frameCount][step] = "F";}
            
			else {finalRows[frameCount][step] = " ";}
            
            step++;
        }

        input.close();

        // FINAL PRINTOUT
        System.out.println("\n---With FIFO Algorithm: ---");
        for (int r=0; r<frameCount + 1; r++) {
            for (int c=0; c<tokens.length; c++) {
                System.out.print(finalRows[r][c] + " ");
            }
            System.out.println();
        }
    }
}