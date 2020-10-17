/*
 * Student information for assignment: Replace <NAME> in the following with your
 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone. 
 * 
 * On my honor, Kevin Hou, this programming assignment is my own work 
 * and I have not provided this code
 * to any other student. 
 * UTEID: kh37228
 * email address: kevin.hou@utexas.edu
 * Number of slip days I am using:
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NameSurfer {

	// CS314 students, explain your menu option 7 here:
	/*
	 * My menu option 7 is a option that a user input a decade and a top N rank to
	 * find the top Nth name in that particular decade.
	 */

	// CS314 students, Explain your interesting search / trend here:
	/*
	 * So I find the trends that the 3 letters name is getting less and less, longer
	 * name is more popular as the decades increase. This is maybe because more
	 * people name their child with longer name or with full name (like Samuel
	 * instead of Sam) so their name is fancier. Or look better.
	 */

	// CS314 students, add test code for NameRecord class here:

	public static void testCode() {
		// I'm using lines 2158, Jo , and line 2214, Joseph.
		String JoRawData = "Jo 869 622 258 96 62 72 184 363 784 0 0";
		String JosephRawData = "Joseph 5 5 7 10 13 13 12 12 10 10 8";
		int baseDecade = 1900;
		NameRecord JoRecord = new NameRecord(baseDecade, JoRawData);
		NameRecord JosephRecord = new NameRecord(baseDecade, JosephRawData);

		// Test 1. toString for Jo.
		String expected = "Jo\n1900: 869\n1910: 622\n1920: 258\n1930: 96\n1940: "
				+ "62\n1950: 72\n1960: 184\n1970: 363\n1980: 784\n1990: 0\n2000: 0\n";
		String actual = JoRecord.toString();
		System.out.println("expected string:\n" + expected);
		System.out.println("actual string:\n" + actual);
		if (expected.equals(actual)) {
			System.out.println("passed Jo toString test.");
		} else {
			System.out.println("FAILED Jo toString test.");
		}

		// Test 2.toString for Joseph.
		String expectedJoseph = "Joseph\n1900: 5\n1910: 5\n1920: 7\n1930: 10\n1940: "
				+ "13\n1950: 13\n1960: 12\n1970: 12\n1980: 10\n1990: 10\n2000: 8\n";
		String actualJoseph = JosephRecord.toString();
		System.out.println("expected string:\n" + expectedJoseph);
		System.out.println("actual string:\n" + actualJoseph);
		if (expected.equals(actual)) {
			System.out.println("passed Joseph toString test.");
		} else {
			System.out.println("FAILED Joseph toString test.");
		}

		// Test 3.getName for Jo
		String expect3 = "Jo";
		String test3 = JoRecord.getName();
		if (expect3.equals(test3)) {
			System.out.println("passed test 3.");
		} else {
			System.out.println("FAILED to pass test 3.");
		}

		// Test 4.getName for Joseph
		String expect4 = "Joseph";
		String test4 = JosephRecord.getName();
		if (expect4.equals(test4)) {
			System.out.println("passed test 4.");
		} else {
			System.out.println("FAILED to pass test 4.");
		}

		// Test 5. getBase Decade.
		int expect5 = 1900;
		int test5 = JoRecord.baseDecade();
		if (expect5 == test5) {
			System.out.println("passed test 5.");
		} else {
			System.out.println("FAILED to pass test 5.");
		}

		// Test 6.getBase Decade.
		NameRecord random = new NameRecord(1910, JoRawData); // A new object just to test getBaseDecade.
		int expect6 = 1910;
		int test6 = random.baseDecade();
		if (expect6 == test6) {
			System.out.println("passed test 6.");
		} else {
			System.out.println("FAILED to pass test 6.");
		}

		// Test 7. BestRankDecade. for JO
		int expect7 = 1940;
		int test7 = JoRecord.bestRankedDecade();
		if (expect7 == test7) {
			System.out.println("passed test 7.");
		} else {
			System.out.println("FAILED to pass test 7.");
		}

		// Test 8. BestRankDecade. for Jospeh.
		int expect8 = 1900;
		int test8 = JosephRecord.bestRankedDecade();
		if (expect8 == test8) {
			System.out.println("passed test 8.");
		} else {
			System.out.println("FAILED to pass test 8.");
		}

		// Test 9. Rank in Decades for Jo
		int JotestDecade = 1910;
		int expect9 = 622;
		int test9 = JoRecord.rankInDecade(JotestDecade);
		if (expect9 == test9) {
			System.out.println("passed test 9.");
		} else {
			System.out.println("FAILED to pass test 9.");
		}

		// Test 10. Rank in Decades for Joseph
		int JosephtestDecade = 1950;
		int expect10 = 13;
		int test10 = JosephRecord.rankInDecade(JosephtestDecade);
		if (expect10 == test10) {
			System.out.println("passed test 10.");
		} else {
			System.out.println("FAILED to pass test 10.");
		}

		// Test 11. number of ranked decade for Jo.
		int expect11 = 9;
		int test11 = JoRecord.numRankedDecades();
		if (expect11 == test11) {
			System.out.println("passed test 11.");
		} else {
			System.out.println("FAILED to pass test 11.");
		}

		// Test 12. number of ranked decade for Joseph.
		int expect12 = 11;
		int test12 = JosephRecord.numRankedDecades();
		if (expect12 == test12) {
			System.out.println("passed test 12.");
		} else {
			System.out.println("FAILED to pass test 12.");
		}

		// Test 13. Ranked all decade for Jo
		boolean expect13 = false;
		boolean test13 = JoRecord.rankedAllDecades();
		if (expect13 == test13) {
			System.out.println("passed test 13.");
		} else {
			System.out.println("FAILED to pass test 13.");
		}

		// Test14. Ranked all decade for Joseph
		boolean expect14 = true;
		boolean test14 = JosephRecord.rankedAllDecades();
		if (expect14 == test14) {
			System.out.println("passed test 14.");
		} else {
			System.out.println("FAILED to pass test 14.");
		}

		// Test 15. Ranked Once for Jo
		boolean expect15 = false;
		boolean test15 = JoRecord.rankedOnce();
		if (expect15 == test15) {
			System.out.println("passed test 15.");
		} else {
			System.out.println("FAILED to pass test 15.");
		}

		// Test 16. Ranked Once for Joseph.
		boolean expect16 = false;
		boolean test16 = JoRecord.rankedOnce();
		if (expect16 == test16) {
			System.out.println("passed test 16.");
		} else {
			System.out.println("FAILED to pass test 16.");
		}

		// data for 17 - 20
		String one = "Kevin 11 10 9 8 7 6 5 4 3 2 1";
		String two = "Kebbons 1 2 3 4 5 6 7 8 9 10 11";
		NameRecord firstSample = new NameRecord(baseDecade, one);
		NameRecord secondSample = new NameRecord(baseDecade, two);

		// Test 17 is more popular for one.
		boolean expect17 = true;
		boolean test17 = firstSample.morePopular();
		if (expect17 == test17) {
			System.out.println("passed test 17.");
		} else {
			System.out.println("FAILED to pass test 17.");
		}

		// Test 18 is more popular for two.
		boolean expect18 = false;
		boolean test18 = secondSample.morePopular();
		if (expect18 == test18) {
			System.out.println("passed test 18.");
		} else {
			System.out.println("FAILED to pass test 18.");
		}

		// Test 19 is less popular for one.
		boolean expect19 = false;
		boolean test19 = firstSample.lessPopular();
		if (expect19 == test19) {
			System.out.println("passed test 19.");
		} else {
			System.out.println("FAILED to pass test 19.");
		}

		// Test 20 is less popular for two.
		boolean expect20 = true;
		boolean test20 = secondSample.lessPopular();
		if (expect20 == test20) {
			System.out.println("passed test 20.");
		} else {
			System.out.println("FAILED to pass test 20.");
		}

	}

	// A few simple tests for the Names and NameRecord class.
	public static void simpleTest() {
		// raw data for Jake. Alter as necessary for your NameRecord
		String jakeRawData = "Jake 262 312 323 479 484 630 751 453 225 117 97";
		int baseDecade = 1900;
		NameRecord jakeRecord = new NameRecord(baseDecade, jakeRawData);// complete with your constructor
		String expected = "Jake\n1900: 262\n1910: 312\n1920: 323\n1930: 479\n1940: "
				+ "484\n1950: 630\n1960: 751\n1970: 453\n1980: 225\n1990: 117\n2000: 97\n";
		String actual = jakeRecord.toString();
		System.out.println("expected string:\n" + expected);
		System.out.println("actual string:\n" + actual);
		if (expected.equals(actual)) {
			System.out.println("passed Jake toString test.");
		} else {
			System.out.println("FAILED Jake toString test.");
		}

		// Some getName Tests
		String NAME_FILE = "names.txt";
		Names names = new Names(getFileScannerForNames(NAME_FILE));
		String[] testNames = { "Isabelle", "isabelle", "sel", "X1178", "ZZ", "via", "kelly" };
		boolean[] expectNull = { false, false, true, true, true, true, false };
		for (int i = 0; i < testNames.length; i++) {
			performGetNameTest(names, testNames[i], expectNull[i]);
		}
	}

	private static void performGetNameTest(Names names, String name, boolean expectNull) {
		System.out.println("Performing test for this name: " + name);
		if (expectNull) {
			System.out.println("Expected return value is null");
		} else {
			System.out.println("Expected return value is not null");
		}
		NameRecord result = names.getName(name);
		if ((expectNull && result == null) || (!expectNull && result != null)) {
			System.out.println("PASSED TEST.");
		} else {
			System.out.println("Failed test");
		}
	}

	// main method. Driver for the whole program
	public static void main(String[] args) {
		testCode();
		final String NAME_FILE = "names.txt";
		Scanner fileScanner = getFileScannerForNames(NAME_FILE);
		Names namesDatabase = new Names(fileScanner);
		fileScanner.close();
		runOptions(namesDatabase);
	}

	// pre: namesDatabase != null
	// ask user for options to perform on the given Names object.
	// Creates a Scanner connected to System.in.
	private static void runOptions(Names namesDatabase) {
		Scanner keyboard = new Scanner(System.in);
		MenuChoices[] menuChoices = MenuChoices.values();
		MenuChoices menuChoice;
		do {
			showMenu();
			int userChoice = getChoice(keyboard) - 1;
			menuChoice = menuChoices[userChoice];
			if (menuChoice == MenuChoices.SEARCH) {
				search(namesDatabase, keyboard);
			} else if (menuChoice == MenuChoices.ONE_NAME) {
				oneName(namesDatabase, keyboard);
			} else if (menuChoice == MenuChoices.APPEAR_ONCE) {
				appearOnce(namesDatabase);
			} else if (menuChoice == MenuChoices.APPEAR_ALWAYS) {
				appearAlways(namesDatabase);
			} else if (menuChoice == MenuChoices.ALWAYS_MORE) {
				alwaysMore(namesDatabase);
			} else if (menuChoice == MenuChoices.ALWAYS_LESS) {
				alwaysLess(namesDatabase);
			} else if (menuChoice == MenuChoices.STUDENT_SEARCH) {
				mostPopularName(namesDatabase, keyboard);
			}
		} while (menuChoice != MenuChoices.QUIT);
		keyboard.close();
	}

	// pre: fileName != null
	// create a Scanner and return connected to a File with the given name.
	private static Scanner getFileScannerForNames(String fileName) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Problem reading the data file. Returning null for Scanner"
					+ "object. Problems likely to occur." + e);
		}
		return sc;
	}

	// method that shows names that have appeared in every decade
	// pre: n != null
	// post: print out names that have appeared in every decade
	private static void appearAlways(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
		}
		// Store the method result in the result list.
		ArrayList<String> result = namesDatabase.rankedOnlyOneDecade();
		System.out.println(result.size() + "  names appear in every decade. The names are:  ");
		// Print every element in the result.
		for (String name : result) {
			System.out.println(name);
		}
	}

	// method that shows names that have appeared in only one decade
	// pre: n != null
	// post: print out names that have appeared in only one decade
	private static void appearOnce(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
		}
		// Store the method result in the result list.
		ArrayList<String> result = namesDatabase.rankedOnlyOneDecade();
		System.out.println(result.size() + " names appear in exactly one decade. The names are: ");
		// Print every element in the result.
		for (String name : result) {
			System.out.println(name);
		}

	}

	// method that shows names that have gotten more popular
	// in each successive decade
	// pre: n != null
	private static void alwaysMore(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
		}
		// Store the method result in the result list.
		ArrayList<String> result = namesDatabase.alwaysMorePopular();
		System.out.println(result.size() + " names are more popular in every decade.");
		// Print every element in the result.
		for (String name : result) {
			System.out.println(name);
		}
	}

	// method that shows names that have gotten less popular
	// in each successive decade
	// pre: n != null
	// post: print out names that have gotten less popular in each decade
	private static void alwaysLess(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
		}
		// Store the method result in the result list.
		ArrayList<String> result = namesDatabase.alwaysLessPopular();
		System.out.println(result.size() + " names are less popular in every decade.");
		// Print every element in the result.
		for (String name : result) {
			System.out.println(name);
		}
	}

	// method that shows data for one name, or states that name has never been
	// ranked
	// pre: n != null, keyboard != null and is connected to System.in
	// post: print out the data for n or a message that n has never been in the
	// top 1000 for any decade
	private static void oneName(Names namesDatabase, Scanner keyboard) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (namesDatabase == null || keyboard == null) {
			throw new IllegalArgumentException("The parameters cannot be null");
		}
		System.out.print("Enter a name: ");
		String userInput = keyboard.next();
		System.out.println("\n" + namesDatabase.getName(userInput) + "\n");

	}

	// method that shows all names that contain a substring from the user
	// and the decade they were most popular in
	// pre: n != null, keyboard != null and is connected to System.in
	// post: show the correct data

	private static void mostPopularName(Names namesDatabase, Scanner keyboard) {
		if (namesDatabase == null || keyboard == null) {
			throw new IllegalArgumentException("The parameters cannot be null");
		}
		System.out.print("Enter a year: ");
		int userInput = keyboard.nextInt();
		System.out.print("Enter top nth: ");
		// top N minus one to move the rank to match the index in the ArrayList.
		int topN = keyboard.nextInt();
		// Store the method result in the result list.
		ArrayList<String> result = namesDatabase.sortInMostPopular(userInput, topN);
		// Print every element in the result.
		for (String name : result) {
			System.out.println(name);
		}
	}

	private static void search(Names namesDatabase, Scanner keyboard) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (namesDatabase == null || keyboard == null) {
			throw new IllegalArgumentException("The parameters cannot be null");
		}
		System.out.print("Enter a partial name: ");
		String userInput = keyboard.next();
		int count = 0;
		// get the list of the matches name.
		ArrayList<NameRecord> result = namesDatabase.getMatches(userInput);
		System.out.println("\n" + "There are " + result.size() + " matches for " + userInput + "\n");
		System.out.println("The matches with their highest ranking decade are:");
		// Print every element in the result list.
		for (NameRecord matchNames : result) {
			System.out.println(matchNames.getName() + ": " + matchNames.bestRankedDecade());
		}
	}

	// get choice from the user
	// keyboard != null and is connected to System.in
	// return an int that is >= SEARCH and <= QUIT
	private static int getChoice(Scanner keyboard) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (keyboard == null) {
			throw new IllegalArgumentException("The parameter keyboard cannot be null");
		}
		int choice = getInt(keyboard, "Enter choice: ");
		keyboard.nextLine();
		// add one due to zero based indexing of enums, but 1 based indexing of menu
		final int MAX_CHOICE = MenuChoices.QUIT.ordinal() + 1;
		while (choice < 1 || choice > MAX_CHOICE) {
			System.out.println();
			System.out.println(choice + " is not a valid choice");
			choice = getInt(keyboard, "Enter choice: ");
			keyboard.nextLine();

		}
		return choice;
	}

	// ensure an int is entered from the keyboard
	// pre: s != null and is connected to System.in
	private static int getInt(Scanner s, String prompt) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (s == null) {
			throw new IllegalArgumentException("The parameter s cannot be null");
		}
		System.out.print(prompt);
		while (!s.hasNextInt()) {
			s.next();
			System.out.println("That was not an int.");
			System.out.print(prompt);
		}
		return s.nextInt();
	}

	// show the user the menu
	private static void showMenu() {
		System.out.println();
		System.out.println("Options:");
		System.out.println("Enter 1 to search for names.");
		System.out.println("Enter 2 to display data for one name.");
		System.out.println("Enter 3 to display all names that appear in only " + "one decade.");
		System.out.println("Enter 4 to display all names that appear in all " + "decades.");
		System.out.println("Enter 5 to display all names that are more popular " + "in every decade.");
		System.out.println("Enter 6 to display all names that are less popular " + "in every decade.");
		System.out.println("Enter 7 to display the top N rank in a particular decade");
		System.out.println("Enter 8 to quit.");
		System.out.println();
	}

}