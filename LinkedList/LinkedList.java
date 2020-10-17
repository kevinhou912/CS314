
/*
 * Student information for assignment: On my honor, Kevin Hou, this programming
 * assignment is my own work and I have not provided this code to any other
 * student. 
 * UTEID: kh37228
 * email address: kevinhou@utexas.edu
 * Grader name: Andrew Smith
 * Number of slip days I am using: 0
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements IList<E> {
	// CS314 students. Add you instance variables here.
	// You decide what instance variables to use.
	// Must adhere to assignment requirements. No ArrayLists or Java
	// LinkedLists.

	// HEADER is the node that connect the first and last node of the list
	private final DoubleListNode<E> HEADER;
	private int size;

	// CS314 students, add constructors here:
	// Constructing LinkedList class
	public LinkedList() {
		// initialize Header and set the next and previous of HEADER to itself
		HEADER = new DoubleListNode<>();
		HEADER.setNext(HEADER);
		HEADER.setPrev(HEADER);
	}

	// CS314 students, add methods here:

	/**
	 * add item to the front of the list. <br>
	 * pre: item != null <br>
	 * post: size() = old size() + 1, get(0) = item
	 * 
	 * @param item the data to add to the front of this list
	 */
	// Add node as the first node in the list O(1)
	public void addFirst(E item) {
		if (item == null) {
			throw new IllegalArgumentException("Item can not be null!");
		}
		// create the node and set the previous reference to HEADER and the next
		// reference to
		// the node which previous reference was HEADER
		DoubleListNode<E> newNode = new DoubleListNode<>(HEADER, item, HEADER.getNext());
		// Adjust the previous reference of Node that was the first node to the new
		// node.
		HEADER.getNext().setPrev(newNode);
		// Set HEADER's next reference to the new node
		HEADER.setNext(newNode);
		size++;
	}

	/**
	 * add item to the end of the list. <br>
	 * pre: item != null <br>
	 * post: size() = old size() + 1, get(size() -1) = item
	 * 
	 * @param item the data to add to the end of this list
	 */
	// Add node as the last node in the list O(1)
	public void addLast(E item) {
		if (item == null) {
			throw new IllegalArgumentException("Item can not be null!");
		}
		// create the node and set the previous reference to HEADER's previous reference
		// node and
		// the next reference to HEADER
		DoubleListNode<E> newNode = new DoubleListNode<>(HEADER.getPrev(), item, HEADER);
		// Adjust the next reference of Node that was the last node to the new node.
		HEADER.getPrev().setNext(newNode);
		// Set HEADER's previous reference to the new node
		HEADER.setPrev(newNode);	
		size++;
	}

	/**
	 * remove and return the first element of this list. <br>
	 * pre: size() > 0 <br>
	 * post: size() = old size() - 1
	 * 
	 * @return the old first element of this list
	 */
	// Remove the first node of the list O(1)
	public E removeFirst() {
		if (size <= 0) {
			throw new IllegalArgumentException("size has to be greater than 0!");
		}
		// Get the first node of the list
		DoubleListNode<E> first = HEADER.getNext();
		// Get the data that is stored in the first node
		E result = first.getData();
		// Set HEADER's next reference to the node after the first node
		HEADER.setNext(first.getNext());
		// Set the previous reference of the node after the first node to HEADER
		first.getNext().setPrev(HEADER);
		size--;
		return result;
	}

	/**
	 * remove and return the last element of this list. <br>
	 * pre: size() > 0 <br>
	 * post: size() = old size() - 1
	 * 
	 * @return the old last element of this list
	 */
	// Remove the last node of the list O(1)
	public E removeLast() { 
		if (size <= 0) {
			throw new IllegalArgumentException("size has to be greater than 0!");
		}
		// Get the last node of the list
		DoubleListNode<E> last = HEADER.getPrev();
		// Get the data that is stored in the last node
		E result = last.getData();
		// Set the previous reference of HEADER to the node in front of the last node
		HEADER.setPrev(last.getPrev());
		// Set the next reference of the node in front of the last node to HEADER
		last.getPrev().setNext(HEADER);
		size--;
		return result;
	}

	@Override
	// Add a node to the list O(1)
	public void add(E item) {
		if (item == null) {
			throw new IllegalArgumentException("Item can not be null for add method");
		}
		// Using addLast because add method does the samething as addLast.
		addLast(item);
	}

	@Override
	// Insert the node at a specific position O(N)
	public void insert(int pos, E item) {
		if (pos < 0 || pos > size || item == null) {
			throw new IllegalArgumentException("Invalid parameter for insert method!");
		}
		// If the input position is 0, we just add the element as the first element
		if (pos == 0) {
			addFirst(item);
			// If the input position is the same as the size, we just add it at last
		} else if (pos == size) {
			add(item);
			// If the input position is not first or last, we add it in the middle
		} else {
			// get the node before the input position
			DoubleListNode<E> nodeBeforePos = nodeAtPos(pos - 1);
			// create the new node and set its previous reference to the node before the
			// position
			DoubleListNode<E> newNode = new DoubleListNode<>(nodeBeforePos, item, nodeBeforePos.getNext());
			// set the previous reference of the node that was after the node before to the
			// new node
			nodeBeforePos.getNext().setPrev(newNode);
			// set the next reference of the node before to the new node
			nodeBeforePos.setNext(newNode);
			size++;
		}

	}

	@Override
	// Set the data of the node at a specific position O(N)
	public E set(int pos, E item) {
		if (pos < 0 || pos >= size || item == null) {
			throw new IllegalArgumentException("Invalid parameter for set method!");
		}
		E result = null;
		// Get the node at the input position
		DoubleListNode<E> node = nodeAtPos(pos);
		// If it is not null, set the result to the data that was stored in the node
		if (node.getData() != null) {
			result = node.getData();
		}
		node.setData(item);
		return result;
	}



	@Override
	//Get the element in the node at the specific position O(N)
	public E get(int pos) {
		if (pos < 0 || pos >= size) {
			throw new IllegalArgumentException("Invalid parameter for set method!");
		}
		//Find the node and get its data.
		E result = nodeAtPos(pos).getData();
		return result;
	}

	@Override
	//remove the node from the list and return its stored value O(N)
	public E remove(int pos) {
		if (pos < 0 || pos >= size) {
			throw new IllegalArgumentException("Invalid parameter for set method!");
		}
		//Find the node that we want to remove
		DoubleListNode<E> removeNode = nodeAtPos(pos);
		//Get its stored value
		E result = removeNode.getData();
		//Set this node's previous node's next reference to this node's next node
		removeNode.getPrev().setNext(removeNode.getNext());
		//Set this node's next node's previous reference to this node's previous node
		removeNode.getNext().setPrev(removeNode.getPrev());
		size--;
		return result;
	}

	//A helper method that find the node at the input index O(N)
	private DoubleListNode<E> nodeAtPos(int pos) {
		//If pos is the same as the list size - 1, we return the last node.
		if (pos == size - 1) {
			return HEADER.getPrev();
		}
		//Find the element at the position
		DoubleListNode<E> temp = HEADER.getNext();
		for (int i = 0; i < pos; i++) {
			temp = temp.getNext();
		}
		return temp;
	}

	@Override
	//Check if the element is removed and list is changed, O(N)
	public boolean remove(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException("obj can not be null");
		}
		DoubleListNode<E> node = HEADER.getNext();
		// find if element equals to the passed in obj
		for (int i = 0; i < size; i++) {
			//if we find the same element, we set the boolean to true and remove the 
			//element and its node.
			if (node.getData().equals(obj)) {
				node.getPrev().setNext(node.getNext());
				node.getNext().setPrev(node.getPrev());
				size --;
				return true;
			}
			node = node.getNext();
		}
		return false;
	}

	@Override
	//Get a IList that contain the node from the range O(N)
	public IList<E> getSubList(int start, int stop) {
		if (start < 0 || stop > size || start > stop) {
			throw new IllegalArgumentException("Invalid parameter for getSubList methods");
		}
		//Create a new list to store node that is in the range
		LinkedList<E> result = new LinkedList<E>();
		//if start and top are the same, it mean we add no Element.
		if (start - stop == 0) {
			return result;
		}
		//Adjust the cursor
		Iterator<E> thisIt = iterator();
		for(int j = 0; j < start; j++) {
			thisIt.next();
		}
		// Add the element from the given range into result
		for (int i = start; i < stop; i++) {
			result.add(thisIt.next());
		}
		return result;
	}

	@Override
	//return the size of the list. O(1)
	public int size() {
		return size;
	}

	@Override
	// get the index of the element O(N)
	public int indexOf(E item) {
		if (item == null) {
			throw new IllegalArgumentException("obj can not be null");
		}
		//Since we don't have position, we use indexOf with the position from the first node.
		int first = 0;		
		return indexOf(item, first);
	}

	@Override
	// get the index of the node that store the element begin from the position. O(N).
	public int indexOf(E item, int pos) {
		if (item == null || pos < 0 || pos >= size) {
			throw new IllegalArgumentException("obj can not be null");
		}
		Iterator<E> thisIt = iterator();
		//Adjust position
		for(int j = 0; j < pos; j++) {
			thisIt.next();
		}
		// Find the index of the first node that store item.
		for (int j = pos; j < size; j++) {
			// use get method to get the element at the index
			if (thisIt.next().equals(item)) {
				return j;
			}
		}
		// If no node store item, return -1.
		return -1;
	}

	@Override
	// Clear out everything in the list O(1)
	public void makeEmpty() {
		size = 0;
		HEADER.setNext(HEADER);
		HEADER.setPrev(HEADER);
	}

	@Override
	// Iterator O(1)
	public Iterator<E> iterator() {
		return new DLIterator();
	}

	// DLIterator 
	private class DLIterator implements Iterator<E> {

		private DoubleListNode<E> nodeWithNext;
		private boolean removeOk;

		public DLIterator() {
			//Initialize the nodeWithNext to the first Node.
			nodeWithNext = HEADER.getNext();

		}

		@Override
		//If the cursor does not run back to HEADER, it mean we 
		//do not finish checking everything. 0(1)
		public boolean hasNext() {
			return nodeWithNext != HEADER;
		}

		@Override
		//Move the cursor to the next element O(1)
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No more element !");
			}
			removeOk = true;	
			E result = nodeWithNext.getData();
			nodeWithNext = nodeWithNext.getNext();			
			return result;
		}

		//remove the element that the cursor is on, O(1)
		public void remove() {
			if (!removeOk) {
				throw new IllegalStateException("Illegal Statement");
			}
			// Remove the element
			nodeWithNext.getPrev().getPrev().setNext(nodeWithNext);
			nodeWithNext.setPrev(nodeWithNext.getPrev().getPrev());
			removeOk = false;
			size--;

		}

	}

	@Override
	// Remove nodes from the given range , O(N)
	public void removeRange(int start, int stop) {
		if (start < 0 || start > stop || start > size || stop < 0) {
			throw new IllegalArgumentException("Invalid parameter for removeRange");
		}
		Iterator<E> thisIt = iterator();
		//Adjust position
		for(int j = 0; j < start; j++) {
			thisIt.next();
		}
		// Remove item from the back of the list
		for (int i = start; i < stop; i++) {
			thisIt.next();
			thisIt.remove();
		}
	}

	// Override object toString method. O(N)
	public String toString() {
		// If the size of the list is 0, return a [] with no element.
		if (size == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		// Create a temp to store node and set it to the first element of the list
		DoubleListNode<E> temp = HEADER.getNext();
		// Add the element in the first node into the string builder
		sb.append(temp.getData());
		// get the next node after the first node
		temp = temp.getNext();
		// If temp does not run back to HEADER (thats mean we are not finish checking
		// every node)
		// keep getting the next node and add its element into the string builder
		while (temp != HEADER) {
			sb.append(", ");
			sb.append(temp.getData());
			temp = temp.getNext();
		}
		sb.append("]");
		return sb.toString();
	}

	// overriding the Object equal method O(N).
	public boolean equals(Object other) {
		// Compare if they have the same data type
		if(other != null) {
			if (other instanceof IList) {
				// Create a otherList to reference the casted other
				IList<?> otherList = (IList<?>) other;
				if (size == otherList.size()) {
					// Create iterator for both list
					Iterator<E> thisIt = iterator();
					Iterator<?> otherIt = otherList.iterator();
					// Check if both list has a next element
					while (thisIt.hasNext() && otherIt.hasNext()) {
						// If the element is not the same at the same position, return fasle.
						if (!thisIt.next().equals(otherIt.next())) {
							return false;
						}
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		}else {
			return false;
		}
		return true;
	}

}
