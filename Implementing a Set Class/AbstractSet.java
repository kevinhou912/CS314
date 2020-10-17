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

/**
 * Students are to complete this class. 
 * Students should implement as many methods
 * as they can using the Iterator from the iterator 
 * method and the other methods. 
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {

	/* NO INSTANCE VARIABLES ALLOWED.
	 * NO DIRECT REFERENCE TO UnsortedSet OR SortedSet ALLOWED.
	 * (In other words the data types UnsortedSet and SortedSet
	 * will not appear any where in this class.)
	 * Also no direct references to ArrayList or other Java Collections.
	 */

	//Add all method, Efficiency complexity O(N^2)
	public boolean addAll(ISet<E> otherSet) {
		if(otherSet == null) {
			throw new IllegalArgumentException("item can not be null for add");
		}
		//Create an iterator for otherSet
		Iterator<E> otherIt = otherSet.iterator();
		//Make a copy of the size.
		int oldSize = this.size();
		//Loop through otherSet 
		while(otherIt.hasNext()) {
			E otherElement = otherIt.next();
			//If the current element is not in this Set, then we add it into this Set.
			if(!this.contains(otherElement)) {
				this.add(otherElement);
			}
		}
		//Return true if the size changed.
		return this.size() != oldSize;
	}

	//Add all method, Efficiency complexity O(N)
	public boolean contains(E item) {
		if(item == null) {
			throw new IllegalArgumentException("item can not be null for contains");
		}
		//Create an iterator 
		Iterator<E> it = this.iterator();
		//Loop through this set
		while(it.hasNext()) {
			E element = it.next();
			//If we find the element, we return true
			if(element.equals(item)) {
				return true;
			}
		}
		return false;
	}

	//Different method, Efficiency complexity O(N^2)
	public ISet<E> difference(ISet<E> otherSet){
		//Create a ISet with the reference to this union other set
		ISet<E> union = this.union(otherSet);
		//Create an Iterator for otherSet
		Iterator<E> it = otherSet.iterator();
		//Loop through other set.
		while(it.hasNext()) {
			E element = it.next();
			//If union set contains the element, then we remove it
			if(union.contains(element)) {
				union.remove(element);
			}
		}
		return union;
	}

	//ContainsAll method, Efficiency complexity O(N^2)
	public boolean containsAll(ISet<E> otherSet) {
		if(otherSet == null) {
			throw new IllegalArgumentException("otherSet can not be null for containsAll");
		}
		//If the size is not the same, then the set can't contain everything
		if(otherSet.size() > this.size()) {
			return false;
		}
		//Create an iterator for otherSet
		Iterator<E> otherIt = otherSet.iterator();
		while(otherIt.hasNext()) {
			E otherElement = otherIt.next();
			//If this set does not contian the element, then we return false.
			if(!this.contains(otherElement)) {
				return false;
			}
		}
		return true;
	}	

	//Equals method, Efficiency complexity O(N^2)
	public boolean equals(Object other) {
		//check if other is a ISet
		if(other instanceof ISet) {
			ISet<?> otherSet = (ISet<?>)other;
			if(otherSet.size() != this.size()) {
				return false;
			}
			//A boolean to track if the element is found in the other set.
			boolean found = false;
			Iterator<E> it = this.iterator();	
			//loop through the element in this set.
			while(it.hasNext()) {
				Iterator<?> otherit = (Iterator<?>) otherSet.iterator();
				E element = it.next();
				//loop through the element in other set.
				while(otherit.hasNext()) {
					//if found one match element, found == true;
					if(element.equals(otherit.next())) {
						found = true;
					}
				}
				//If the element is not found, then return false.
				if(!found) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	//Empty the set, Efficiency complexity O(N^2).
	public void clear() {
		//iterator to go through the set
		Iterator<E> it = this.iterator();
		//a while loop to remove everything in the set
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	//return the size of the set, Efficiency complexity O(N).
	public int size() {
		int count  = 0;
		Iterator<E> it = this.iterator();
		while(it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}
	//Remove method, Efficiency complexity O(N)
	public boolean remove(E item) {
		if(item == null) {
			throw new IllegalArgumentException("item can not be null for remove");
		}
		//Create an iterator for this set
		Iterator<E> it = this.iterator();
		while(it.hasNext()) {
			E element = it.next();
			// If we find the element, then we remove it from the set.
			if(element.equals(item)) {
				it.remove();
				return true;          
			}
		}
		return false;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		String seperator = ", ";
		result.append("(");

		Iterator<E> it = this.iterator();
		while (it.hasNext()) {
			result.append(it.next());
			result.append(seperator);
		}
		// get rid of extra separator
		if (this.size() > 0)
			result.setLength(result.length() - seperator.length());

		result.append(")");
		return result.toString();
	}


}
