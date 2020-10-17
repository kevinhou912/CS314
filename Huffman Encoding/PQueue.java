
/*  Student information for assignment:
 *
 *  On OUR honor, AVI and KEVIN, this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used: 1
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID: akg2628
 *  email address: aghayalod@utexas.edu
 *  Grader name:
 *
 *  Student 2
 *  UTEID: kh37228
 *  email address: kevin.hou@utexas.edu
 *
 */
import java.util.ArrayList;

public class PQueue<E extends Comparable<? super E>> {
	// container
	private ArrayList<E> con;

	/**
	 * Create a priority queue
	 */
	public PQueue() {
		// initialize con
		con = new ArrayList<E>();
	}

	/**
	 * Add an item to the queue in order of priority
	 * 
	 * @param item data to be added to queue
	 */
	public void add(E item) {
		// if empty j add to end
		if (con.size() == 0) {
			con.add(item);
			return;
		} else {
			int i = 0;
			while (i < con.size()) {
				int diff = con.get(i).compareTo(item);
				// only want to add once we find something greater
				if (diff > 0) {
					con.add(i, item);
					return;
				}
				i++;
			}
			// if we are here than add to end
			con.add(item);
		}
	}

	/**
	 * Display front item in queue
	 * 
	 * @return the item at front in the queue
	 */
	public E peek() {
		// return top of queue
		return con.get(0);
	}

	/**
	 * Remove front item in queue
	 * 
	 * @return value being removed
	 */
	public E remove() {
		// remove and return element at front
		return con.remove(0);
	}

	/**
	 * Returns size of queue
	 * 
	 * @return
	 */
	public int size() {
		return con.size();
	}

	@Override
	public String toString() {
		return con.toString();
	}

}
