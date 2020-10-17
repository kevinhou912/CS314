

/*  Student information for assignment:
 *
 *  On MY honor, Kevin Hou, this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used:0
 *
 *  Student 1 Kevin Hou
 *  UTEID:kh37228
 *  email address: kevinhou@utexas.edu
 *  Grader name: Andrew Smith
 *  Section number:
 */


import java.util.ArrayList;
import java.util.Collections;

/**
 * Tester class for the methods in Recursive.java
 * @author scottm
 *
 */
public class RecursiveTester {

	// run the tests
	public static void main(String[] args) {
		studentTests();
	}

	// pre: r != null
	// post: run student test
	private static void studentTests() {
		// CS314 students put your tests here
		//Test 1, Binary
		String actualBinary = Recursive.getBinary(170);
		String expectedBinary = "10101010";
		if (actualBinary.equals(expectedBinary))
			System.out.println( "Test 1 passed. get binary.");
		else
			System.out.println( "Test 1 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);

		//Test 2,Binary
		actualBinary = Recursive.getBinary(-1000);
		expectedBinary = "-1111101000";
		if (actualBinary.equals(expectedBinary))
			System.out.println( "Test 2 passed. get binary.");
		else
			System.out.println( "Test 2 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);

		//Test 3, Reverse String
		String actualRev = Recursive.revString("cs314");
		String expectedRev = "413sc";
		if (actualRev.equals(expectedRev))
			System.out.println( "Test 3 passed. reverse string.");
		else
			System.out.println( "Test 3 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);

		//Test 4, Reverse String
		actualRev = Recursive.revString("hello");
		expectedRev = "olleh";
		if (actualRev.equals(expectedRev))
			System.out.println( "Test 4 passed. reverse string.");
		else
			System.out.println( "Test 4 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);

		//Test 5, NextIsDouble

		int[] numsForDouble = {1, 2, 4, 8, 16, 32, 64, 128, 1};
		int actualDouble = Recursive.nextIsDouble(numsForDouble);
		int expectedDouble = 7;
		if (actualDouble == expectedDouble)
			System.out.println( "Test 5 passed. next is double.");
		else
			System.out.println( "Test 5 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);

		//Test 6, NextIsDouble
		int[]  numsForDoubleSecond = {1, 1, 0, 0, 1, 2, 4};
		actualDouble = Recursive.nextIsDouble(numsForDoubleSecond);
		expectedDouble = 2;
		if (actualDouble == expectedDouble)
			System.out.println( "Test 6 passed. next is double.");
		else
			System.out.println( "Test 6 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);

		//Test 7, mnemonics
		ArrayList<String> mnemonics = Recursive.listMnemonics("33");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("DD");
		expected.add("DE");
		expected.add("DF");
		expected.add("ED");
		expected.add("EE");
		expected.add("EF");
		expected.add("FD");
		expected.add("FE");
		expected.add("FF");
		if (mnemonics.equals(expected))
			System.out.println( "Test 7 passed. Phone mnemonics.");
		else
			System.out.println( "Test 7 failed. Phone mnemonics.");

		//Test 8, mnemonics
		ArrayList<String> mnemonicsSecond = Recursive.listMnemonics("10101010");
		ArrayList<String> expectedSecond = new ArrayList<>();
		expectedSecond.add("10101010");
		if (mnemonics.equals(expected))
			System.out.println( "Test 8 passed. Phone mnemonics.");
		else
			System.out.println( "Test 8 failed. Phone mnemonics.");

		//Test 9, canFlowOff
		int[][] world = {{5,5,5,5,5,5},
				{5,5,5,5,5,5},
				{5,5,5,5,5,5},
				{5,5,4,4,5,5},
				{5,5,3,3,5,5},
				{5,5,2,2,5,5},
				{5,5,5,1,5,5},
				{5,5,5,-2,5,5}};
		boolean actual = Recursive.canFlowOffMap(world, 7, 0);
		if (actual == true) {
			System.out.println("Test 9 passed, can flow off map.");
		} else {
			System.out.println("Test 9 failed, can flow off map.");
		}

		//Test 10, canFlowOff
		actual = Recursive.canFlowOffMap(world, 1, 2);
		if (actual == false) {
			System.out.println("Test 10 passed, can flow off map.");
		} else {
			System.out.println("Test 10 failed, can flow off map.");
		}

		//Test 11, fair team
		int[] abilities = {1,1,1};
		if(Recursive.minDifference(3, abilities) == 0)
			System.out.println( "Test 11 passed. min difference.");
		else
			System.out.println( "Test 11 failed. min difference.");

		//Test 12 fair team
		int[] abilitiesSecond = {1, 3, 5, 7, 9, 11};
		if(Recursive.minDifference(3, abilitiesSecond) == 0)
			System.out.println( "Test 12 passed. min difference.");
		else
			System.out.println( "Test 12 failed. min difference.");

		//Test 13, canEscape Maze
		char[][] maze = new char[][] { { '$', 'G', 'S', 'E' },{'$','$','$','$'},{'Y','G','$','Y'} };
		int expect = 2 ;
		int actualMazeResult = Recursive.canEscapeMaze(maze);
		if (actualMazeResult == expect) {
			System.out.println("Test 13 passed, can Escape Maze");
		} else {
			System.out.println("Test 13 failed, can Escape Maze");
		}

		//Test 14, can Escape Maze
		char[][] mazeTwo = new char[][] { { '$', '*', 'S', 'E' },{'*','*','$','$'},{'Y','G','$','Y'} };
		expect = 1 ;
		actualMazeResult = Recursive.canEscapeMaze(mazeTwo);
		if (actualMazeResult == expect) {
			System.out.println("Test 13 passed, can Escape Maze");
		} else {
			System.out.println("Test 13 failed, can Escape Maze");
		}

	}
	
}