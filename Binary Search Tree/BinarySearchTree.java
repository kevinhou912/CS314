/*  Student information for assignment:
 *
 *  On <MY> honor, Kevin Hou, this programming assignment is <MY> own work
 *  and <I> have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: kh37228
 *  email address: kevin.hou@utexas.edu
 *  Grader name: Andrew Smith
 *
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Shell for a binary search tree class.
 * @author scottm
 * @param <E> The data type of the elements of this BinarySearchTree.
 * Must implement Comparable or inherit from a class that implements
 * Comparable.
 *
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

	private BSTNode<E> root;
	private int size;
	// CS314 students. Add any other instance variables you want here

	// CS314 students. Add a default constructor here if you feel it is necessary.

	/**
	 *  Add the specified item to this Binary Search Tree if it is not already present.
	 *  <br>
	 *  pre: <tt>value</tt> != null<br>
	 *  post: Add value to this tree if not already present. Return true if this tree
	 *  changed as a result of this method call, false otherwise.
	 *  @param value the value to add to the tree
	 *  @return false if an item equivalent to value is already present
	 *  in the tree, return true if value is added to the tree and size() = old size() + 1
	 */
	//Add method to add a value into the binary search tree
	public boolean add(E value) {
		if(value == null) {
			throw new IllegalArgumentException("value for add can not be null");
		}
		int oldSize = size;
		root = addHelp(root, value);
		return oldSize != size;
	}

	//A helper method that add the value into the tree;
	private BSTNode<E> addHelp(BSTNode<E> n, E value){
		//if n is null, that mean there is nothing and we can create 
		//a new node for it
		if(n == null){
			size++;
			return new BSTNode<>(value);
		}else{
			//decide which way to go at the current node
			int dir = value.compareTo(n.data);
			//if value is smaller than the current node value, go to left.
			if(dir < 0){
				n.left = addHelp(n.left, value);
			//else go to right.
			}else if(dir > 0){
				n.right = addHelp(n.right, value);
			}
			//return the node.
			return n;									
		}
	}



	/**
	 *  Remove a specified item from this Binary Search Tree if it is present.
	 *  <br>
	 *  pre: <tt>value</tt> != null<br>
	 *  post: Remove value from the tree if present, return true if this tree
	 *  changed as a result of this method call, false otherwise.
	 *  @param value the value to remove from the tree if present
	 *  @return false if value was not present
	 *  returns true if value was present and size() = old size() - 1
	 */
	//Remove method for the binary search tree
	public boolean remove(E value) {
		int oldSize = size;
		root = removeHelp(root, value);
		return oldSize != size;
	}

	//A helper method that remove nodes from the binary search tree
	private BSTNode<E> removeHelp(BSTNode<E> n , E val){
		// if n is null, there is nothing to be remove, return itself.
		if(n == null){
			return n;
		}else{
			//decide which way to go
			int dir = val.compareTo(n.data);
			//if value is smaller than the current node value, go to left.
			if(dir < 0){
				n.left = removeHelp(n.left,val);
			//else if the value is bigger, go to right
			}else if(dir > 0){
				n.right= removeHelp(n.right,val);
			//else we found it and try to remove it now.
			}else{
				//decrement the size 
				size--;
				// if the node has no child, we can just return null
				if(n.left == null && n.right == null){
					return null;
				// if the node's right child is null, we return the left child.
				}else if(n.right == null){
					return n.left;
				// if the node's left child is null, we return the right child.
				}else if(n.left == null){
					return n.right;
				//else it has two child.
				}else{
					//we want to set the maximum to it current node position
					n.setData(getMax(n.left));
					n.left = removeHelp(n.left, n.data);
					//increment the size;
					size++;
				}
			}
		}
		return n;
	}

	//Helper method to find the max of the node.
	private E getMax(BSTNode<E> n) {
		BSTNode<E> temp = n;
		//while right is not null, keep going to the left
		while(temp.right != null) {
			temp = temp.right;
		}
		return temp.data;
	}

	/**
	 *  Check to see if the specified element is in this Binary Search Tree.
	 *  <br>
	 *  pre: <tt>value</tt> != null<br>
	 *  post: return true if value is present in tree, false otherwise
	 *  @param value the value to look for in the tree
	 *  @return true if value is present in this tree, false otherwise
	 */
	// isPresent to determine if the value is in the tree
	public boolean isPresent(E value) {
		if(value == null) {
			throw new IllegalArgumentException("value can not be null for isPresent");
		}
		return isPresentHelp(root,value);
	}

	// A helper method that helps to determine if the value is in the tree
	private boolean isPresentHelp(BSTNode<E> n, E value) {
		if(n == null) {
			return false;
		}
		// if the value and node value is equal, return true.
		if(n.data.compareTo(value) == 0) {
			return true;
		// if the node value is bigger than the value, we go to the left 
		// of the current node.
		}else if(n.data.compareTo(value) > 0) {
			return isPresentHelp(n.left, value);
		// if the node value is smaller, we go tp the right size of the current node
		}else {
			return isPresentHelp(n.right, value);
		}
	}

	/**
	 *  Return how many elements are in this Binary Search Tree.
	 *  <br>
	 *  pre: none<br>
	 *  post: return the number of items in this tree
	 *  @return the number of items in this Binary Search Tree
	 */
	// return the size of the size
	public int size() {
		return size;
	}



	/**
	 *  return the height of this Binary Search Tree.
	 *  <br>
	 *  pre: none<br>
	 *  post: return the height of this tree.
	 *  If the tree is empty return -1, otherwise return the
	 *  height of the tree
	 *  @return the height of this tree or -1 if the tree is empty
	 */
	//return the height of the tree.
	public int height() {
		return htHelp(root);
	}

	// A helper method that help to find the height of the tree.
	private int htHelp(BSTNode<E> n) {
		//if nothing in the tree, return - 1.
		if(n == null) {
			return -1;
		}else {
			//Use maximum to determine the largest height value.
			return 1 + Math.max(htHelp(n.right), htHelp(n.left));
		}
	}
	/**
	 *  Return a list of all the elements in this Binary Search Tree.
	 *  <br>
	 *  pre: none<br>
	 *  post: return a List object with all data from the tree in ascending order.
	 *  If the tree is empty return an empty List
	 *  @return a List object with all data from the tree in sorted order
	 *  if the tree is empty return an empty List
	 */
	// getAll method to get all the node in the tree
	public List<E> getAll() {
		//create a result list to store the node value
		List<E> result = new ArrayList();
		//if root is null, that mean there is nothing in the tree.
		if(root == null) {
			return result;
		}
		return getAllHelp(root, result);
	}

	// A helper method that helper to find all the element in the tree.
	private List<E> getAllHelp(BSTNode<E> n, List<E> current){
		//update the list.
		List<E> result = current;
		//if the current node is not null, we run the recursive call
		if(n != null) {
			//go to the left of every node
			getAllHelp(n.left, current);
			//add data.
			current.add(n.data);
			//go to the right of every node
			getAllHelp(n.right,current);
		}
		return result;
	}


	/**
	 * return the maximum value in this binary search tree.
	 * <br>
	 * pre: <tt>size()</tt> > 0<br>
	 * post: return the largest value in this Binary Search Tree
	 * @return the maximum value in this tree
	 */
	//return the maximum value of the tree
	public E max() {
		BSTNode<E> temp = root;
		//if the current node right side is not null,
		//that mean there is a bigger value.
		while(temp.right != null) {
			temp = temp.right;
		}
		return temp.data;
	}

	/**
	 * return the minimum value in this binary search tree.
	 * <br>
	 * pre: <tt>size()</tt> > 0<br>
	 * post: return the smallest value in this Binary Search Tree
	 * @return the minimum value in this tree
	 */
	//return the smallest value in the tree.
	public E min() {
		BSTNode<E> temp = root;
		//if the current node left side is not null,
				//that mean there is a smaller value.
		while(temp.left != null) {
			temp = temp.left;
		}
		return temp.data;
	}

	/**
	 * An add method that implements the add algorithm iteratively instead of recursively.
	 * <br>pre: data != null
	 * <br>post: if data is not present add it to the tree, otherwise do nothing.
	 * @param data the item to be added to this tree
	 * @return true if data was not present before this call to add, false otherwise.
	 */
	//iterative add add data with a iterative way instead of recursive way
	public boolean iterativeAdd(E data) {
		if(data == null) {
			throw new IllegalArgumentException("data can not be null for iterativeAdd");
		}
		int oldSize = size;
		//create a boolean to track if the value is added.
		boolean add = false;
		BSTNode<E> n = root;
		//if root is null, there is nothing in the tree,we can add right away
		if(root  == null) {
			root = new BSTNode<E>(data);
			size++;
		}else {
			//while the data has not been added yet
			while(!add) {
				//decide which way to go.
				int dir = data.compareTo(n.data);
				// if the current node value is bigger than the value and 
				//current node left side have nothing, we can add value to its left
				if(dir < 0 && n.left == null) {
					n.setLeft(new BSTNode<E>(data));
					size++;
					add = true;
				// if the current node value is smaller than the value and 
				//current node right side have nothing, we can add value to its right
				}else if(dir > 0 && n.right == null) {
					n.setRight(new BSTNode<E>(data));
					size++;
					add = true;
				//if the node have two children, we go to it next node
				}else if(dir < 0) {
					n = n.left;
				}else if(dir > 0) {
					n = n.right;
				//if we find the data exist already, we don't add it and return false.
				}else {
					return false;
				}
			}
		}
		return oldSize != size;
	}


	/**
	 * Return the "kth" element in this Binary Search Tree. If kth = 0 the
	 * smallest value (minimum) is returned. If kth = 1 the second smallest value is
	 * returned, and so forth.
	 * <br>pre: 0 <= kth < size()
	 * @param kth indicates the rank of the element to get
	 * @return the kth value in this Binary Search Tree
	 */
	//Get the kth value of the tree
	public E get(int kth) {
		if(kth < 0 || kth > size) {
			throw new IllegalArgumentException("kth can not be null for get method");
		}
		// start at negative one to save a space for the increment in the recursive method.
		int first = -1;
		//if kth is 0, return the smallest.
		if(kth == 0) {
			return min();
		}
		//create a array to track count in the helper method
		int[] count = new int[1];	
		count[0] = first;
		return getKthHelp(root, kth, count);
	}

	//A helper method that helper to find the kth value in the tree.
	private E getKthHelp(BSTNode<E> n, int kth, int[] count) {
		E result = null;
		if(n != null) {
			// find value in left side
			result = getKthHelp(n.left, kth, count);
			// if value in left side is null, try to find value in right side
			if(result == null) {
				count[0]++;
				//if we reach kth, we return the value right away
				if(count[0] == kth) {
					return n.data;
			    //else, go to the right side
				}else {
					result = getKthHelp(n.right, kth, count);
				}
			}
		}
		return result;
	}

	/**
	 * Return a List with all values in this Binary Search Tree that are less than
	 * the parameter <tt>value</tt>.
	 * <tt>value</tt> != null<br>
	 * @param value the cutoff value
	 * @return a List with all values in this tree that are less than the parameter value. If there are
	 * no values in this tree less than value return an empty list. The elements of the list are in ascending order.
	 */
	// Return a list of value in the tree that is smaller than the passed in value
	public List<E> getAllLessThan(E value) {
		if(value == null) {
			throw new IllegalArgumentException("data can not be null for getAllLessThan");
		}
		//create a arraylist to store the data
		List<E> result = new ArrayList<>();
		return getAllLessThanHelp(root,result, value);
	}

	//A helper method that help to find the smaller values.
	private List<E> getAllLessThanHelp(BSTNode<E> n, List<E> result, E value) {
		//if n is null, return the result, base case.
		if(n == null) {
			return result;
		}else {
			//go to the left side first.
			getAllLessThanHelp(n.left, result,value);
			//if the value is smaller, than add it into the list.
			if(n.data.compareTo(value) < 0) {
				result.add(n.data);
			}
			//go to the right side
			getAllLessThanHelp(n.right, result,value);
		}
		return result;
	}

	/**
	 * Return a List with all values in this Binary Search Tree that are greater than
	 * the parameter <tt>value</tt>.
	 * <tt>value</tt> != null<br>
	 * @param value the cutoff value
	 * @return a List with all values in this tree that are greater than the parameter value. If there are
	 * no values in this tree greater than value return an empty list. The elements of the list are in ascending order.
	 */
	// Return a list of value in the tree that is greater than the passed in value
	public List<E> getAllGreaterThan(E value) {
		if(value == null) {
			throw new IllegalArgumentException("data can not be null for getAllGreaterThan");
		}
		//create a arraylist to store the data
		List<E> result = new ArrayList<>();
		return getAllGreaterThanHelp(root, result, value);
	}

	//A helper method that help to find the greater values.
	private List<E> getAllGreaterThanHelp(BSTNode<E> n, List<E> result, E value) {
		//if n is null, return the result, base case.
		if(n == null) {
			return result;
		}else {
			//go to the left side first.
			getAllGreaterThanHelp(n.left, result,value);
			//if the value is bigger, then add it into the list
			if(n.data.compareTo(value) > 0) {
				result.add(n.data);
			}
			//go to the right side
			getAllGreaterThanHelp(n.right, result,value);
		}
		return result;
	}

	/**
	 * Find the number of nodes in this tree at the specified depth.
	 * <br>pre: none
	 * @param d The target depth.
	 * @return The number of nodes in this tree at a depth equal to
	 * the parameter d.
	 */
	//return the depth of the tree.
	public int numNodesAtDepth(int d) {
		return depthHelp(root, d);
	}

	//A helper method that helps to determine the depths of a tree
	private int depthHelp(BSTNode<E> n , int d) {
		int count = 0;
		// if n is not null.
		if(n != null) {
			// if d is 0, return 1.(root itself)
			if(d == 0) {
				count = 1;
			}else {
				// we add the number of node until we reach the depth d.
				count += depthHelp(n.left, d - 1);
				count += depthHelp(n.right, d - 1);
			}
		}
		return count;
	}

	/**
	 * Prints a vertical representation of this tree.
	 * The tree has been rotated counter clockwise 90
	 * degrees. The root is on the left. Each node is printed
	 * out on its own row. A node's children will not necessarily
	 * be at the rows directly above and below a row. They will
	 * be indented three spaces from the parent. Nodes indented the
	 * same amount are at the same depth.
	 * <br>pre: none
	 */
	public void printTree() {
		printTree(root, "");
	}

	private void printTree(BSTNode<E> n, String spaces) {
		if(n != null){
			printTree(n.getRight(), spaces + "  ");
			System.out.println(spaces + n.getData());
			printTree(n.getLeft(), spaces + "  ");
		}
	}

	private static class BSTNode<E extends Comparable<? super E>> {
		private E data;
		private BSTNode<E> left;
		private BSTNode<E> right;

		public BSTNode() {
			this(null);
		}

		public BSTNode(E initValue) {
			this(null, initValue, null);
		}

		public BSTNode(BSTNode<E> initLeft,
				E initValue,
				BSTNode<E> initRight) {
			data = initValue;
			left = initLeft;
			right = initRight;
		}

		public E getData() {
			return data;
		}

		public BSTNode<E> getLeft() {
			return left;
		}

		public BSTNode<E> getRight() {
			return right;
		}

		public void setData(E theNewValue) {
			data = theNewValue;
		}

		public void setLeft(BSTNode<E> theNewLeft) {
			left = theNewLeft;
		}

		public void setRight(BSTNode<E> theNewRight) {
			right = theNewRight;
		}
	}
}
