import java.util.Arrays;
//MathMatrix.java - CS314 Assignment 2

/*  Student information for assignment:
 *
 *  On my honor, Kevin Hou, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:kh37228
 *  email address: kevin.hou@utexas.edu
 *  Unique section number: 50340
 *  Number of slip days I am using:
 */

/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 */
public class MathMatrix {

	//create the 2D array that is going to sort the value later.
	private int[][] nums;

	/**
	 * create a MathMatrix with cells equal to the values in mat.
	 * A "deep" copy of mat is made.
	 * Changes to mat after this constructor do not affect this
	 * Matrix and changes to this MathMatrix do not affect mat
	 * @param  mat  mat !=null, mat.length > 0, mat[0].length > 0,
	 * mat is a rectangular matrix
	 */
	// copying the value from the passed in 2D arrays
	public MathMatrix(int[][] mat) {
		if(mat == null || mat.length < 0 || mat[0].length < 0 || !rectangularMatrix(mat)){
			throw new IllegalArgumentException("parameter is invalid");
		}

		int row = mat.length;
		int col = mat[0].length;
		nums = new int[row][col];

		for(int r = 0; r < nums.length; r++) {
			for(int c = 0; c < nums[0].length; c++) {
				nums[r][c] = mat[r][c];
			}
		}
	}


	/**
	 * create a MathMatrix of the specified size with all cells set to the intialValue.
	 * <br>pre: numRows > 0, numCols > 0
	 * <br>post: create a matrix with numRows rows and numCols columns. 
	 * All elements of this matrix equal initialVal.
	 * In other words after this method has been called getVal(r,c) = initialVal 
	 * for all valid r and c.
	 * @param numRows numRows > 0
	 * @param numCols numCols > 0
	 * @param initialVal all cells of this Matrix are set to initialVal
	 */
	
	// Constructor of the MathMatrix with the inital value
	public MathMatrix(int numRows, int numCols, int initialVal) {
		if(numRows < 0 || numCols < 0){
			throw new IllegalArgumentException("parameter is invalid");
		}
        //initializing nums
		nums = new int[numRows][numCols];
		for(int r = 0; r < numRows; r++) {
			for(int c = 0; c < numCols; c++) {
				nums[r][c] = initialVal;
			}
		}
	}

	/**
	 * Get the number of rows.
	 * @return the number of rows in this MathMatrix
	 */
	
	//Return the number of rows of nums
	public int getNumRows() {
		return nums.length;
	}


	/**
	 * Get the number of columns.
	 * @return the number of columns in this MathMatrix
	 */
	
	//Return the number of columns of nums
	public int getNumColumns(){
		return nums[0].length;
	}


	/**
	 * get the value of a cell in this MathMatrix.
	 * <br>pre: row  0 <= row < getNumRows(), col  0 <= col < getNumColumns()
	 * @param  row  0 <= row < getNumRows()
	 * @param  col  0 <= col < getNumColumns()
	 * @return the value at the specified position
	 */
	
	//Return the value of the matrix at the passed in rows and columns.
	public int getVal(int row, int col) {
		return nums[row][col];
	}


	/**
	 * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
	 * <br>pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
	 * <br>post: This method does not alter the calling object or rightHandSide
	 * @param rightHandSide rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
	 * @return a new MathMatrix that is the result of adding this Matrix to rightHandSide.
	 * The number of rows in the returned Matrix is equal to the number of rows in this MathMatrix.
	 * The number of columns in the returned Matrix is equal to the number of columns in this MathMatrix.
	 */
	// Addition operation
	public MathMatrix add(MathMatrix rightHandSide){
		if(rightHandSide.getNumRows() != getNumRows() ||rightHandSide.getNumColumns() != getNumColumns()){
			throw new IllegalArgumentException("parameter is invalid");
		}
		// create the result mathmatrix to sort the result so that the original matrix doesn't change
		MathMatrix result = new MathMatrix(this.getNumRows(),this.getNumColumns(),0);
		for(int r = 0; r < this.getNumRows(); r++){
			for(int c = 0; c < this.getNumColumns(); c++) {
				result.nums[r][c] = this.nums[r][c] + rightHandSide.nums[r][c]; //addition of the two matrixes at the respect position
			}
		}
		return result;
	}


	/**
	 * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
	 * <br>pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
	 * <br>post: This method does not alter the calling object or rightHandSide
	 * @param rightHandSide rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
	 * @return a new MathMatrix that is the result of subtracting rightHandSide from this MathMatrix.
	 * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
	 * The number of columns in the returned MathMatrix is equal to the number of columns in this MathMatrix.
	 */
	
	//Subtraction operation
	public MathMatrix subtract(MathMatrix rightHandSide){
		if(rightHandSide.getNumRows() != getNumRows() ||rightHandSide.getNumColumns() != getNumColumns()){
			throw new IllegalArgumentException("parameter is invalid");
		}
		// create the result mathmatrix to sort the result so that the original matrix doesn't change
		MathMatrix result = new MathMatrix(this.getNumRows(),this.getNumColumns(),0);
		for(int r = 0; r < this.getNumRows(); r++){
			for(int c = 0; c < this.getNumColumns(); c++) {
				result.nums[r][c] = this.nums[r][c] - rightHandSide.nums[r][c]; //subtraction of the two matrixes at the respect position
			}
		}
		return result;
	}


	/**
	 * implements matrix multiplication, (this MathMatrix) * rightHandSide.
	 * <br>pre: rightHandSide.getNumRows() = getNumColumns()
	 * <br>post: This method should not alter the calling object or rightHandSide
	 * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
	 * @return a new MathMatrix that is the result of multiplying this MathMatrix and rightHandSide.
	 * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
	 * The number of columns in the returned MathMatrix is equal to the number of columns in rightHandSide.
	 */
	
	// multiplication operation
	public MathMatrix multiply(MathMatrix rightHandSide){
		if(rightHandSide.getNumRows() != getNumColumns()){
			throw new IllegalArgumentException("parameter is invalid");
		}
		// create the result mathmatrix to sort the result so that the original matrix doesn't change
		MathMatrix result = new MathMatrix(nums.length,rightHandSide.nums[0].length,0);
		for(int r = 0; r < nums.length; r++) {
			for(int c = 0; c < rightHandSide.nums[0].length; c++){
				for(int s = 0; s < nums[0].length; s++) {         // The first matrix columns and the second matrix row
					result.nums[r][c] += nums[r][s] * rightHandSide.nums[s][c]; // multiplication and add it to the result matrix
				}
			}
		}
		return result;

	} 


	/**
	 * Create and return a new Matrix that is a copy
	 * of this matrix, but with all values multiplied by a scale
	 * value.
	 * <br>pre: none
	 * <br>post: returns a new Matrix with all elements in this matrix 
	 * multiplied by factor. 
	 * In other words after this method has been called 
	 * returned_matrix.getVal(r,c) = original_matrix.getVal(r, c) * factor
	 * for all valid r and c.
	 * @param factor the value to multiply every cell in this Matrix by.
	 * @return a MathMatrix that is a copy of this MathMatrix, but with all
	 * values in the result multiplied by factor.
	 */
	
	// Scale matrix by the pass in factor
	public MathMatrix getScaledMatrix(int factor) {
		// create the result mathmatrix to sort the result so that the original matrix doesn't change
		MathMatrix result = new MathMatrix(this.getNumRows(),this.getNumColumns(),0);
		for(int r = 0; r < this.getNumRows(); r++){
			for(int c = 0; c < this.getNumColumns(); c++) {
				result.nums[r][c] = this.nums[r][c] * factor;  // scale each value by the factor.
			}
		}
		return result;
	}


	/**
	 * accessor: get a transpose of this MathMatrix. 
	 * This Matrix is not changed.
	 * <br>pre: none
	 * @return a transpose of this MathMatrix
	 */
	
	//Transpose operation
	public MathMatrix getTranspose() {
		// create the result mathmatrix to sort the result so that the original matrix doesn't change
		MathMatrix result = new MathMatrix(this.getNumRows(),this.getNumColumns(),0);
		for(int r = 0; r < this.getNumRows(); r++){
			for(int c = 0; c < this.getNumColumns(); c++) {
				result.nums[c][r] = this.nums[r][c]; // transpose the matrix
			}
		}
		return result;
	}


	/**
	 * override equals.
	 * @return true if rightHandSide is the same size as this MathMatrix and all values in the
	 * two MathMatrix objects are the same, false otherwise
	 */
	public boolean equals(Object rightHandSide){
		/* CS314 Students. The following is standard equals
		 * method code. We will learn about in the coming weeks.
		 */
		boolean result = true;
		if( rightHandSide != null && this.getClass() == rightHandSide.getClass()){
			// rightHandSide is a non null MathMatrix
			MathMatrix otherMatrix = (MathMatrix) rightHandSide;

			for(int r = 0; r < otherMatrix.nums.length; r++) {
				for(int c = 0; c < otherMatrix.nums[0].length; c++) {
					if(otherMatrix.nums[r][c] != this.nums[r][c]) {
						return false;
					}
				}
			}
		}
		return result;
	}


	/**
	 * override toString.
	 * @return a String with all elements of this MathMatrix. 
	 * Each row is on a separate line.
	 * Spacing based on longest element in this Matrix.
	 */
	
	//print the matrix 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int max = getMaxLength(this.nums) + 1; // get the maximum length and plus one for the spacing 
		String form="%1$"+max+"d";            // set the format of the output.
		for(int r = 0 ; r < this.nums.length; r++){		
			sb.append("|");
			for(int c = 0 ; c < this.nums[0].length; c++) {
				String output=String.format(form,this.nums[r][c]); //output each value with the format
				sb.append(output);  // add the formated string into the stringbuilder
			}
			sb.append("|\n");
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	//Return the value that has the longest length in the matrix
	private int getMaxLength(int[][] nums) {
		int max = 0;	
		for(int r = 0 ; r < this.nums.length; r++){		
			for(int c = 0 ; c < this.nums[0].length; c++) {
				int length = (""+this.nums[r][c]).length();
				if(length > max){
					max = length;
				}
			}	
		}
		return max;
	}


	/**
	 * Return true if this MathMatrix is upper triangular. To
	 * be upper triangular all elements below the main 
	 * diagonal must be 0.<br>
	 * pre: this is a square matrix. getNumRows() == getNumColumns()  
	 * @return <tt>true</tt> if this MathMatrix is upper triangular,
	 * <tt>false</tt> otherwise. 
	 */
	
	//Determine whether the matrix is uppertriangular or not.
	public boolean isUpperTriangular(){
		if(this.getNumRows() != this.getNumColumns()) {
			throw new IllegalArgumentException("Matrix must be a square !");
		}

		//The nested loop only checks the upper part of the matrix.
		for(int r = 0; r < nums.length; r++){
			for(int c  =0; c < r; c++) {
				if(nums[r][c] != 0) {
					return false;  // return false as soon as one value is not 0 in the upper part of the diagonal.
				}
			}
		}

		return true;
	}

	// method to ensure mat is rectangular
	// pre: mat != null, mat has at least one row
	// return true if all rows in mat have the same
	// number of columns false otherwise.
	public static boolean rectangularMatrix(int[][] mat) {
		if (mat == null || mat.length == 0) {
			throw new IllegalArgumentException("argument mat may not be null and must "
					+ " have at least one row. mat = " + Arrays.toString(mat));
		}
		boolean isRectangular = true;
		int row = 1;
		final int COLUMNS = mat[0].length;
		while (isRectangular && row < mat.length) {
			isRectangular = (mat[row].length == COLUMNS);
			row++;
		}
		return isRectangular;
	}

}