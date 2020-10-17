/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment: Kevin Hou
 *
 *  On my honor, Kevin Hou, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:kh37228
 *  email address: kevinhou@utexas.edu
 *  Grader name: Andrew Smith
 *  Number of slip days I am using: 0
 */


public class LetterInventory {
	//The length of the frequency array 
	private static final int NUM_CHARS = 26;
	//The first character is always 'a'
	private static final char FIRST = 'a';
	//The last character is always 'z'
	private static final char LAST = 'z';
	private int size;
	private int[] frequency;

	//Constructor of LetterInventory
	public LetterInventory(String input) {
		if (input == null) {
			throw new IllegalArgumentException("Invalid input!");
		}
		frequency = new int[NUM_CHARS];
		storeFrequency(input);
	}

	
	//A constructor that create a new inventory with the size the frequency
	private LetterInventory(int newSize, int[] newFrequency) {
		size = newSize;
		frequency = newFrequency;
	}

	//A helper method that store the frequency of the characters appear in the word
	private void storeFrequency(String input) {
		input = input.toLowerCase();
		//A loop that go through each character in the word
		for (int i = 0; i < input.length(); i++) {
			char character = input.charAt(i);
			//check bound
			if (character >= FIRST && character <= LAST) {
				//use character subtract first to get the index of the character in the array
				int index = character - FIRST;
				frequency[index]++;
				size++;
			}
		}
	}

	//Return size of the frequency array
	public int size() {
		return size;
	}

	//get the frequency of the character appear in the word
	public int get(char ch) {
		//change the character passed int to lower case.
		ch = Character.toLowerCase(ch);
		if (ch < FIRST && ch > LAST) {
			throw new IllegalArgumentException("ch is not in bound");
		}	
		int index = ch - FIRST;
		return frequency[index];
	}

	//Return if the frequency array is empty
	public boolean isEmpty() {
		return size == 0;
	}

	//Return the String of the frequency array
	public String toString() {
		StringBuilder sb = new StringBuilder();
		//A loop that go through frequency array
		for (int i = 0; i < frequency.length; i++) {
			//A loop that check the frequency of the character
			for (int k = 0; k < frequency[i]; k++) {
				//use first + i and cast it to char to get the character at that array
				sb.append((char) (FIRST + i));
			}
		}
		return sb.toString();
	}

	//Add two LetterInventory 
	public LetterInventory add(LetterInventory other) {
		if (other == null) {
			throw new IllegalArgumentException("Input can not be null for add method");
		}
		int[] newFrequency = new int[NUM_CHARS];
		// A loop that go through both letterinventory's frequency array 
		for (int i = 0; i < frequency.length; i++) {
			//add the frequency of the same character
			newFrequency[i] = frequency[i] + other.frequency[i];
		}
		// change the size of the new frequency
		int newSize = size + other.size;
		//return a new letterInventory by constructing one
		return new LetterInventory(newSize, newFrequency);
	}

	public LetterInventory subtract(LetterInventory other) {
		if (other == null) {
			throw new IllegalArgumentException("Input can not be null for subtract method");
		}
		int[] newFrequency = new int[NUM_CHARS];
		// A loop that go through both letterinventory's frequency array 
		for (int i = 0; i < frequency.length; i++) {
			//if the result smaller than 0, return null
			if (frequency[i] - other.frequency[i] < 0) {
				return null;
			}
			//subtract the frequency of the same character
			newFrequency[i] = frequency[i] - other.frequency[i];
		}
		// change the size of the new frequency
		int newSize = size - other.size;
		//return a new letterInventory by constructing one
		return new LetterInventory(newSize, newFrequency);
	}

	//override the equals method
	public boolean equals(Object obj) {
		//check if the obj is a LetterInventory
		if (obj instanceof LetterInventory) {
			//create a new letter inventory reference it to the casted obj
			LetterInventory other = (LetterInventory) obj;
			for (int i = 0; i < frequency.length; i++) {
				//if one of the frequency is not the same, return false
				if (frequency[i] != other.frequency[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
