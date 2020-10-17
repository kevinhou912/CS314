/*  Student information for assignment:
 *
 *  On MY honor, Kevin Hou, this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: kh37228
 *  email address: kevinhou@utexas.edu
 *  Grader name: Andrew Smith
 *  Section number:
 */

//imports

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Various recursive methods to be implemented. Given shell file for CS314
 * assignment.
 */
public class Recursive {

	/**
	 * Problem 1: convert a base 10 int to binary recursively. <br>
	 * pre: n != Integer.MIN_VALUE<br>
	 * <br>
	 * post: Returns a String that represents N in binary. All chars in returned
	 * String are '1's or '0's. Most significant digit is at position 0
	 * 
	 * @param n the base 10 int to convert to base 2
	 * @return a String that is a binary representation of the parameter n
	 */
	// Get the binary number of a base 10 number
	public static String getBinary(int n) {
		if (n == Integer.MIN_VALUE) {
			throw new IllegalArgumentException(
					"Failed precondition: getBinary. " + "n cannot equal Integer.MIN_VALUE. n: " + n);
		}
		String result = "";
		// Check if put a 0 or 1 on the current number
		if (n % 2 == 0) {
			result = "0" + result;
		} else {
			result = "1" + result;
		}
		// Divide by half everytime.
		n = n / 2;
		// no more number
		if (n == 0) {
			return result;
		} else if (n == 1) {
			return "1" + result;
			// if number is negative, add -1 instead.
		} else if (n == -1) {
			return "-1" + result;
		} else {
			// recursion to get all the digit of the binary number
			return getBinary(n) + result;
		}
	}

	/**
	 * Problem 2: reverse a String recursively.<br>
	 * pre: stringToRev != null<br>
	 * post: returns a String that is the reverse of stringToRev
	 * 
	 * @param stringToRev the String to reverse.
	 * @return a String with the characters in stringToRev in reverse order.
	 */
	// Reverse String.
	public static String revString(String stringToRev) {
		if (stringToRev == null) {
			throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");
		}
		String result = "";
		if(stringToRev == "") {
			return result;
		}
		int lastIndex = stringToRev.length() - 1;
		// add from the last character.
		result += stringToRev.charAt(lastIndex);
		// While have character, keep adding the character from the back of the letter.
		if (lastIndex > 0) {
			return result + revString(stringToRev.substring(0, lastIndex--));
		} else {
			return result;
		}
	}

	/**
	 * Problem 3: Returns the number of elements in data that are followed directly
	 * by value that is double that element. pre: data != null post: return the
	 * number of elements in data that are followed immediately by double the value
	 * 
	 * @param data The array to search.
	 * @return The number of elements in data that are followed immediately by a
	 *         value that is double the element.
	 */

	// Check how many next is double pair number are there
	public static int nextIsDouble(int[] data) {
		if (data == null) {
			throw new IllegalArgumentException("Failed precondition: nextIsDouble. parameter may not be null.");
		}
		int result = 0;
		int index = 0;
		// special case, if there is only one number in the array.
		if (data.length == 1) {
			return 0;
		}
		result = nextIsTwice(data, index);
		return result;
	}

	// Helper method that return the number of next number is double the previous
	// number.
	private static int nextIsTwice(int[] data, int index) {
		int count = 0;
		// check if next is double previous
		if (2 * data[index] == data[index + 1] && data[index] != 0) {
			count++;
		}
		index++;
		// keep running recursion if we didn't reach to the end.
		if (index + 1 == data.length) {
			return count;
		} else {
			return count + nextIsTwice(data, index);
		}
	}

	/**
	 * Problem 4: Find all combinations of mnemonics for the given number.<br>
	 * pre: number != null, number.length() > 0, all characters in number are
	 * digits<br>
	 * post: see tips section of assignment handout
	 * 
	 * @param number The number to find mnemonics for
	 * @return An ArrayList<String> with all possible mnemonics for the given number
	 */
	// Return the possible combination of the character that is on the key.
	public static ArrayList<String> listMnemonics(String number) {
		if (number == null || number.length() == 0 || !allDigits(number)) {
			throw new IllegalArgumentException("Failed precondition: listMnemonics");
		}
		// create an array to store all the possible combination.
		ArrayList<String> result = new ArrayList<>();
		// run the recursive methods.
		recursiveMnemonics(result, "", number);
		return result;
	}

	/*
	 * Helper method for listMnemonics mnemonics stores completed mnemonics
	 * mneominicSoFar is a partial (possibly complete) mnemonic digitsLeft are the
	 * digits that have not been used from the original number
	 */
	private static void recursiveMnemonics(ArrayList<String> mnemonics, String mnemonicSoFar, String digitsLeft) {
		// if no more digit left, stop recursion.
		if (digitsLeft.length() == 0) {
			// add the combination into the arraylist.
			mnemonics.add(mnemonicSoFar);
		} else {
			char digit = digitsLeft.charAt(0);
			// find the characters of the digit.
			String letter = digitLetters(digit);
			// remove the first digit each time we run.
			String updateDigitsLeft = digitsLeft.substring(1);
			for (int i = 0; i < letter.length(); i++) {
				// Store one combination
				String newMnemonics = mnemonicSoFar + letter.charAt(i);
				// run recursion to check for the next digit.
				recursiveMnemonics(mnemonics, newMnemonics, updateDigitsLeft);
			}
		}
	}

	// used by method digitLetters
	private static final String[] letters = { "0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };

	/*
	 * helper method for recursiveMnemonics pre: ch is a digit '0' through '9' post:
	 * return the characters associated with this digit on a phone keypad
	 */
	private static String digitLetters(char ch) {
		if (ch < '0' || ch > '9') {
			throw new IllegalArgumentException("parameter ch must be a digit, 0 to 9. Given value = " + ch);
		}
		int index = ch - '0';
		return letters[index];
	}

	/*
	 * helper method for listMnemonics pre: s != null post: return true if every
	 * character in s is a digit ('0' through '9')
	 */
	private static boolean allDigits(String s) {
		if (s == null) {
			throw new IllegalArgumentException("Failed precondition: allDigits. String s cannot be null.");
		}
		boolean allDigits = true;
		int i = 0;
		while (i < s.length() && allDigits) {
			allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
			i++;
		}
		return allDigits;
	}

	/**
	 * Problem 5: Draw a Sierpinski Carpet.
	 * 
	 * @param size  the size in pixels of the window
	 * @param limit the smallest size of a square in the carpet.
	 */
	// Draw carpet.
	public static void drawCarpet(int size, int limit) {
		DrawingPanel p = new DrawingPanel(size, size);
		Graphics g = p.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, size, size);
		g.setColor(Color.WHITE);
		drawSquares(g, size, limit, 0, 0);
	}

	/*
	 * Helper method for drawCarpet Draw the individual squares of the carpet.
	 * 
	 * @param g The Graphics object to use to fill rectangles
	 * 
	 * @param size the size of the current square
	 * 
	 * @param limit the smallest allowable size of squares
	 * 
	 * @param x the x coordinate of the upper left corner of the current square
	 * 
	 * @param y the y coordinate of the upper left corner of the current square
	 */
	private static void drawSquares(Graphics g, int size, int limit, double x, double y) {
		// Size reduce by 3 each time.
		int factor = 3;
		int newSize = size / factor;
		// Draw the rectangle with the new size and coordinate.
		g.fillRect((int) x + newSize, (int) y + newSize, newSize, newSize);
		// if the newSize has not got to the minimum value, keep resizing.
		if (newSize > limit) {
			for (int i = 0; i <= 2; i++) {
				for (int j = 0; j <= 2; j++) {
					// run recursion with the new size and coordinate.
					drawSquares(g, newSize, limit, x + newSize * j, y + newSize * i);
				}
			}
		}

	}

	/**
	 * Problem 6: Determine if water at a given point on a map can flow off the map.
	 * <br>
	 * pre: map != null, map.length > 0, map is a rectangular matrix, 0 <= row <
	 * map.length, 0 <= col < map[0].length <br>
	 * post: return true if a drop of water starting at the location specified by
	 * row, column can reach the edge of the map, false otherwise.
	 * 
	 * @param map The elevations of a section of a map.
	 * @param row The starting row of a drop of water.
	 * @param col The starting column of a drop of water.
	 * @return return true if a drop of water starting at the location specified by
	 *         row, column can reach the edge of the map, false otherwise.
	 */
	// Check if the given cell can flow off the double array
	public static boolean canFlowOffMap(int[][] map, int row, int col) {
		if (map == null || map.length == 0 || !isRectangular(map) || !inbounds(row, col, map)) {
			throw new IllegalArgumentException("Failed precondition: canFlowOffMap");
		}
		// Always return true if the cell in on the edge.
		if (row == 0 || col == 0 || row == map.length - 1 || col == map[0].length - 1) {
			return true;
		} 
		//A boolean to track the result of the recursion.
		boolean canFlow = false;
		// a 2-D array that store the change of directions
		int direction[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		for (int i = 0; i < direction.length; i++) {
			int newRow = row + direction[i][0];
			int newCol = col + direction[i][1];
			//check if the new cell is inbound and if the new cell is smaller than the current cell.			
			if (map[newRow][newCol] < map[row][col]) {
				// recurse until the condition does not match or it flow off the map.
				canFlow = canFlowOffMap(map, newRow, newCol);
			}
		}
		return canFlow;
	}

	/*
	 * helper method for canFlowOfMap - CS314 students you should not have to call
	 * this method, pre: mat != null,
	 */
	private static boolean inbounds(int r, int c, int[][] mat) {
		if (mat == null) {
			throw new IllegalArgumentException("Failed precondition: inbounds. The 2d array mat may not be null.");
		}
		return r >= 0 && r < mat.length && mat[r] != null && c >= 0 && c < mat[r].length;
	}

	/*
	 * helper method for canFlowOfMap - CS314 students you should not have to call
	 * this method, pre: mat != null, mat.length > 0 post: return true if mat is
	 * rectangular
	 */
	private static boolean isRectangular(int[][] mat) {
		if ((mat == null) || (mat.length == 0)) {
			throw new IllegalArgumentException("Failed precondition: isRectangular. "
					+ "The argument mat may not be null and must have at least one row.");
		}

		boolean correct = true;
		final int NUM_COLS = mat[0].length;
		int row = 0;
		while (correct && row < mat.length) {
			correct = (mat[row] != null) && (mat[row].length == NUM_COLS);
			row++;
		}
		return correct;
	}

	/**
	 * Problem 7: Find the minimum difference possible between teams based on
	 * ability scores. The number of teams may be greater than 2. The goal is to
	 * minimize the difference between the team with the maximum total ability and
	 * the team with the minimum total ability. <br>
	 * pre: numTeams >= 2, abilities != null, abilities.length >= numTeams <br>
	 * post: return the minimum possible difference between the team with the
	 * maximum total ability and the team with the minimum total ability.
	 * 
	 * @param numTeams  the number of teams to form. Every team must have at least
	 *                  one member
	 * @param abilities the ability scores of the people to distribute
	 * @return return the minimum possible difference between the team with the
	 *         maximum total ability and the team with the minimum total ability.
	 *         The return value will be greater than or equal to 0.
	 */
	// Create a team with the minimum different between the team.
	public static int minDifference(int numTeams, int[] abilities) {
		if (numTeams < 2 || abilities == null || abilities.length < numTeams) {
			throw new IllegalArgumentException("Invalid parameter");
		}
		// Create teams array to store teams scores.
		int[] teams = new int[numTeams];
		// Create an array to track the number of the member in each team.
		int[] numMember = new int[numTeams];
		int index = 0;
		return getTeam(abilities, teams, index, numMember);
	}

	private static int getTeam(int[] abilities, int[] teams, int index, int[] numMember) {
		int min = Integer.MAX_VALUE;
		// if there is more people in the selecting pool, keep assigning them to team.
		if (index < abilities.length) {
			for (int i = 0; i < teams.length; i++) {
				int temp = 0;
				// Get the sum of the scores of one team.
				teams[i] += abilities[index];
				// number of member plus one.
				numMember[i]++;
				// recursion and store the minimum different.
				temp = getTeam(abilities, teams, index + 1, numMember);

				if (temp < min) {
					min = temp;
				}
				// undo if the current assign people can't be in the team.
				teams[i] -= abilities[index];
				// number of member minus one while removing one.
				numMember[i]--;
			}
			return min;
		} else {
			// return the min
			return getMin(teams, numMember);
		}
	}

	// Get the different between the highest scores and the lowest scores in the
	// group.
	private static int getMin(int[] teams, int[] numMember) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < teams.length; i++) {
			if (numMember[i] == 0) {
				return Integer.MAX_VALUE;
			}
			if (teams[i] < min) {
				min = teams[i];
			}
			if (teams[i] > max) {
				max = teams[i];
			}

		}
		return max - min;
	}

	/**
	 * Problem 8: Maze solver. Return 2 if it is possible to escape the maze after
	 * collecting all the coins. Return 1 if it is possible to escape the maze but
	 * without collecting all the coins. Return 0 if it is not possible to escape
	 * the maze. More details in the assignment handout. <br>
	 * pre: board != null <br>
	 * pre: board is a rectangular matrix <br>
	 * pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*' <br>
	 * pre: there is a single 'S' character present <br>
	 * post: rawMaze is not altered as a result of this method. Return 2 if it is
	 * possible to escape the maze after collecting all the coins. Return 1 if it is
	 * possible to escape the maze but without collecting all the coins. Return 0 if
	 * it is not possible to escape the maze. More details in the assignment
	 * handout.
	 * 
	 * @param rawMaze represents the maze we want to escape. rawMaze is not altered
	 *                as a result of this method.
	 * @return per the post condition
	 */

	// Check if the Maze is solvable
	public static int canEscapeMaze(char[][] rawMaze) {
		if (!isRectangular(rawMaze) && validBoard(rawMaze)) {
			throw new IllegalArgumentException("Invalide Maze");
		}
		// Create a new Maze.
		Maze escape = new Maze(rawMaze);
		// Set the board to the maze board from the Maze that we created.
		char[][] currentMaze = escape.maze;
		// get the beginning coordinate.
		int[] startCord = escape.getStart();
		int currentRow = startCord[0];
		int currentCol = startCord[1];
		// initialize the coins that we have been collected to 0
		int numCoins = 0;
		// Check if the board has an Exit.
		if (!escape.hasExit) {
			return 0;
		}
		return mazeResult(currentMaze, currentRow, currentCol, numCoins);
	}

	// A method that is being recursed to find the result of solving the maze.
	private static int mazeResult(char[][] currentMaze, int row, int col, int coinsCollected) {
		// Recreate a new maze so we don't change the original one.(Also Undo the
		// changes)
		Maze updateEscape = new Maze(currentMaze);
		// reset the board to the new board so we don't change the old board.
		currentMaze = updateEscape.maze;
		//Record the best result for the maze.
		int bestCase = 0;
		//If we did not find the Exit, keep run the recursion.
		if (!updateEscape.foundExit(row, col)) {
			coinsCollected += updateEscape.coinsCollected;
			//Update the game board in the new copied board.
			updateEscape.change(row, col);
			int direction[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
			for (int i = 0; i < direction.length; i++) {
				int newRow = row + direction[i][0];
				int newCol = col + direction[i][1];
				//If the next step is in bound or it is not '*', we can go to the next step
				if (updateEscape.inbound( newRow, newCol) && updateEscape.canMove(newRow, newCol)) {
					//Run the recursion
					int result = mazeResult(currentMaze, newRow, newCol, coinsCollected);
					//Early return the best case
					if (result == 2) {
						return result;
						// keep 1 if we find a solution, and look if there is a better case.
					} else if (result == 1) {
						bestCase = result;
					}
				}
			}
		} else {
			//Return the result after complete all possible case
			return updateEscape.result(coinsCollected);
		}
		//return the best case.
		return bestCase;
	}

	//Check if the board is rectangular
	private static boolean isRectangular(char[][] rawMaze) {
		if ((rawMaze == null) || (rawMaze.length == 0)) {
			throw new IllegalArgumentException("Failed precondition: isRectangular. "
					+ "The argument mat may not be null and must have at least one row.");
		}
		boolean correct = true;
		final int NUM_COLS = rawMaze[0].length;
		int row = 0;
		while (correct && row < rawMaze.length) {
			correct = (rawMaze[row] != null) && (rawMaze[row].length == NUM_COLS);
			row++;
		}
		return correct;
	}

	//Check if the board contains valid element
	private static boolean validBoard(char[][] rawMaze) {
		//The only characters that are allowed to be in the board. 
		String valid = "S$GYE*";
		int countS = 0;
		for (int r = 0; r < rawMaze.length; r++) {
			for (int c = 0; c < rawMaze[0].length; c++) {
				if (rawMaze[r][c] == 'S') {
					countS++;
				}
				//return false as soon as we find one element that doesn't match
				if (valid.indexOf(rawMaze[r][c]) == -1) {
					return false;
				}
			}
		}
		//Each board can only have one Starting point.
		if (countS != 1) {
			return false;
		}
		return true;
	}

	//A Maze class to store the methods.
	private static class Maze {
		//Private static char that represent the chars.
		private final static char DEAD_END = '*';
		private final static char COIN = '$';
		private final static char GREEN = 'G';
		private final static char START = 'S';
		private final static char YELLOW = 'Y';
		private final static char EXIT = 'E';

		//private instance
		private int coinsCollected;
		private boolean hasExit;
		private char[][] maze;

		//Constructor
		private Maze(char[][] rawMaze) {
			maze = deepCopy(rawMaze);
		}

		//DeepCopy the board so that we don't change the original board.
		private char[][] deepCopy(char[][] rawMaze) {
			char[][] result = new char[rawMaze.length][rawMaze[0].length];
			for (int r = 0; r < result.length; r++) {
				for (int c = 0; c < result[0].length; c++) {
					result[r][c] = rawMaze[r][c];
				}
			}
			return result;
		}

		//Check if the point is in the boundary
		private boolean inbound( int row, int col) {
			return row < maze.length && col < maze[0].length && row > -1 && col > -1;
		}

		// Return an array that store the starting row and column.
		private int[] getStart() {
			int[] startCordinate = new int[2];
			for (int r = 0; r < maze.length; r++) {
				for (int c = 0; c < maze[0].length; c++) {
					//find the Start coordinate and store them in the array.
					if (maze[r][c] == START) {
						startCordinate[0] = r;
						startCordinate[1] = c;
					}
					//if there is an exit, set hasExit to true.
					if (maze[r][c] == EXIT) {
						hasExit = true;
					}
				}
			}
			return startCordinate;
		}

		//Return the total coins on the board.
		private int getTotalCoins() {
			int count = 0;
			for (int r = 0; r < maze.length; r++) {
				for (int c = 0; c < maze[0].length; c++) {
					if (maze[r][c] == COIN) {
						count++;
					}
				}
			}
			return count;
		}

		//Change the chars on the board.
		//This is going to change after we move to the next coordinate.
		private void change(int currentRow, int currentCol) {
			//If the current coordinate is on S, we change it to G
			if (maze[currentRow][currentCol] == START) {
				maze[currentRow][currentCol] = GREEN;
				//If the current coordinate is on G, we change it to Y
			} else if (maze[currentRow][currentCol] == GREEN) {
				maze[currentRow][currentCol] = YELLOW;
				//If the current coordinate is on Y, we change it to *
			} else if (maze[currentRow][currentCol] == YELLOW) {
				maze[currentRow][currentCol] = DEAD_END;
				//If the current coordinate is on $, we change it to Y	
			} else if (maze[currentRow][currentCol] == COIN) {
				maze[currentRow][currentCol] = YELLOW;
				coinsCollected++;
			} 
		}

		//Check if we have find a boolean.
		private boolean foundExit(int currentRow, int currentCol) {
			return maze[currentRow][currentCol] == EXIT;
		}

		//Check if the next coordinate is available to go.
		private boolean canMove(int currentRow, int currentCol) {
			return maze[currentRow][currentCol] != DEAD_END;
		}

		//Return the result of the maze if find a exit.
		private int result(int coinsCollected) {
			//if find all the coins, return 2
			if (coinsCollected == getTotalCoins()) {
				return 2;
			} else {
				return 1;
			}
		}

	}
}