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
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 * 
 * The data type for E must be a type that implements Comparable.
 * 
 * Students are to implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must be
 * used as the internal storage container. For methods involving two set, if
 * that method can be done more efficiently if the other set is also a SortedSet
 * do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {
	private ArrayList<E> myCon;
	private boolean changed;

	/**
	 * create an empty SortedSet
	 */
	//Constructor for sortedSet.
	public SortedSet() {
		myCon = new ArrayList<E>();
	}

	/**
	 * create a SortedSet out of an unsorted set. <br>
	 * 
	 * @param other != null
	 */
	//Constructor for sortedSet that accept an ISet and change the ISet to a SortedSet. Efficiency Complexity O(NlogN)
	public SortedSet(ISet<E> other) {
		if(other == null) {
			throw new IllegalArgumentException("item can not be null");
		}
		myCon = new ArrayList<E>();
		Iterator<E> otherIt = other.iterator();
		//Copy every element in the ISet.
		while (otherIt.hasNext()) {
			myCon.add(otherIt.next());
		}
		//Sort the element in myCon with mergeSort.
		mergeSort(myCon);
	}

	//MergeSort. O(NlogN)
	public void mergeSort(ArrayList<E> list) {
		ArrayList<E> temp = new ArrayList<E>(list);
		sort(list, temp, 0, list.size() - 1);
	}
	
	//Helper method for merge sort
	private void sort(ArrayList<E> list, ArrayList<E> temp, int low, int high) {
		if( low < high) {
			int center = (low + high) / 2;
			//sort the first half of the list.
			sort(list, temp, low, center);
			//sort the second half of the list.
			sort(list, temp, center + 1, high);
			//merge the two list back into one.
			merge(list, temp, low, center + 1, high);
		}
	}
	
	//Helper method for merge sort
	private void merge( ArrayList<E> list, ArrayList<E> temp, int leftPos, int rightPos, int rightEnd) {
		int leftEnd = rightPos - 1;
		int tempPos = leftPos;
		int numElements = rightEnd - leftPos + 1;
		//main loop
		while( leftPos <= leftEnd && rightPos <= rightEnd){
			if( list.get(leftPos).compareTo(list.get(rightPos)) <= 0) {
				temp.set(tempPos,list.get(leftPos));
				leftPos++;
			}
			else{
				temp.set(tempPos,list.get(rightPos));
				rightPos++;
			}
			tempPos++;
		}
		//copy rest of left half
		while( leftPos <= leftEnd){
			temp.set(tempPos,list.get(leftPos));
			tempPos++;
			leftPos++;			                      
		}
		//copy rest of right half
		while( rightPos <= rightEnd){
			temp.set(tempPos,list.get(rightPos));
			tempPos++;
			rightPos++;			                      
		}
		//Copy temp back into list
		for(int i = 0; i < numElements; i++, rightEnd--) {
			list.set(rightEnd,temp.get(rightEnd));
		}
	}

	/**
	 * Return the smallest element in this SortedSet. <br>
	 * pre: size() != 0
	 * 
	 * @return the smallest element in this SortedSet.
	 */
	//Efficiency complexity O(1)
	public E min() {
		if (myCon.size() == 0) {
			throw new IllegalArgumentException("size can not be 0");
		}
		return myCon.get(0);
	}

	/**
	 * Return the largest element in this SortedSet. <br>
	 * pre: size() != 0
	 * 
	 * @return the largest element in this SortedSet.
	 */
	//Efficiency complexity O(1)
	public E max() {
		if (myCon.size() == 0) {
			throw new IllegalArgumentException("size can not be 0");
		}
		return myCon.get(size() - 1);
	}

	@Override
	//binary search
	//Add method, Efficiency complexity O(N).
	public boolean add(E item) {
		if(item == null) {
			throw new IllegalArgumentException("item can not be null for add");
		}
		//Check if item is in the list already.
		if(this.contains(item)) {
			return false;
		}
		int index = 0;
		//We also add item if the list is empty.
		if(myCon.size() == 0) {
			myCon.add(item);
			return true;
		}else {	
			Iterator<E> it = this.iterator();
			while(it.hasNext()) {
				E element = it.next();
				//We only add if item is smaller than the current element.
				if(item.compareTo(element) < 0){
					myCon.add(index, item);
					return true;
				}		
				index++;
			}
			//If item is bigger than everything, we add it to the end.
			myCon.add(item);
			return true;
		}
	}

	//Efficiency complexity O(N) if the otherSet is sortedSet, else it is O(NlogN)
	public boolean addAll(ISet<E> otherSet) {
		if(otherSet == null) {
			throw new IllegalArgumentException("item can not be null for addAll");
		}
		SortedSet<E> other = new SortedSet<>(otherSet);
		//Call union to add all the element in this set and other set.
		this.union(other);
		return this.changed;
	}

	//Different method, O(N) if otherSet is also a sortedSet, otherwise it will be O(NlogN).
	public ISet<E> difference(ISet<E> otherSet){
		if(otherSet == null) {
			throw new IllegalArgumentException("item can not be null for intersection");
		}
		//Declare the set.
		SortedSet<E> otherS;
		//check if the otherSet is a sortedSet already.
		if(otherSet instanceof SortedSet) {
			otherS = (SortedSet<E>) otherSet; 
		}else {
			otherS = new SortedSet<E>(otherSet);
		}
		//These are the starting index of this and other set.
		int thisPos = 0;
		int otherPos = 0;
		return differentMerge(otherS, thisPos, otherPos);
	}
	
	//A helper method to get the different between the two set. O(N)
	private ISet<E> differentMerge(SortedSet<E> otherS, int thisPos, int otherPos) {
		SortedSet<E> result = new SortedSet<E>();
		while(thisPos < this.size() && otherPos < otherS.size()) {
			E otherElement = otherS.myCon.get(otherPos);
			E thisElement = myCon.get(thisPos);
			//If the elements are equal, we don't add anything and increment both position
			if(thisElement.compareTo(otherElement) == 0) {
				thisPos++;
				otherPos++;
			//If this element is bigger, we increment other position .
			}else if(thisElement.compareTo(otherElement) > 0){
				otherPos++;
			//If this element is smaller, we add the element to the reuslt and increment this position.
			}else {
				result.myCon.add(thisElement);
				thisPos++;
			}
		}
		//We add the left over element from this Set
		while(thisPos < this.size()) {
			result.myCon.add(myCon.get(thisPos));
			thisPos++;
		}
		
		return result;
	}

	//Intersection method, O(N) if otherSet is also a sortedSet, otherwise it will be O(NlogN).
	public ISet<E> intersection(ISet<E> otherSet) {
		if(otherSet == null) {
			throw new IllegalArgumentException("item can not be null for intersection");
		}
		//Declare the set.
		SortedSet<E> otherS;
		//check if the otherSet is a sortedSet already.
		if(otherSet instanceof SortedSet) {
			otherS = (SortedSet<E>) otherSet; 
		}else {
			otherS = new SortedSet<E>(otherSet);
		}
		//These are the starting index of this and other set.
		int thisPos = 0;
		int otherPos = 0;
		return intersectionMerge(otherS, thisPos, otherPos);
	}

	// a helper method that get the intersection of two set. O(N)
	private ISet<E> intersectionMerge(SortedSet<E> otherS, int thisPos, int otherPos){
		SortedSet<E> result = new SortedSet<E>();
		while(thisPos < this.size() && otherPos < otherS.size()) {
			E otherElement = otherS.myCon.get(otherPos);
			E thisElement = myCon.get(thisPos);
			//If the elements are equal, we add this element.
			if(thisElement.compareTo(otherElement) == 0) {
				result.myCon.add(thisElement);
				//if this size is smaller than the other size, we increment this position.
				//So that is doesn't skip element.
				if(this.size() <= otherS.size()) {
					thisPos++;
				}else {
					otherPos++;
				}
			}
			//if this size is smaller than the other size, we increment other position.
			//So that is doesn't skip element.
			if(this.size() <= otherS.size()) {
				otherPos++;
			}else {
				thisPos++;
			}
		}
		return result;
	}
	
	//Union method, O(N) if otherSet is also a sortedSet, otherwise it will be O(NlogN)
	public ISet<E> union(ISet<E> otherSet) {
		if(otherSet == null) {
			throw new IllegalArgumentException("item can not be null for union");
		}
		//Declare the set.
		SortedSet<E> otherS;
		//check if the otherSet is a sortedSet already.
		if(otherSet instanceof SortedSet) {
			otherS = (SortedSet<E>) otherSet; 
		}else {
			otherS = new SortedSet<E>(otherSet);
		}
		//These are the starting index of this and other set.
		int thisPos = 0;
		int otherPos = 0;
		return unionMerge(otherS, thisPos, otherPos);
	}

	
	 
	// A private helper method that get the union of the two set, Efficiency Complexity O(N)
	private ISet<E> unionMerge(SortedSet<E> otherS, int thisPos, int otherPos){
		//Copy of the current size.
		int oldSize = this.size();
		SortedSet<E> result = new SortedSet<E>();	
		while(thisPos < this.size() && otherPos < otherS.size()) {
			E otherElement = otherS.myCon.get(otherPos);
			E thisElement = myCon.get(thisPos);
			// if this element is smaller than the other element, we add the element and 
			// increment this position.
			if(thisElement.compareTo(otherElement) < 0) {
				result.myCon.add(thisElement);
				thisPos++;
			//If the element is equal, we only add one element and increment both position
			}else if(thisElement.compareTo(otherElement) == 0) {
				result.myCon.add(thisElement);
				thisPos++;
				otherPos++;
				// if this element is bigger than the other element, we add the other element and 
				// increment other position.
			}else {
				result.myCon.add(otherElement);
				otherPos++;
			}
		}
		//We add the left over from thisSet.
		while(thisPos < this.size()) {
			result.myCon.add(myCon.get(thisPos));
			thisPos++;
		}
		//We add the left over from otherSet
		while(otherPos < otherS.size()) {
			result.add(otherS.myCon.get(otherPos));
			otherPos++;
		}
		//The set change as the result of adding both set together.
		changed = oldSize != result.size();
		return result;
	}
	 
	//Efficiency complexity O(1)
	public Iterator<E> iterator() {
		return myCon.iterator();
	}
	
	//Contain method, Efficiency complexity O(logN)
	public boolean contains(E item) {
		//Binary search.
		int left = 0;
		int right = myCon.size() - 1;
		while(left <= right) {
			int mid = (left + right ) / 2;
			//if we find the element, we return true.
			if(myCon.get(mid).compareTo(item) == 0) {
				return true;
			}
			//If the current element is smaller, then we increase the left bound
			if(myCon.get(mid).compareTo(item) < 0) {
				left = mid + 1;
			//If the current element is bigger, we decrease the right bound.
			}else {
				right = mid - 1;
			}
		}

		return false;  	 
	}

	// containsAll method, Efficiency complexity O(N)
	public boolean containsAll(ISet<E> otherSet) {
		if(otherSet == null) {
			throw new IllegalArgumentException("otehrSet can not be null for contiansAll method");
		}
		//Declare a sorted set
		SortedSet<E> otherS;
		//check if the otherSet is a sortedSet already.
		if(otherSet instanceof SortedSet) {
			otherS = (SortedSet<E>) otherSet; 
		}else {
			otherS = new SortedSet<E>(otherSet);
		}
		int thisPos = 0;
		int otherPos = 0;
		//If other size is bigger, we return false.
		if(otherS.size() > this.size()) {
			return false;
		}
		// loop through both set.
		while(thisPos < this.size()) {
			E otherElement = otherS.myCon.get(otherPos);
			E thisElement = myCon.get(thisPos);
			//If this two element is equals, we increment both position tracker.
			if(thisElement.compareTo(otherElement) == 0) {
				thisPos++;
				otherPos++;
			//If this element is smaller, we increment this position.
			}else if(thisElement.compareTo(otherElement) < 0) {
				thisPos++;   
			//If this element at the position is bigger, that mean other set does not have this element.
			}else {
				return false;
			}
		}
		return true;
	}

	//Remove method, Efficiency complexity O(N)
	public boolean remove(E item) {
		if(item == null) {
			throw new IllegalArgumentException("item can not be null for remove method");
		}
		Iterator<E> it = this.iterator();
		while(it.hasNext()) {
			E element = it.next();
			if(item.compareTo(element) == 0) {
				myCon.remove(item);
				return true;
			}
		}
		return false;
	}

	//Clear method, Efficiency complexity O(N).
	 public void clear() {
		 myCon.clear();
	 }
	
	//Equals methods, Efficiency Complexity O(N)
	public boolean equals(Object other) {
		if(other instanceof SortedSet) {
			//Since we don't know what does the other set sort, we use a wild card.
			SortedSet<?> otherSet = (SortedSet<?>)other;
			//If the size is equal, we continue check other conditions.
			if(this.size() == otherSet.size()) {
				for(int i = 0 ; i < this.size(); i++) {
					//If we find one unequal element, then we return false.
					if(!myCon.get(i).equals(otherSet.myCon.get(i))) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	//Efficiency Complexity O(1)
	public int size() {
		return myCon.size();
	}
}
