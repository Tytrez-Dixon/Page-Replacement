/*----------------------------------------------------------------------------
 * Authors: Tytrez Dixon and Cannon Miles
 * 
 * Date Created: 11/27/2025
 * 
 * Date Updated: __________
 * 
 * This Java program simulates the page replacement OS operation using 3, 4,
 * or 5 frames. The FCFS (Cannon Miles) and LRU (Tytrez Dixon) page 
 * replacement polices are used.
 * 
 ----------------------------------------------------------------------------*/
import java.util.Scanner;
public class PageReplacement {

	public static void main(String[] args) {
		
		// Allow user to select frame count for process assignment.
		System.out.println("Select frame count for process assignment (3, 4," +
				" or 5).");
		
		int frameCount = 0;
		
		while(true) {
			Scanner input = new Scanner(System.in);
			
			int frameCount1 = input.nextInt();
				
			// Reject inputs that do not fall into the specified range.
			if (frameCount1 < 3 || frameCount1 > 5) {
				System.out.println("Invalid Input. Please select a valid " +
						"frame count:");
			}
			
			else {
				frameCount = frameCount1;
				break;
			}
		}	
		
		// Create process of specified frame count.
		int[] process = new int[frameCount];

		
		
//		for (int x: process) {
//			System.out.println(x);
//		}
	}

}
