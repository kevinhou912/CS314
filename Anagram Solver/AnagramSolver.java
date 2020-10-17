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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnagramSolver {
	Map<String, LetterInventory> anagramResult;

	/*
	 * pre: list != null
	 * 
	 * @param list Contains the words to form anagrams from.
	 */
	//Constructor
	public AnagramSolver(Set<String> dictionary) {
		if(dictionary == null) {
			throw new IllegalArgumentException("Dictionary can not be null for the constructor");
		}
		anagramResult = new HashMap<String, LetterInventory>();
		for (String word : dictionary) {
			LetterInventory inventory = new LetterInventory(word);
			anagramResult.put(word, inventory);
		}

	}

	/*
	 * pre: maxWords >= 0, s != null, s contains at least one English letter. Return
	 * a list of anagrams that can be formed from s with no more than maxWords,
	 * unless maxWords is 0 in which case there is no limit on the number of words
	 * in the anagram
	 */
	
	// A method that find the anagrams
	public List<List<String>> getAnagrams(String s, int maxWords) {
		if (s == null || maxWords < 0) {
			throw new IllegalArgumentException("String can not be null or maxWords can't be smaller than 0 !");
		}
		// the first index of checking the possible word list
		final int start = 0;
		// create the comparator
		AnagramComparator compareList = new AnagramComparator();
		// create the result List
		List<List<String>> result = new ArrayList<List<String>>();
		// create the current word letterInventory
		LetterInventory current = new LetterInventory(s);
		// currentList is the list that contain the combination of words(this is going to be use in the recursion method)
		List<String> currentList = new ArrayList<String>();
		//create the possible word list
		List<String> wordLeft = possibleWords(s);
		int limit = maxWords;
		if (maxWords == 0) {
			limit = wordLeft.size();
		}
		if (wordLeft.size() > 0) {
			findAnagrams(currentList, limit, wordLeft, current, result, start);
			Collections.sort(result, compareList);
		}
		return result;
	}

	// The recursive method for getting the anagrams.
	private void findAnagrams(List<String> currentList, int limit, List<String> wordLeft, LetterInventory currentWord,
			List<List<String>> anagram, int index) {
		if (currentWord.isEmpty()) {
			// deep copy the list so it doesn't change
			List<String> temp = deepCopy(currentList);
			//sort the letter in the currentList by order
			Collections.sort(temp);
			//add the sorted list to the anagram list
			anagram.add(temp);
		} else if (limit > 0) {
			//use loop to go through the possibleWord List
			for (int i = index; i < wordLeft.size(); i++) {
				String word = wordLeft.get(i);
				//subtract the word inventory from the current word letterInventory to check if there are more 
				//character left.
				LetterInventory updateInventory = currentWord.subtract(anagramResult.get(word));
				//if the current word inventory still contain character, that mean it is possible to find a word
				if (updateInventory != null) {
					currentList.add(word);
					//run recursion by minus limit by one, update the current word inventory, and the new index to start
					//the loop of finding word in wordLeft.
					findAnagrams(currentList, limit - 1, wordLeft, updateInventory, anagram, i);
					//undo
					currentList.remove(currentList.size()-1);
				}
			}
		}
	}

	//Get all the possible words from the dictionary
	private List<String> possibleWords(String word) {
		List<String> currentWords = new ArrayList<String>();
		//create the letterInventory of the current word 
		LetterInventory wordInventory = new LetterInventory(word);
		//loop through the dictionary and find the possible words
		for (String words : anagramResult.keySet()) {
			//if the current word letterInventory subtract the letterInventory from the word in dictionary
			//does not equal null. we add it into the currentWords list.
			if (wordInventory.subtract(anagramResult.get(words)) != null) {
				currentWords.add(words);
			}
		}
		return currentWords;
	}

	//deepCopy the List
	private List<String> deepCopy(List<String> currentList) {
		List<String> result = new ArrayList<String>(currentList.size());
		// copy the element in the list
		for (int i = 0; i < currentList.size(); i++) {
			result.add(currentList.get(i));
		}
		return result;
	}

	//A private comparator class that help to compare two List
	private static class AnagramComparator implements Comparator<List<String>> {

		public int compare(List<String> list1, List<String> list2) {
			if (list1.size() == list2.size()) {
				// a loop to check the element at the same index in both list
				for (int i = 0; i < list1.size(); i++) {
					String word1 = list1.get(i);
					String word2 = list2.get(i);
					// if the elements are different, return the compareTo value
					if (word1 != word2) {
						return word1.compareTo(word2);
					}
				}
				//if they are the same, return 0
				return 0;
			}
			//return the size different if the size is not the same
			return list1.size() - list2.size();

		}

	}

}
