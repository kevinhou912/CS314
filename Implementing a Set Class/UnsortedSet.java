/*  Student information for assignment:
 *
 *  On <MY> honor, Kevin Hou , this programming assignment is <MY> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID: kh37228
 *  email address: kevinhou@utexas.edu
 *  Grader name: Andrew Smith
 *  Section number:

 *  
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently. 
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {
	private ArrayList<E> myCon;

	// Constructor for unsortedSet
	public UnsortedSet() {
		myCon = new ArrayList<E>();
	
	}

	// Constructor for unsortedSet that makes a ISet to a unsortedSet
	private UnsortedSet(ISet<E> other) {
		if(other == null) {
			throw new IllegalArgumentException("other set can not be null for unsortedSet constructor");
		}
		myCon = new ArrayList<E>();
		Iterator<E> otherIt = other.iterator();
		//Loop through ISet and copy every element into ISet unsorted myCon
		while(otherIt.hasNext()) {
			myCon.add(otherIt.next());
		}
	}
	
	//Efficiency complexity O(N). add method
	public boolean add(E item) {
		if(item == null) {
			throw new IllegalArgumentException("item can not be null for add");
		}
		// If item has not be in myCon, then we can add it and return true;
		if(!myCon.contains(item)) {
			myCon.add(item); 
			return true;
		}
		return false;
	}

	//Efficiency complexity O(N), clear method
	public void clear() {
		myCon.clear();
	}

	//Efficiency complexity O(N^2) , return an ISet of two ISet intersection part.
	public ISet<E> intersection(ISet<E> otherSet) {
		UnsortedSet<E> result = new UnsortedSet<E>();
		//create UnsortedSet for otherSet ISet
		UnsortedSet<E> other = new UnsortedSet<E>(otherSet);
		//Loop through every element in myCon and check if it is in the otherSet
		for(int i = 0; i < myCon.size(); i++) {
			E element = myCon.get(i);	
			//if other contains this element, then we add it into the result ISet.
			if(other.contains(element)) {
				result.add(element);
			}
		}	
		return result;
	}

	//Efficiency complexity O(N^2), return the sum of the element in both Set
	public ISet<E> union(ISet<E> otherSet) {
		UnsortedSet<E> result = new UnsortedSet<E>();
		//create UnsortedSet for otherSet ISet
		UnsortedSet<E> other = new UnsortedSet<E>(otherSet);
		//Loop through every element in myCon and add it in the result.
		for(int i = 0; i < myCon.size(); i++) {
			E element = myCon.get(i);	
			result.add(element);
		}
		//Loop through every element in other myCon
		for(int i = 0; i < other.myCon.size(); i++) {
			E element = other.myCon.get(i);	
			// if this myCon does not contain the element, then we add it
			// we don't want duplicate value.
			if(!myCon.contains(element)) {
				result.add(element);
			}
		}		
		return result;
	}

	//Efficiency complexity O(1)
	public Iterator<E> iterator() {
		return myCon.iterator();
	}

	//return the size, O(1).
	public int size() {
		return myCon.size();
	}

}
