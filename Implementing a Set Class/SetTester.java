/*
 * Student information for assignment: Kevin Hou
 *
 * Number of slip days used:
 * Student 1 (Student whose turnin account is being used)
 *  UTEID: kh37228
 *  email address: kevinhou@utexas.edu	
 *  Grader name: Andrew Smith

 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

/*
 * CS 314 Students, put your results to the experiments
 * and answers to questions here:
 *
 *	SortedSet             Size (KB)        Number of Words          Number of Distinct Words           Time (Sec)
 *
 *	smallDictionary				1 					9							9							2.873E-4
 *  d1                          1x                  6.2x                        6.2x                        2.938x 
 *	Cassell's Natural History	56.3x               982.4x                      318.7x                      189.66x
 *	From School to              447x                8702x                       1585.88x                    1385.8x
 *  Battle-field  
 *
 *
 *	UnsortedSet
 *
 *	smallDictionary              1                   9                            9                          3.039E-4
 *  d1 							 1                   56                          56                          0.947x 
 *	Cassell's Natural History	 56.3x               982.4x                      318.7x                      136.14x
 *	From School to 				 447x                8702x                       1585.88x                    3823x
 *  Battle-field
 *
 *
 *	HashSet
 *
 *	smallDictionary              1                   9                            9                           2.143E-4
 *  d1							 1                   56                          56                           1.5x
 *	Cassell's Natural History	 56.3x               982.4x                      318.7x                       28.85x
 *	From School to 				 447x                8702x                       1585.88x                     205.17x
 *  Battle-field
 *
 *	TreeSet
 *
 *	smallDictionary              1                   9                            9                          0.0044624 
 *  d1							 1                   56                          56                          0.0789x
 *	Cassell's Natural History    56.3x               982.4x                      318.7x                      1.725x
 *  From School to 				 447x                8702x                       1585.88x                    11.449x
 *  Battle-field
 *
 * 1. Based on the experiment result, I think all the order for these two processText method is O(N).
 * 2. My O for unsortedSet and sortedSet are both O(N), and I think for HashSet and TreeSet are maybe O(N^2).
 * 3. Tree Set has sorted the letters in descending order while Hash Set is unsorted. 
 *
 *
 * CS314 Students, why is it unwise to implement all three of the
 * intersection, union, and difference
 * methods in the AbstractSet class:
 */

public class SetTester {

	public static void main(String[] args) {
		mytest();

//		 CS314 Students. Uncomment this section when ready to
//		 run your experiments
		 try {
		 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		 }
		 catch(Exception e) {
		 System.out.println("Unable to change look and feel");
		 }
		 Scanner sc = new Scanner(System.in);
		 String response = "";
		 do {
		 largeTest();
		 System.out.print("Another file? Enter y to do another file: ");
		 response = sc.next();
		 } while( response != null && response.length() > 0
		 && response.substring(0,1).equalsIgnoreCase("y") );

	}


	private static void mytest() {
		ISet<String> s1 = new UnsortedSet<>();
		s1.add("B");
		s1.add("A");
		s1.add("A");
		s1.add("B");

		ISet<String> expected = new UnsortedSet<>();
		expected.add("B");
		expected.add("A");

		//test 1, unsorted constructor and add
		showTestResults(s1.equals(expected), 1, "constructor and add for UnsortedSet");

		//test 2, unsorted clear()
		expected.remove("A");
		expected.remove("B");
		s1.clear();
		showTestResults(s1.equals(expected), 2, "clear method for UnsortedSet");

		//test 3, intersection for unsorted
		s1 = new UnsortedSet<>();
		ISet<String> s2 = new UnsortedSet<>();
		s1.add("A");
		s1.add("B");
		s1.add("C");
		s2.add("B");
		s2.add("C");	
		ISet<String> s3 = s1.intersection(s2);
		expected.add("B");
		expected.add("C");
		showTestResults(s3.equals(expected), 3, "intersection method for UnsortedSet");

		//test 4, containsAll for unsorted
		showTestResults(s1.containsAll(s2) == true, 4, "containsAll method for UnsortedSet");


		//test 5,union for unsorted
		s3 = s1.union(s2);
		expected.add("A");
		showTestResults(s3.equals(expected), 5, "union method for UnsortedSet");

		//test 6, iterator unsorted
		Iterator<String> it = s1.iterator();
		Iterator<String> it2 = s2.iterator();
		String first = it.next();
		first = it.next();
		String second = it2.next();
		showTestResults(first.equals(second), 6, "iterator method for UnsortedSet");

		//test 7, size for unsorted
		showTestResults(s1.size() == 3, 7, "size method for UnsortedSet");

		//test 8, different for unsorted inherited from abstractset
		s3 = s1.difference(s2);
		expected.clear();
		expected.add("A");
		showTestResults(s3.equals(expected), 8, "different method for UnsortedSet");
		
		//test 9, addAll method for unsorted
		s1.clear();
		s2.clear();
		s1.add("A");
		s1.add("C");
		s2.add("B");
		showTestResults(s1.addAll(s2) == true, 9, "addAll method for UnsortedSet");
		
		//test 10, remove method for unsorted.	
		showTestResults(s1.remove("D") == false, 10, "remove method for UnsortedSet");
		
		//test 11, constructor and merge sort for sortedSet
		expected.clear();
		expected = new SortedSet<>();
		expected.add("A");
		expected.add("B");
		expected.add("C");
		ISet<String> ss1 = new SortedSet(s1);
		showTestResults(ss1.equals(expected), 11, "constructor and merge sort method for SortedSet");
		
		//test 12, union and addAll for sorted set
		s1.clear();
		s2 = new SortedSet<>();
		s1.add("A");
		s1.add("C");
		s2.add("B");
		s2.add("D");
		expected.add("D");
		s3 = s1.union(s2);
		showTestResults(s3.equals(expected), 12, "union and addAll method for SortedSet");
  
		//test 13, add and difference method for sorted set
		s3 = s1.difference(s2);
		expected.clear();
		expected.add("A");
		expected.add("C");
		showTestResults(s3.equals(expected), 13, "add and different method for SortedSet");
		
		//test 14, intersection method for sorted set
		s2.add("A");
		s2.add("C");
		s3 = s1.intersection(s2);
		showTestResults(s3.equals(expected), 14, "intersection method for SortedSet");
		
		//test 15, clear and contains
		s1.clear();
		s1.add("A");
		s1.add("B");
		showTestResults(s1.contains("A"), 15, "contains method for SortedSet");
		
		//test 16 containsAll and remove
		expected.remove("D");
		expected.remove("C");
		showTestResults(s1.containsAll(expected), 16, "containsAll and remove method for SortedSet");
		
		//test 17 iterator 
		Iterator<String> itt = s1.iterator();
		String element = itt.next();
		showTestResults(element.equals("A"), 17, " iterator method for SortedSet");
		
		//test18, size
		showTestResults(s1.size() == 2, 18, "size method for SortedSet");
	}

	// print out results of test
	private static void showTestResults(boolean passed, int testNumber,
			String testDescription) {
		if (passed) {
			System.out.print("Passed test ");
		} else {
			System.out.print("Failed test ");
		}
		System.out.println(testNumber + ": " + testDescription);
	}

	/*
	 * Method asks user for file and compares run times to add words from file
	 * to various sets, including CS314 UnsortedSet and SortedSet and Java's
	 * TreeSet and HashSet.
	 */
	private static void largeTest() {
		System.out.println();
		System.out
		.println("Opening Window to select file. You may have to minimize other windows.");
		String text = convertFileToString();
		System.out.println();
		System.out.println("***** CS314 SortedSet: *****");
		processTextCS314Sets(new SortedSet<String>(), text);
		System.out.println("****** CS314 UnsortedSet: *****");
		processTextCS314Sets(new UnsortedSet<String>(), text);
		System.out.println("***** Java HashSet ******");
		processTextJavaSets(new HashSet<String>(), text);
		System.out.println("***** Java TreeSet ******");
		processTextJavaSets(new TreeSet<String>(), text);
	}

	/*
	 * pre: set != null, text != null Method to add all words in text to the
	 * given set. Words are delimited by white space. This version for CS314
	 * sets.
	 */
	private static void processTextCS314Sets(ISet<String> set, String text) {
		Stopwatch s = new Stopwatch();
		Scanner sc = new Scanner(text);
		int totalWords = 0;
		s.start();
		while (sc.hasNext()) {
			totalWords++;
			set.add(sc.next());
		}
		s.stop();
		sc.close();

		showResultsAndWords(set, s, totalWords, set.size());
	}

	/*
	 * pre: set != null, text != null Method to add all words in text to the
	 * given set. Words are delimited by white space. This version for Java
	 * Sets.
	 */
	private static void processTextJavaSets(Set<String> set, String text) {
		Stopwatch s = new Stopwatch();
		Scanner sc = new Scanner(text);
		int totalWords = 0;
		s.start();
		while (sc.hasNext()) {
			totalWords++;
			set.add(sc.next());
		}
		s.stop();
		sc.close();

		showResultsAndWords(set, s, totalWords, set.size());
	}

	/*
	 * Show results of add words to given set.
	 */
	private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, int totalWords,
			int setSize) {

		System.out.println("Time to add the elements in the text to this set: " + s.toString());
		System.out.println("Total number of words in text including duplicates: " + totalWords);
		System.out.println("Number of distinct words in this text " + setSize);

		System.out.print("Enter y to see the contents of this set: ");
		Scanner sc = new Scanner(System.in);
		String response = sc.next();

		if (response != null && response.length() > 0
				&& response.substring(0, 1).equalsIgnoreCase("y")) {
			for (Object o : set)
				System.out.println(o);
		}
		System.out.println();
	}

	/*
	 * Ask user to pick a file via a file choosing window and convert that file
	 * to a String. Since we are evalutatin the file with many sets convert to
	 * string once instead of reading through file multiple times.
	 */
	private static String convertFileToString() {
		// create a GUI window to pick the text to evaluate
		JFileChooser chooser = new JFileChooser(".");
		StringBuilder text = new StringBuilder();
		int retval = chooser.showOpenDialog(null);

		chooser.grabFocus();

		// read in the file
		if (retval == JFileChooser.APPROVE_OPTION) {
			File source = chooser.getSelectedFile();
			try {
				Scanner s = new Scanner(new FileReader(source));

				while (s.hasNextLine()) {
					text.append(s.nextLine());
					text.append(" ");
				}

				s.close();
			} catch (IOException e) {
				System.out.println("An error occured while trying to read from the file: " + e);
			}
		}

		return text.toString();
	}
}
