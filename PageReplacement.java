/*----------------------------------------------------------------------------
 * Authors: Tytrez Dixon and Cannon Miles
 * 
 * Date Created: 11/27/2025
 * 
 * Date Updated: 11/30/2025
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
		
		Scanner input = new Scanner(System.in);
		
		while(true) {
			
			int frameCount2 = input.nextInt();
				
			// Reject inputs that do not fall into the specified range.
			if (frameCount2 < 3 || frameCount2 > 5) {
				System.out.println("Invalid Input. Please select a valid " +
						"frame count.");
			}
			
			else {
				frameCount = frameCount2;
				break;
			}
		}	
		
		// Create process of specified frame count.
		int[] process = new int[frameCount];

		// Prompt user to enter page requests based on process size
		System.out.println("Enter page requests. Please enter exactly" +
				" twice as many pages as the number of frames you selected.");
		
		// Create array to keep track of page requests.
		int[] pageRequests = new int[frameCount * 2];
		int processIndex = 0;
		
		while (input.hasNextInt()) {
			pageRequests[processIndex] = input.nextInt();
			processIndex++;
			
			if (processIndex == pageRequests.length) {
				break;
			}
		}
		
		input.close();
		
		
	}

}
