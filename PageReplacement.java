/*----------------------------------------------------------------------------
 * Author: Tytrez Dixon
 * 
 * Date Created: 11/27/2025
 * 
 * Date Updated: 12/1/2025
 * 
 * This Java program simulates the OS operation of page replacement using 
 * 3, 4, or 5 frames. The user is asked to select both the frame count for 
 * assignment to a process and twice the amount of page requests. The pages
 * are then placed into the appropriate frames, with the Least Recently Used
 * (LRU) method being used in the event of page faults. The result of the 
 * page requests are then listed in a somewhat tabular format.
 * 
 ----------------------------------------------------------------------------*/

// Import Scanner class needed for user input.
import java.util.Scanner;

public class PageReplacement {

	public static void main(String[] args) {
		
		// Allow user to select frame count for process assignment.
		System.out.println("Select frame count for assignment to a process "
				+ "(3, 4, or 5).");
		
		int frameCount = 0;
		
		Scanner input = new Scanner(System.in);
		
		while(true) {
			
			int frameCount2 = input.nextInt();
				
			// Reject inputs that do not fall into the specified range.
			if (frameCount2 < 3 || frameCount2 > 5) {
				System.out.println("Invalid Input. Please select a valid " +
						"frame count.");
			}
			
			else {frameCount = frameCount2; break;}
		}	
		
		// Create process of specified frame count.
		int[] process = new int[frameCount];

		// Prompt user to enter page requests based on process size.
		System.out.println("Enter page requests. Please enter exactly" +
				" twice as many pages as the number of frames you selected.");
		
		// Create array to keep track of page requests.
		int[] pageRequests = new int[frameCount * 2];
		int processIndex = 0;
		
		/*
		 * Read page requests from the console and place them into the
		 * pageRequests array.
		 */
		while (input.hasNextInt()) {
			pageRequests[processIndex] = input.nextInt();
			processIndex++;
			
			/*
			 * Only consider the required amount of pages 
			 * given by the user.
			 */
			if (processIndex == pageRequests.length) {break;}
		}
		
		// Close Scanner object.
		input.close();
		
		System.out.println();
		
		// Place the pages into the frames.
		
		// This for-loop is used to traverse the array of page requests.
		for (int i = 0; i < pageRequests.length; i++) {
			
			// Keep track of pages that have been recently used.
			int[] pageStatus = new int[process.length];
			int pageStatusCount = process.length;
			
			// This for-loop is used to traverse the process.
			for (int j = 0; j < process.length; j++) {
				
				/*
				 * Disregard the page request if it has already
				 * been allocated to the frame.
				 */
				if (process[j] == pageRequests[i]) {
					
					/*
					 * Once a page has been been inserted into a frame,
					 * print the page request, then the resulting frames.
					 */
					System.out.println(pageRequests[i]);
					System.out.println("--------------");
					for (int x: process) {System.out.println(x);}
					System.out.println();
					System.out.println();
					break;
				}
				
				// Place the page into the frame if it is empty.
				if (process[j] == 0) {
					process[j] = pageRequests[i];
					System.out.println(pageRequests[i]);
					System.out.println("--------------");
					for (int x: process) {System.out.println(x);}
					System.out.println();
					System.out.println();
					break;
				}
				
				/*
				 * In the event of a page fault, conduct page replacement
				 * using the LRU policy.
				 */
				if (j == process.length - 1 && process[j] != 0) {
					
					/*
					 * Traverse (backwards) the array of page requests to 
					 * find out which page was the least recently used.
					 */
					for (int k = i-1; k > -1; k--) {
						
						/*
						 * Use the pages already in the process to find
						 * out which one has been the least recently used.
						 */
						for (int l = 0; l < process.length; l++) {
							
							// This page has been recently used.
							if (pageRequests[k] == process[l]) {
								
								if (pageStatus[l] == 1) {
									continue;
								}
								
								// This page has been very recently used.
								else {
									pageStatus[l] = 1;
									pageStatusCount--;
								}
								
								// The least-recently-used page has been found.
								if (pageStatusCount == 1) {
									break;
								}
								
							}
						}
						
						if (pageStatusCount == 1) {
							break;
						}
					}
					
					/*
					 *  Find the least-recently-used page and replace it with
					 *  the new page request.
					 */
					for (int m = 0; m < pageStatus.length; m++) {
						if (pageStatus[m] == 0) {
							process[m] = pageRequests[i];
							System.out.println(pageRequests[i]);
							System.out.println("--------------");
							for (int x: process) {System.out.println(x);}
							
							// Print F to indicate a page fault.
							System.out.println("F");
							System.out.println();
							System.out.println();
						}
					}
				}
			}
		}
		
		
	}

}
