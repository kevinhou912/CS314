import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID:kh37228	
 *  email address:kevin.hou@utexas.edu
 *  Grader name:Andrew
 *  Number of slip days I am using:
 */

/* CS314 Students. Put your experiment results and
 * answers to questions here.
 * 
 * 1. When size is 1,000, the time is 1.5023 s ; when size is 2,000, the time is 5.336 s.
 *   so doubling the size will take 4 times longer.
 * 2. The big O is O(N^2). The data support it because when N is twice larger, the time will be 4 times more.
 * 3. when size is 250, the time is about 1.7515 second ; when size is 500, the time is 20.1601 s.
 *    so doubling the size will take 11 times more.
 * 4. 4.The big O is O(N^3). The data do support it. Although doubling data size cost more than 8 times then original,
 *    it is because of the T(N) and other operation make the time more than 11 times longer.
 * 5. The max is 1024 M based on my computer when I put 10,000 as size.
 */

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

	/**
	 * main method that runs simple test on the MathMatrix class
	 *
	 * @param args not used
	 */
	public static void main(String[] args) {
		int[][] data1 = { { 1, 2, 3 }, { 4, 5, 6 } };
		int[][] data2 = { { 2, 1, 1 }, { 2, 3, 1 } };
		int[][] e1;

		// test 1, specify size and values constructor
		MathMatrix mat1 = new MathMatrix(2, 2, 2);
		e1 = new int[][] { { 2, 2 }, { 2, 2 } };
		printTestResult(get2DArray(mat1), e1, 1, "Constructor with size and initial val specified.");

		// test 2, specify size and values constructor
		MathMatrix mat2 = new MathMatrix(3, 3, 1);
		e1 = new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		printTestResult(get2DArray(mat2), e1, 2, "Constructor with size and initial val specified.");

		// tests 3-4, int[][] constructor, deep copy
		mat1 = new MathMatrix(data1);
		data1[0][0] = 4;
		// alter data1. mat1 should be unchanged if deep copy made
		e1 = new int[][] { { 4, 2, 3 }, { 4, 5, 6 } };
		printTestResult(data1, e1, 3, "constructor with one parameter of type int[][]");
		// data1 altered. mat1 should be unchanged if deep copy made
		e1 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
		printTestResult(get2DArray(mat1), e1, 4,
				"constructor with one parameter of type int[][]. Testing deep copy made.");

		// test 5-6, int[][] constructor, deep copy
		mat1 = new MathMatrix(data2);
		data2[0][0] = 1;
		// alter data1. mat1 should be unchanged if deep copy made
		e1 = new int[][] { { 1, 1, 1 }, { 2, 3, 1 } };
		printTestResult(data2, e1, 5, "constructor with one parameter of type int[][]");
		// data1 altered. mat1 should be unchanged if deep copy made
		e1 = new int[][] { { 2, 1, 1 }, { 2, 3, 1 } };
		printTestResult(get2DArray(mat1), e1, 6,
				"constructor with one parameter of type int[][]. Testing deep copy made.");

		// test 7, get numbers of rows
		mat1 = new MathMatrix(data1);
		int numRows = mat1.getNumRows();
		if (numRows == 2) {
			System.out.println("Test numer 7 tests the correctness of the getNumRow method. The test passed");
		} else {
			System.out.println("Test numer 7 tests the correctness of the getNumRow method. The test failed");
		}

		// test 8, get numbers of rows
		int[][] data3 = new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
		mat1 = new MathMatrix(data3);
		int numRows2 = mat1.getNumRows();
		if (numRows2 == 4) {
			System.out.println("Test numer 8 tests the correctness of the getNumRow method. The test passed");
		} else {
			System.out.println("Test numer 8 tests the correctness of the getNumRow method. The test failed");
		}

		// test 9, get number of cols
		mat1 = new MathMatrix(data1);
		int numCols = mat1.getNumColumns();
		if (numCols == 3) {
			System.out.println("Test numer 9 tests the correctness of the getNumColumns method. The test passed");
		} else {
			System.out.println("Test numer 9 tests the correctness of the getNumColumns method. The test failed");
		}

		// test 10 , get number of cols
		data3 = new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
		mat1 = new MathMatrix(data3);
		int numCols2 = mat1.getNumColumns();
		if (numCols2 == 2) {
			System.out.println("Test numer 10 tests the correctness of the getNumColumns method. The test passed");
		} else {
			System.out.println("Test numer 10 tests the correctness of the getNumColumns method. The test failed");
		}

		// test 11, get Value
		data3 = new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
		mat1 = new MathMatrix(data3);
		int val = mat1.getVal(0, 0);
		if (val == 1) {
			System.out.println("Test numer 11 tests the correctness of the getVal method. The test passed");
		} else {
			System.out.println("Test numer 11 tests the correctness of the getVal method. The test failed");
		}
		// test 12 get Value
		data3 = new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
		mat1 = new MathMatrix(data3);
		int val2 = mat1.getVal(1, 1);
		if (val2 == 3) {
			System.out.println("Test numer 12 tests the correctness of the getVal method. The test passed");
		} else {
			System.out.println("Test numer 12 tests the correctness of the getVal method. The test failed");
		}

		// tests 13, addition
		data1[0][0] = 1;
		data2 = new int[][] { { 2, 1, 1 }, { 2, 3, 1 } };
		mat1 = new MathMatrix(data1);
		MathMatrix mat = new MathMatrix(data2);
		MathMatrix mat3 = mat1.add(mat);
		e1 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
		printTestResult(get2DArray(mat1), e1, 13, "add method. Testing mat1 unchanged.");
		e1 = new int[][] { { 2, 1, 1 }, { 2, 3, 1 } };
		printTestResult(get2DArray(mat), e1, 13, "add method. Testing mat2 unchanged.");
		e1 = new int[][] { { 3, 3, 4 }, { 6, 8, 7 } };
		printTestResult(get2DArray(mat3), e1, 13, "add method. Testing mat3 correct result.");

		// test 14, multiplication
		data1 = new int[][] { { 1, 2 }, { 3, 1 }, { 2, 1 } };
		data2 = new int[][] { { 1, 1 }, { 3, 3 } };
		mat1 = new MathMatrix(data1);
		mat2 = new MathMatrix(data2);
		mat3 = mat1.multiply(mat2);
		e1 = new int[][] { { 7, 7 }, { 6, 6 }, { 5, 5 } };
		printTestResult(get2DArray(mat3), e1, 14, "multiply method");

		// test 15, mutiplication
		data1 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		data2 = new int[][] { { 1, 1 }, { 2, 2 }, { 3, 3 } };
		mat1 = new MathMatrix(data1);
		mat2 = new MathMatrix(data2);
		mat3 = mat1.multiply(mat2);
		e1 = new int[][] { { 14, 14 }, { 32, 32 }, { 50, 50 } };
		printTestResult(get2DArray(mat3), e1, 15, "multiply method");

		// test 16, scale
		data1 = new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		mat1 = new MathMatrix(data1);
		mat2 = mat1.getScaledMatrix(2);
		e1 = new int[][] { { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 } };
		printTestResult(get2DArray(mat2), e1, 16, "matrix scale method");

		// test 17, scale
		data1 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		mat1 = new MathMatrix(data1);
		mat2 = mat1.getScaledMatrix(7);
		e1 = new int[][] { { 7, 14, 21 }, { 28, 35, 42 }, { 49, 56, 63 } };
		printTestResult(get2DArray(mat2), e1, 17, "matrix scale method");

		// test 18, transpose
		data1 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		mat1 = new MathMatrix(data1);
		mat2 = mat1.getTranspose();
		e1 = new int[][] { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 } };
		printTestResult(get2DArray(mat2), e1, 18, "matrix transpose method");

		// test 19 transpose
		data1 = new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 2 } };
		mat1 = new MathMatrix(data1);
		mat2 = mat1.getTranspose();
		e1 = new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 2 } };
		printTestResult(get2DArray(mat2), e1, 19, "matrix transpose method");

		// test 20, toString()
		data1 = new int[][] { { 100, 100, 100, -1000 }, { 10000, 100, 100, 10 }, { 1, -1, 4, 0 } };
		mat1 = new MathMatrix(data1);
		String expected = "|   100   100   100 -1000|\n| 10000   100   100    10|\n|     1    -1     4     0|\n";
		if (mat1.toString().equals(expected)) {
			System.out.println("passed test 20, toString method.");
		} else {
			System.out.println("failed test 20, toString method.");
		}

		// test 21, toString()
		data1 = new int[][] { { 10000, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		mat1 = new MathMatrix(data1);
		String expected2 = "| 10000     1     1|\n|     1     1     1|\n|     1     1     1|\n";
		if (mat1.toString().equals(expected2)) {
			System.out.println("passed test 21, toString method.");
		} else {
			System.out.println("failed test 21, toString method.");
		}

		// test 22, upperTriangular
		data1 = new int[][] { { -1, 2, 3, 0 }, { 0, 3, 3, 3 }, { 0, 0, 4, -6 }, { 0, 0, 0, 10 } };
		mat1 = new MathMatrix(data1);
		if (mat1.isUpperTriangular()) {
			System.out.println("Passed test 22, upperTriangular method.");
		} else {
			System.out.println("Failed test 22, upperTriangular method.");
		}

		// test 23, upperTriangular
		data1 = new int[][] { { 7, 6, 5, 0 }, { 0, 4, 3, 3 }, { 0, 0, 1, -1 }, { -1, 2, -3, 4 } };
		mat1 = new MathMatrix(data1);
		if (!mat1.isUpperTriangular()) {
			System.out.println("Passed test 23, upperTriangular method.");
		} else {
			System.out.println("Failed test 23, upperTriangular method.");
		}

		Stopwatch s = new Stopwatch();
		Random r = new Random();
		int size = 1000;                 // size of the square matrix
		double taddition = 0;            // total time for addition
		double tmultiplication = 0;      // total time for multiplication

		// first experiment
		MathMatrix matfirst = createMat(r, size, size, 10);
		MathMatrix matsecond = createMat(r, size, size, 10);

		for (int i = 0; i < 1000; i++) {
			s.start();
			matfirst.add(matsecond);
			s.stop();
			taddition += s.time();
		}
		System.out.println(taddition);
		
		// second experiment
		for (int i = 0; i < 100; i++) {
			s.start();
			matfirst.multiply(matsecond);
			s.stop();
			tmultiplication += s.time();
		}
		System.out.println(tmultiplication);
		// CS314 Students. When ready delete the above tests
		// and add your 24 tests here.

	}

	// method that sums elements of mat, may overflow int!
	// pre: mat != null
	private static int sumVals(MathMatrix mat) {
		if (mat == null) {
			throw new IllegalArgumentException("mat may not be null");
		}
		int result = 0;
		final int ROWS = mat.getNumRows();
		final int COLS = mat.getNumColumns();
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				result += mat.getVal(r, c); // likely to overflow, but can still do simple check
			}
		}
		return result;
	}

	// create a matrix with random values
	// pre: rows > 0, cols > 0, randNumGen != null
	public static MathMatrix createMat(Random randNumGen, int rows, int cols, final int LIMIT) {

		if (randNumGen == null) {
			throw new IllegalArgumentException("randomNumGen variable may no be null");
		} else if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException(
					"rows and columns must be greater than 0. " + "rows: " + rows + ", cols: " + cols);
		}

		int[][] temp = new int[rows][cols];
		final int SUB = LIMIT / 4;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
			}
		}

		return new MathMatrix(temp);
	}

	private static void printTestResult(int[][] data1, int[][] data2, int testNum, String testingWhat) {
		System.out.print("Test number " + testNum + " tests the " + testingWhat + ". The test ");
		String result = equals(data1, data2) ? "passed" : "failed";
		System.out.println(result);
	}

	// pre: m != null, m is at least 1 by 1 in size
	// return a 2d array of ints the same size as m and with
	// the same elements
	private static int[][] get2DArray(MathMatrix m) {
		// check precondition
		if ((m == null) || (m.getNumRows() == 0) || (m.getNumColumns() == 0)) {
			throw new IllegalArgumentException("Violation of precondition: get2DArray");
		}

		int[][] result = new int[m.getNumRows()][m.getNumColumns()];
		for (int r = 0; r < result.length; r++) {
			for (int c = 0; c < result[0].length; c++) {
				result[r][c] = m.getVal(r, c);
			}
		}
		return result;
	}

	// pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1
	// matrices
	// data1 and data2 are rectangular matrices
	// post: return true if data1 and data2 are the same size and all elements are
	// the same
	private static boolean equals(int[][] data1, int[][] data2) {
		// check precondition
		if ((data1 == null) || (data1.length == 0) || (data1[0].length == 0) || !rectangularMatrix(data1)
				|| (data2 == null) || (data2.length == 0) || (data2[0].length == 0) || !rectangularMatrix(data2)) {
			throw new IllegalArgumentException("Violation of precondition: equals check on 2d arrays of ints");
		}
		boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
		int row = 0;
		while (result && row < data1.length) {
			int col = 0;
			while (result && col < data1[0].length) {
				result = (data1[row][col] == data2[row][col]);
				col++;
			}
			row++;
		}

		return result;
	}

	// method to ensure mat is rectangular
	// pre: mat != null, mat is at least 1 by 1
	private static boolean rectangularMatrix(int[][] mat) {
		if (mat == null || mat.length == 0 || mat[0].length == 0) {
			throw new IllegalArgumentException(
					"Violation of precondition: " + " Parameter mat may not be null" + " and must be at least 1 by 1");
		}
		return MathMatrix.rectangularMatrix(mat);
	}
}
