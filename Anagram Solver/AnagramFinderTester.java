import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/* CS 314 STUDENTS: FILL IN THIS HEADER AND THEN COPY AND PASTE IT TO YOUR
 * LetterInventory.java AND AnagramSolver.java CLASSES.
 *
 * Student information for assignment: Kevin Hou
 *
 *  On my honor, Kevin Hou, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: kh37228
 *  email address: kevinhou@utexas.edu
 *  Grader name: Andrew Smith
 *  Number of slip days I am using: 0
 */

public class AnagramFinderTester{


    private static final String testCaseFileName = "testCaseAnagrams.txt";
    private static final String dictionaryFileName = "d3.txt";

    /**
     * main method that executes tests.
     * @param args Not used.
     */
    public static void main(String[] args) {
    	
    	//My 2 test per method
        letterInventoryMyTest();
        // tests on the anagram solver itself
        boolean displayAnagrams = getChoiceToDisplayAnagrams();
        AnagramSolver solver = new AnagramSolver(AnagramMain.readWords(dictionaryFileName));
        runAnagramTests(solver, displayAnagrams);
    }

    private static void letterInventoryMyTest() {
    	// size()
        LetterInventory inventory1 = new LetterInventory("");
        Object expected = 0;
        Object actual = inventory1.size();
        showTestResults(expected, actual, 1, " size on empty LetterInventory");

        LetterInventory inventory2 = new LetterInventory("apple");
        expected = 5;
        actual = inventory2.size();
        showTestResults(expected, actual, 2, " size on LetterInventory with the word 'apple'");

        //get()
        expected = 1;
        actual = inventory2.get('a');
        showTestResults(expected, actual, 3, " get 'a' on LetterInventory with the word 'apple'");

        expected = 2;
        actual = inventory2.get('p');
        showTestResults(expected, actual, 4, " get 'p' on LetterInventory with the word 'apple'");

        //isEmpty
        expected = true;
        actual = inventory1.isEmpty();
        showTestResults(expected, actual, 5, " isEmpty on empty LetterInventory");

        expected = false;
        actual = inventory2.isEmpty();
        showTestResults(expected, actual, 6, " isEmpty on a LetterInventory with the word 'apple'");
        
        
        //toString
        expected = "";
        actual = inventory1.toString();
        showTestResults(expected, actual, 7, " LetterInventory toString on empty LetterInventory");

        expected = "aelpp";
        actual = inventory2.toString();
        showTestResults(expected, actual, 8, " LetterInventory toString on a LetterInventory with the word 'apple'");
      
        //Constructor and toString
        LetterInventory inventory3 = new LetterInventory("CS314 computer scince");
        expected = "cccceeimnoprsstu";
        actual = inventory3.toString();
        showTestResults(expected, actual, 9, " LetterInventory constructor and toString");

        inventory3 = new LetterInventory("Hey Mike!");
        expected = "eehikmy";
        actual = inventory3.toString();
        showTestResults(expected, actual, 10, " LetterInventory constructor and toString");

        //Subtract   
        expected = null;
        actual = inventory1.subtract(inventory2);
        showTestResults(expected, actual, 11, "LetterInventory subtract");

        inventory1 = new LetterInventory("apple");
        expected = "";
        actual = inventory1.subtract(inventory2).toString();
        showTestResults(expected, actual, 12, "LetterInventory subtract");

        //Add
        expected = "aaeellpppp";
        actual = inventory1.add(inventory2).toString();
        showTestResults(expected, actual, 13, "LetterInventory add");

        expected = "aeeehiklmppy";
        actual = inventory1.add(inventory3).toString();
        showTestResults(expected, actual, 14, "LetterInventory add");

        //Equals
        expected = true;
        actual = inventory1.equals(inventory2);
        showTestResults(expected, actual, 15, "LetterInventory equals");
        
        expected = false;
        actual = inventory1.equals(inventory3);
        showTestResults(expected, actual, 16, "LetterInventory equals");
       
    }

      

    private static void cs314StudentTestsForLetterInventory() {
        // CS314 Students, delete the above tests when you turn in your assignment
        // CS314 Students add your LetterInventory tests here. 
    }

    private static boolean getChoiceToDisplayAnagrams() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter y or Y to display anagrams during tests: ");
        String response = console.nextLine();
        console.close();
        return response.length() > 0 && response.toLowerCase().charAt(0) == 'y';
    }

    private static boolean showTestResults(Object expected, Object actual, int testNum, String featureTested) {
        System.out.println("Test Number " + testNum + " testing " + featureTested);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result: " + actual);
        boolean passed = (actual == null && expected == null) || (actual != null && actual.equals(expected));
        if (passed) {
            System.out.println("Passed test " + testNum);
        } else {
            System.out.println("!!! FAILED TEST !!! " + testNum);
        }
        System.out.println();
        return passed;
    }

    /**
     * Method to run tests on Anagram solver itself.
     * pre: the files d3.txt and testCaseAnagrams.txt are in the local directory
     * 
     * assumed format for file is
     * <NUM_TESTS>
     * <TEST_NUM>
     * <MAX_WORDS>
     * <PHRASE>
     * <NUMBER OF ANAGRAMS>
     * <ANAGRAMS>
     */
    private static void runAnagramTests(AnagramSolver solver, boolean displayAnagrams) {
        int solverTestCases = 0;
        int solverTestCasesPassed = 0;
        Stopwatch st = new Stopwatch();
        try {
            Scanner sc = new Scanner(new File(testCaseFileName));
            final int NUM_TEST_CASES = Integer.parseInt(sc.nextLine().trim());
            System.out.println(NUM_TEST_CASES);
            for (int i = 0; i < NUM_TEST_CASES; i++) {
                // expected results
                TestCase currentTest = new TestCase(sc);
                solverTestCases++;
                st.start();
                // actual results
                List<List<String>> actualAnagrams = solver.getAnagrams(currentTest.phrase, currentTest.maxWords);
                st.stop();
                if (checkPassOrFailTest(currentTest, actualAnagrams)) {
                    solverTestCasesPassed++;
                }
                System.out.println();
                System.out.println("Time to find anagrams: " + st.time());
                if (displayAnagrams) {
                    displayAnagrams("actual anagrams", actualAnagrams);
                    System.out.println();
                    displayAnagrams("expected anagrams", currentTest.anagrams);
                }
                System.out.println("\n******************************************\n");
                // System.out.println("Number of calls to recursive helper method: " + NumberFormat.getNumberInstance(Locale.US).format(AnagramSolver.callsCount));
            }
            sc.close();
        } catch(IOException e) {
            System.out.println("\nProblem while running test cases on AnagramSolver. Check" +
                            " that file testCaseAnagrams.txt is in the correct location.");
            System.out.println(e);
            System.out.println("AnagramSolver test cases run: " + solverTestCases);
            System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
        }
        System.out.println("\nAnagramSolver test cases run: " + solverTestCases);
        System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
    }


    // print out all of the anagrams in a list of anagram
    private static void displayAnagrams(String type,
                    List<List<String>> anagrams) {

        System.out.println("Results for " + type);
        System.out.println("num anagrams: " + anagrams.size());
        System.out.println("anagrams: ");
        for(List<String> singleAnagram : anagrams)
            System.out.println(singleAnagram);
    }


    // determine if the test passed or failed
    private static boolean checkPassOrFailTest(TestCase currentTest,
                    List<List<String>> actualAnagrams) {

        boolean passed = true;
        System.out.println();
        System.out.println("Test number: " + currentTest.testCaseNumber);
        System.out.println("Phrase: " + currentTest.phrase);
        System.out.println("Word limit: " + currentTest.maxWords);
        System.out.println("Expected Number of Anagrams: " + currentTest.anagrams.size());
        if(actualAnagrams.equals(currentTest.anagrams)) {
            System.out.println("Passed Test");
        } else {
            System.out.println("\n!!! FAILED TEST CASE !!!");
            System.out.println("Recall MAXWORDS = 0 means no limit.");
            System.out.println("Expected number of anagrams: " + currentTest.anagrams.size());
            System.out.println("Actual number of anagrams:   " + actualAnagrams.size());
            if(currentTest.anagrams.size() == actualAnagrams.size()) {
                System.out.println("Sizes the same, but either a difference in anagrams or anagrams not in correct order.");
            }
            System.out.println();
            passed = false;
        }  
        return passed;
    }

    // class to handle the parameters for an anagram test 
    // and the expected result
    private static class TestCase {

        private int testCaseNumber;
        private String phrase;
        private int maxWords;
        private List<List<String>> anagrams;

        // pre: sc is positioned at the start of a test case
        private TestCase(Scanner sc) {
            testCaseNumber = Integer.parseInt(sc.nextLine().trim());
            maxWords = Integer.parseInt(sc.nextLine().trim());
            phrase = sc.nextLine().trim();
            anagrams = new ArrayList<>();
            readAndStoreAnagrams(sc);
        }

        // pre: sc is positioned at the start of the resulting anagrams
        // read in the number of anagrams and then for each anagram:
        //  - read in the line
        //  - break the line up into words
        //  - create a new list of Strings for the anagram
        //  - add each word to the anagram
        //  - add the anagram to the list of anagrams
        private void readAndStoreAnagrams(Scanner sc) {
            int numAnagrams = Integer.parseInt(sc.nextLine().trim());
            for (int j = 0; j < numAnagrams; j++) {
                String[] words = sc.nextLine().split("\\s+");
                ArrayList<String> anagram = new ArrayList<String>();
                for (String st : words) {
                    anagram.add(st);
                }
                anagrams.add(anagram);
            }
            assert anagrams.size() == numAnagrams : "Wrong number of angrams read or expected";
        }
    }
    
   
}
