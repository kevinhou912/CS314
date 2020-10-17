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
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * A collection of NameRecords. Stores NameRecord objects and provides methods
 * to select NameRecords based on various criteria.
 */
public class Names {

	private int baseDecade;
	private int numDecade;
	private ArrayList<NameRecord> names;

	/**
	 * Construct a new Names object based on the data source the Scanner sc is
	 * connected to. Assume the first two lines in the data source are the base year
	 * and number of decades to use. Any lines without the correct number of decades
	 * are discarded and are not part of the resulting Names object. Any names with
	 * ranks of all 0 are discarded and not part of the resulting Names object.
	 * 
	 * @param sc Is connected to a data file with baby names and positioned at the
	 *           start of the data source.
	 */

	// Constructor of Names class.
	public Names(Scanner sc) {
		baseDecade = sc.nextInt();
		numDecade = sc.nextInt();
		names = new ArrayList<NameRecord>();

		// Move the cursor from the second line to the third line.
		sc.nextLine();

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			// String delimiter to seperates the elements in the line.
			String delimiter = "\\s+";
			String[] parsedData = line.split(delimiter);
			// Determine if the number of rank matches the total decade.
			if (parsedData.length - 1 == numDecade) {
				// pass the baseDecade and the name line into the NameRecord.
				NameRecord records = new NameRecord(baseDecade, line);
				// add NameRecord to the ArrayList.
				names.add(records);
			}

		}
		// Sort the ArrayList.
		Collections.sort(names);
	}

	/**
	 * Returns an ArrayList of NameRecord objects that contain a given substring,
	 * ignoring case. The names must be in sorted order based on name.
	 * 
	 * @param partialName != null, partialName.length() > 0
	 * @return an ArrayList of NameRecords whose names contains partialName. If
	 *         there are no NameRecords that meet this criteria returns an empty
	 *         list.
	 */

	// Return the NameRecord with the names that contains the pass in String.
	public ArrayList<NameRecord> getMatches(String partialName) {
		ArrayList<NameRecord> result = new ArrayList<NameRecord>();
		String loweredPartial = partialName.toLowerCase();
		// This loop get NameRecord element from the name list.
		for (int i = 0; i < names.size(); i++) {
			NameRecord nr = names.get(i);
			String loweredNames = nr.getName().toLowerCase();
			// check if the name string contains the user input string.
			if (loweredNames.contains(loweredPartial)) {
				result.add(nr);
			}
		}
		return result;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been ranked in the top
	 * 1000 or better for every decade. The Strings must be in sorted order based on
	 * name.
	 * 
	 * @return A list of the names that have been ranked in the top 1000 or better
	 *         in every decade. The list is in sorted ascending order. If there are
	 *         no NameRecords that meet this criteria returns an empty list.
	 */

	// This loop get the name that every decade.
	public ArrayList<String> rankedEveryDecade() {
		ArrayList<String> result = new ArrayList<String>();
		// This loop get NameRecord element from the name list.
		for (int i = 0; i < names.size(); i++) {
			NameRecord nr = names.get(i);
			// check if the name is ranked every decade.
			if (nr.rankedAllDecades()) {
				result.add(nr.getName());
			}
		}
		return result;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been ranked in the top
	 * 1000 or better in exactly one decade. The Strings must be in sorted order
	 * based on name.
	 * 
	 * @return A list of the names that have been ranked in the top 1000 or better
	 *         in exactly one decade. The list is in sorted ascending order. If
	 *         there are no NameRecords that meet this cr iteria returns an empty
	 *         list.
	 */

	// This loop get the name that only ranked once.
	public ArrayList<String> rankedOnlyOneDecade() {
		ArrayList<String> result = new ArrayList<String>();
		// This loop get NameRecord element from the name list.
		for (int i = 0; i < names.size(); i++) {
			NameRecord nr = names.get(i);
			// check if the name only ranked once the whole time.
			if (nr.rankedOnce()) {
				result.add(nr.getName());
			}
		}
		return result;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been getting more popular
	 * every decade. The Strings must be in sorted order based on name.
	 * 
	 * @return A list of the names that have been getting more popular in every
	 *         decade. The list is in sorted ascending order. If there are no
	 *         NameRecords that meet this criteria returns an empty list.
	 */

	// This method get the names that are always more popular.
	public ArrayList<String> alwaysMorePopular() {
		ArrayList<String> result = new ArrayList<String>();
		// This loop get NameRecord element from the name list.
		for (int i = 0; i < names.size(); i++) {
			NameRecord nr = names.get(i);
			// check if the element are more popular.
			if (nr.morePopular()) {
				result.add(nr.getName());
			}
		}
		return result;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been getting less popular
	 * every decade. The Strings must be in sorted order based on name.
	 * 
	 * @return A list of the names that have been getting less popular in every
	 *         decade. The list is in sorted ascending order. If there are no
	 *         NameRecords that meet this criteria returns an empty list.
	 */

	// This method get the names that are always less popular.
	public ArrayList<String> alwaysLessPopular() {
		ArrayList<String> result = new ArrayList<String>();
		// This loop get NameRecord element from the name list.
		for (int i = 0; i < names.size(); i++) {
			NameRecord nr = names.get(i);
			// check if the element are less popular.
			if (nr.lessPopular()) {
				result.add(nr.getName());
			}
		}
		return result;
	}

	/**
	 * Return the NameRecord in this Names object that matches the given String.
	 * <br>
	 * <tt>pre: name != null</tt>
	 * 
	 * @param name The name to search for.
	 * @return The name record with the given name or null if no NameRecord in this
	 *         Names object contains the given name.
	 */

	// This method find the NameRecord for a particular name.
	public NameRecord getName(String name) {
		if (name == null)
			throw new IllegalArgumentException("The parameter name cannot be null");
		String loweredInput = name.toLowerCase();
		// This loop get NameRecord element from the name list.
		for (int i = 0; i < names.size(); i++) {
			NameRecord nr = names.get(i);
			String loweredName = nr.getName().toLowerCase();
			// compare if the strings are equal.
			if (loweredName.equals(loweredInput)) {
				return nr;
			}
		}
		return null;
	}

	//Sort the top N most popular name and return it as an ArrayList of String.
	public ArrayList<String> sortInMostPopular(int decade, int topN) {
		ArrayList<String> result = new ArrayList<String>();
		int nameRank = 0;
		// this need to add one because we need to skip 0 for the first rank.
		int adjustTopN = topN + 1;
		// This loop control the topN rank.
		for (int j = 1; j < adjustTopN; j++) {
			// This loop get the name record for each name from the name list.
			for (int i = 0; i < names.size(); i++) {
				NameRecord nr = names.get(i);
				nameRank = nr.rankInDecade(decade);
				// This condition check if the rank of the nameRecord has matches the rank we
				// want.
				if (nameRank == j) {
					// If it have, add the name of the nameRecord to the reuslt.
					result.add(nr.getName());
				}
			}
		}

		return result;
	}

}