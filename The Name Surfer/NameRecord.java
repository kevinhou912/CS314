/*  Student information for assignment:
 *
 *  On my honor, Kevin Hou, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:kh37228
 *  email address:kevinhou@utexas.edu
 *  Number of slip days I am using:
 */

import java.util.ArrayList;
import java.util.Scanner;

public class NameRecord implements Comparable<NameRecord> {
	// Using 1001 to represent unranked name
	private static final int UNRANKED = 1001;
	// One decade is 10 years.
	private static final int DECADE = 10;
	private String name;
	private int startDecade;
	private ArrayList<Integer> nameData;

	/*
	 * Constructor of the NameRecord class with two parameter which will be passed
	 * in from Names class.
	 */
	public NameRecord(int baseDecade, String nameInfo) {
		this.startDecade = baseDecade;
		this.nameData = new ArrayList<Integer>();
		// Scanner scan the passed in name line.
		Scanner lineScan = new Scanner(nameInfo);
		// The first element is always name.
		this.name = lineScan.next();
		while (lineScan.hasNextInt()) {
			// Scan each rank of the name
			int rank = lineScan.nextInt();
			// If rank is not zero, we store the rank from the scanner
			if (rank != 0) {
				nameData.add(rank);
			} else {
				// else, we assign the UNRANKED to its rank.
				nameData.add(UNRANKED);
			}
		}
	}

	// Return the name as String.
	public String getName() {
		return name;
	}

	// Return the starting decade.
	public int baseDecade() {
		return startDecade;
	}

	// Return the decade that has the best rank.
	public int bestRankedDecade() {
		int bestRank = UNRANKED;
		int currentRank = 0;
		int bestDecade = 0;
		for (int i = 0; i < nameData.size(); i++) {
			currentRank = nameData.get(i);
			// Loop through the rank of the year and find the best Rank and its index.
			if (bestRank > currentRank) {
				bestRank = currentRank;
				bestDecade = i;
			}
		}
		// convert the index of the best decade into years.
		bestDecade = bestDecade * DECADE + startDecade;

		return bestDecade;
	}

	// Accept a decade as parameter and return the rank of the name in the decade.
	public int rankInDecade(int decade) {
		// this give us the index of the decade in the Arraylist.
		int indexOfDecade = (decade - startDecade) / DECADE;
		// If the rank at the decade is not UNRANKED, we would return the rank.
		if (nameData.get(indexOfDecade) != UNRANKED) {
			return nameData.get(indexOfDecade);
		}
		// Otherwise, return 0 as its rank in the decade.
		return 0;
	}

	// This return the number of ranks the name have.
	public int numRankedDecades() {
		int timesRanked = 0;
		// loop through each rank and check if the name has a rank at that year.
		for (int i = 0; i < nameData.size(); i++) {
			// If there is, then ranked times plus one.
			if (nameData.get(i) != UNRANKED) {
				timesRanked++;
			}
		}
		return timesRanked;
	}

	// Return if the name is ranked every decade.
	public boolean rankedAllDecades() {
		return numRankedDecades() == nameData.size();
	}

	// Return if the names had only ranked once in top 1000.
	public boolean rankedOnce() {
		return numRankedDecades() == 1;
	}

	// Return if the name is more popular over the year.
	public boolean morePopular() {
		// The loop start at index one and compare with the previous.
		for (int i = 1; i < nameData.size(); i++) {
			// If previous rank is more popular or equal, then return false.
			if (nameData.get(i) >= nameData.get(i - 1)) {
				return false;
			}
		}
		return true;
	}

	// Return if the name is less popular over the year.
	public boolean lessPopular() {
		// The loop start at index one and compare with the previous.
		for (int i = 1; i < nameData.size(); i++) {
			// If previous rank is less popular or equal, then return false.
			if (nameData.get(i) <= nameData.get(i - 1)) {

				return false;
			}
		}
		return true;
	}

	// Compare NamerRecord with other.
	public int compareTo(NameRecord other) {
		return this.name.compareTo(other.name);
	}

	// Print the name and its rank.
	public String toString() {
		int currentDecade = this.baseDecade();
		// Store the element.
		StringBuilder sb = new StringBuilder();
		sb.append(name + "\n");
		// Loop through every element in the nameData.
		for (int i = 0; i < nameData.size(); i++) {
			// Get the current rank at the current decade.
			int currentRank = rankInDecade(currentDecade);
			// Add the current decade and rank into the string builder.
			sb.append(currentDecade + ": " + currentRank + "\n");
			// increment currentDecade to get to the next decade.
			currentDecade += DECADE;
		}
		return sb.toString();
	}

}