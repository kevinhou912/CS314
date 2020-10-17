/*  Student information for assignment:
 *
 *  On my honor, Kevin Hou, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name:Kevin Hou
 *  email address: kevinhou@utexas.edu
 *  UTEID: kh37228
 *  Section 5 digit ID: 50310
 *  Grader name: Andrew Smith
 *  Number of slip days used on this assignment: 1 
 */

// add imports as necessary
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Manages the details of EvilHangman. This class keeps tracks of the possible
 * words from a dictionary during rounds of hangman, based on guesses so far.
 *
 */
public class HangmanManager {

	private Set<String> wordList;
	// TreeSet that sort the guessed character
	private TreeSet<Character> guessedCharacter;
	// The words list that contains all the guessable words.
	private ArrayList<String> activeList;
	private int wordLength;
	private int numGuess;
	private HangmanDifficulty difficulty;
	private String currentPattern;
	private boolean debug;

	/**
	 * Create a new HangmanManager from the provided set of words and phrases. pre:
	 * words != null, words.size() > 0
	 * 
	 * @param words   A set with the words for this instance of Hangman.
	 * @param debugOn true if we should print out debugging to System.out.
	 */
	// Constructor for HangmangManager class
	public HangmanManager(Set<String> words, boolean debugOn) {
		if (words == null || words.size() <= 0) {
			throw new IllegalArgumentException("Words set did not meet requirement");
		}
		debug = debugOn;
		Set<String> wordList = new HashSet<String>(words);
		this.wordList = wordList;
	}

	/**
	 * Create a new HangmanManager from the provided set of words and phrases.
	 * Debugging is off. pre: words != null, words.size() > 0
	 * 
	 * @param words A set with the words for this instance of Hangman.
	 */
	// Constructor for HangmangManager class
	public HangmanManager(Set<String> words) {
		this(words, false);
		if (words == null || words.size() <= 0) {
			throw new IllegalArgumentException("Words set did not meet requirement");
		}

	}

	/**
	 * Get the number of words in this HangmanManager of the given length. pre: none
	 * 
	 * @param length The given length to check.
	 * @return the number of words in the original Dictionary with the given length
	 */
	// return the the number of word for each length
	public int numWords(int length) {
		int num = 0;
		for (String w : wordList) {
			if (w.length() == length) {
				num++;
			}
		}
		return num;
	}

	/**
	 * Get for a new round of Hangman. Think of a round as a complete game of
	 * Hangman.
	 * 
	 * @param wordLen    the length of the word to pick this time. numWords(wordLen)
	 *                   > 0
	 * @param numGuesses the number of wrong guesses before the player loses the
	 *                   round. numGuesses >= 1
	 * @param diff       The difficulty for this round.
	 */
	// Create a new round for Hnagman
	public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff) {
		currentPattern = "";
		wordLength = wordLen;
		numGuess = numGuesses;
		difficulty = diff;
		activeList = new ArrayList<String>();
		guessedCharacter = new TreeSet<Character>();
		// Store the active words based on the length that the user picked.
		for (String w : wordList) {
			if (w.length() == wordLength) {
				activeList.add(w);
			}
		}
		// Default word Pattern.
		for (int i = 0; i < wordLen; i++) {
			currentPattern += "-";
		}
	}

	/**
	 * The number of words still possible (live) based on the guesses so far.
	 * Guesses will eliminate possible words.
	 * 
	 * @return the number of words that are still possibilities based on the
	 *         original dictionary and the guesses so far.
	 */
	// Return the number of the active words.
	public int numWordsCurrent() {
		return activeList.size();
	}

	/**
	 * Get the number of wrong guesses the user has left in this round (game) of
	 * Hangman.
	 * 
	 * @return the number of wrong guesses the user has left in this round (game) of
	 *         Hangman.
	 */
	// Return the guess chance that the user has left.
	public int getGuessesLeft() {
		return numGuess;
	}

	/**
	 * Return a String that contains the letters the user has guessed so far during
	 * this round. The String is in alphabetical order. The String is in the form
	 * [let1, let2, let3, ... letN]. For example [a, c, e, s, t, z]
	 * 
	 * @return a String that contains the letters the user has guessed so far during
	 *         this round.
	 */
	// Return the letter that the user has already guessed.
	public String getGuessesMade() {
		return guessedCharacter.toString();
	}

	/**
	 * Check the status of a character.
	 * 
	 * @param guess The characater to check.
	 * @return true if guess has been used or guessed this round of Hangman, false
	 *         otherwise.
	 */
	// Return a boolean that determine whether the letter that user input is guessed
	// or not.
	public boolean alreadyGuessed(char guess) {
		String guessed = getGuessesMade();
		for (int i = 0; i < guessed.length(); i++) {
			// Return true as soon as one letter match from the guessed letters.
			if (guessed.charAt(i) == guess) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get the current pattern. The pattern contains '-''s for unrevealed (or
	 * guessed) characters and the actual character for "correctly guessed"
	 * characters.
	 * 
	 * @return the current pattern.
	 */
	// Return the current word pattern.
	public String getPattern() {
		return currentPattern;
	}

	// pre: !alreadyGuessed(ch)
	// post: return a tree map with the resulting patterns and the number of
	// words in each of the new patterns.
	// the return value is for testing and debugging purposes
	/**
	 * Update the game status (pattern, wrong guesses, word list), based on the give
	 * guess.
	 * 
	 * @param guess pre: !alreadyGuessed(ch), the current guessed character
	 * @return return a tree map with the resulting patterns and the number of words
	 *         in each of the new patterns. The return value is for testing and
	 *         debugging purposes.
	 */

	// Return the TreeMap that store the pattern as key and the length of the
	// wordlist for the
	// corresponding pattern as value.
	public TreeMap<String, Integer> makeGuess(char guess) {
		if (alreadyGuessed(guess)) {
			throw new IllegalArgumentException("This letter is already guessed");
		}
		TreeMap<String, Integer> result = new TreeMap<String, Integer>();
		// Local treeMap that store the patterns and the wordlist for each pattern.
		TreeMap<String, ArrayList<String>> families = new TreeMap<String, ArrayList<String>>();
		// find patterns and word for each pattern, and update the families treeMap.
		guessedCharacter.add(guess);
		activeListBreakDown(guess, families);
		// A Set that sort the patterns.
		Set<String> keys = families.keySet();
		// A iterator that iterate through the pattern set.
		Iterator<String> keyIt = keys.iterator();
		// Store the Pattern
		ArrayList<Pattern> patterns = new ArrayList<Pattern>();
		// Store the value into the result TreeMap that is going to be returned.
		while (keyIt.hasNext()) {
			String currentKey = keyIt.next();
			int patSize = families.get(currentKey).size();
			if (patSize != 0) {
				result.put(currentKey, patSize);
			}
			patterns.add(new Pattern(currentKey, patSize));
		}
		// Sort the pattern ArrayList.
		Collections.sort(patterns);
		// Check if the pattern have changed to track the number of Guesses that the
		// user has left.
		String temp = this.currentPattern;
		updateWordList(patterns, families);
		if (temp.equals(this.currentPattern)) {
			numGuess--;
		}
		// print debug message if debug is on.
		if (debug) {
			debugMessage();
		}

		return result;
	}

	// A helper method that find the pattern and the pattern's corresponding
	// wordList.
	private void activeListBreakDown(char guess, TreeMap<String, ArrayList<String>> families) {
		String wordPattern = "";
		// Loop through active list to get all the possible patterns.
		for (String w : activeList) {
			wordPattern = wordPattern(w, guess);
			if (!families.containsKey(wordPattern)) {
				families.put(wordPattern, new ArrayList<String>());
			}
			families.get(wordPattern).add(w);
		}
	}

	// Update the active wordList base on the difficulty
	private void updateWordList(ArrayList<Pattern> patterns, TreeMap<String, ArrayList<String>> families) {
		// Hardest word list
		ArrayList<String> hardest = families.get(patterns.get(0).pattern);
		// Hardest pattern
		String hardestPattern = patterns.get(0).pattern;
		// Second hardest word list
		ArrayList<String> secondHardest = hardest;
		// Second hardest pattern
		String secondHardestPattern = hardestPattern;
		if (patterns.size() > 1) {
			secondHardest = families.get(patterns.get(1).pattern);
			secondHardestPattern = patterns.get(1).pattern;
		}
		// round is the same as the size of the guessed letter set.
		int round = guessedCharacter.size();
		if (difficulty == HangmanDifficulty.HARD) {
			activeList = hardest;
			currentPattern = hardestPattern;
		} else if (difficulty == HangmanDifficulty.MEDIUM) {
			if (secondHardest != null) {
				if (round != 0 && round % 4 == 0) {
					activeList = secondHardest;
					currentPattern = secondHardestPattern;
				} else {
					activeList = hardest;
					currentPattern = hardestPattern;
				}
			} else {
				activeList = hardest;
				currentPattern = hardestPattern;
			}
		} else if (difficulty == HangmanDifficulty.EASY) {
			if (secondHardest != null) {
				if (round % 2 == 0) {
					activeList = hardest;
					currentPattern = hardestPattern;
				} else {
					activeList = secondHardest;
					currentPattern = secondHardestPattern;
				}
			} else {
				activeList = hardest;
				currentPattern = hardestPattern;
			}
		}
	}

	// A helper method that return the word pattern for each word.
	private String wordPattern(String word, char guess) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < word.length(); i++) {
			char testCharacter = word.charAt(i);
			if (guessedCharacter.contains(testCharacter)) {
				sb.append(testCharacter);
			} else {
				sb.append('-');
			}
		}
		return sb.toString();
	}

	/**
	 * Return the secret word this HangmanManager finally ended up picking for this
	 * round. If there are multiple possible words left one is selected at random.
	 * <br>
	 * pre: numWordsCurrent() > 0
	 * 
	 * @return return the secret word the manager picked.
	 */
	// Generate a secrete word.
	public String getSecretWord() {
		if (numWordsCurrent() < 0) {
			throw new IllegalArgumentException("There is no word in the current list");
		}
		Random r = new Random();
		// if the active word list size is 1, there is only one word.
		if (activeList.size() == 1) {
			return activeList.get(0);
		}
		int index = r.nextInt(activeList.size());
		return activeList.get(index);
	}

	// Debug message
	private void debugMessage() {
		System.out.print("\n" + "DEBUGGING:");
		if (difficulty == HangmanDifficulty.HARD) {
			System.out.println("Picking hardest list.");
		} else if (difficulty == HangmanDifficulty.MEDIUM) {
			System.out.println("Picking medium list.");
		} else if (difficulty == HangmanDifficulty.EASY) {
			System.out.println("Picking easy list.");
		}
		System.out.print("DEBUGGING:" + "New Pattern is: " + currentPattern + ", New family has " + activeList.size()
				+ " word.");
		System.out.println();

	}

	// A comparable class that helps to sort the pattern base on the difficulty of
	// its word list and itself.
	private static class Pattern implements Comparable<Pattern> {
		private String pattern;
		private int size;
		private int numRevealedChars;

		public Pattern(String pattern, int size) {
			this.pattern = pattern;
			this.size = size;
			this.numRevealedChars = 0;
		}

		//return the values that is going to be used to sort.
		public int compareTo(Pattern other) {
			//if size is the same, compare the number of revealed letter
			//else, return the size different.
			if (this.size == other.size) {
				int revealCharsDiff = 0;
				for (int i = 0; i < pattern.length(); i++) {
					if (this.pattern.charAt(i) != '-') {
						this.numRevealedChars++;
					}
					if (other.pattern.charAt(i) != '-') {
						other.numRevealedChars++;
					}
				}
				revealCharsDiff = this.numRevealedChars - other.numRevealedChars;
				//if the revealed letters are the same, return the string compareTo value
				if (revealCharsDiff != 0) {
					return revealCharsDiff;
				} else {
					return this.pattern.compareTo(other.pattern);
				}

			} else {
				return other.size - this.size;
			}

		}

	}

}