/*  Student information for assignment:
 *
 *  On my honor, Kevin Hou, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name:Kevin Hou
 *  email address: kevinhou@utexas.edu
 *  UTEID:kh37228
 *  Grader name: Andrew Smith
 *  Number of slip days used on this assignment: 0
 */

//Experiment results. CS314 students, place your experiment
//results here:
//When adding at the end, the time is about the same
//When adding at the front, LinkedList is much faster
//When removing from the front, LinkedList is much faster
//When getting random, the ArrayList is much faster
//When getting all using iterator, LinkedList is about twice faster than ArrayList
//When getting all using method, ArrayList is much faster than LinkedList


import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class LinkedListTester {

	public static void main(String[] args) {
		//CS314 students. Add your tests here:

		LinkedList<String> list = new LinkedList<>();
		Object[] actual;
		Object[] expected;

		//Test 1 (AddFirst) 
		System.out.println("\nTest 1: add first");
		list = new LinkedList<>();
		list.addFirst("A");
		actual = toArray(list);
		expected = new Object[]{"A"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 1");
		else
			System.out.println("Failed test 1");

		//Test 2 (AddFirst)
		System.out.println("\nTest 2: add first");
		list.addFirst("B");	
		actual = toArray(list);
		expected = new Object[]{"B","A"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 2");
		else
			System.out.println("Failed test 2");

		//Test 3 (AddFirst)
		System.out.println("\nTest 3: add first");
		list = new LinkedList<>();
		list.add("AAA");
		list.addFirst("BBB");
		if( list.get(0).equals("BBB") )
			System.out.println("Passed test 3");
		else
			System.out.println("Failed test 3");  

		//Test 4 (AddLast)
		System.out.println("\nTest 4: add last");
		list = new LinkedList<>();
		list.addLast("A");
		actual = toArray(list);
		expected = new Object[]{"A"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 4");
		else
			System.out.println("Failed test 4");

		//Test 5 (AddLast)
		System.out.println("\nTest 5: add last");
		list.addLast("B");
		actual = toArray(list);
		expected = new Object[] {"A", "B"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 5");
		else
			System.out.println("Failed test 5");

		//Test 6 (AddLast)
		System.out.println("\nTest 6: add last");
		list = new LinkedList<>();
		list.addLast("B");
		list.addLast("C");
		list.addLast("A");
		if( list.get(2).equals("A") )
			System.out.println("Passed test 6");
		else
			System.out.println("Failed test 6");  

		//Test 7 (removeFirst)
		System.out.println("\nTest 7: remove first");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		String removed = list.removeFirst();
		if( removed.equals("A") )
			System.out.println("Passed test 7");
		else
			System.out.println("Failed test 7");

		//Test 8 (removeFirst)
		System.out.println("\nTest 8: remove first");
		list.addLast("A");
		list = new LinkedList<>();
		list.addLast("A");
		removed =  list.removeFirst();
		if( removed.equals("A") )
			System.out.println("Passed test 8");
		else
			System.out.println("Failed test 8");


		//Test 9 (removeFirst)
		System.out.println("\nTest 9: remove first");
		list = new LinkedList<>();
		list.addLast("B");
		list.addLast("C");
		list.addLast("A");
		removed =  list.removeFirst();
		if( removed.equals("B") )
			System.out.println("Passed test 9");
		else
			System.out.println("Failed test 9");

		//Test 10 (removeLast)
		System.out.println("\nTest 10: remove last");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.removeLast();
		actual = toArray(list);
		expected = new Object[]{"A","B"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 10");
		else
			System.out.println("Failed test 10");

		//Test 11 (removeLast)
		System.out.println("\nTest 11: remove last");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		removed=  list.removeLast();
		if( removed.equals("C") )
			System.out.println("Passed test 11");
		else
			System.out.println("Failed test 11");

		//Test 12 (removeLast)
		System.out.println("\nTest 12: remove last");
		list = new LinkedList<>();
		list.addFirst("AAA");
		removed=  list.removeLast();
		if( removed.equals("AAA") )
			System.out.println("Passed test 12");
		else
			System.out.println("Failed test 12");

		//Test 13 (add)
		System.out.println("\nTest 13: add");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		if( list.toString().equals("[A, B, C]") )
			System.out.println("Passed test 13");
		else
			System.out.println("Failed test 13");

		//Test 14 (add)
		System.out.println("\nTest 14: add");
		list = new LinkedList<>();
		list.add("A");
		list.add("A");
		list.add("A");
		if( list.toString().equals("[A, A, A]") )
			System.out.println("Passed test 14");
		else
			System.out.println("Failed test 14");

		//Test 15 (add)
		System.out.println("\nTest 15: add");
		list = new LinkedList<>();
		list.add("A");
		if( list.toString().equals("[A]") )
			System.out.println("Passed test 15");
		else
			System.out.println("Failed test 15");

		//Test 16 (insert with position and item)
		System.out.println("\nTest 16: insert with position and item");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.insert(1, "C");
		actual = toArray(list);
		expected = new Object[]{"A","C","B","C"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 16");
		else
			System.out.println("Failed test 16");

		//Test 17 (insert with position and item)
		System.out.println("\nTest 17: insert with position and item");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("D");
		list.insert(2,"C");
		if( list.get(2).equals("C") )
			System.out.println("Passed test 17");
		else
			System.out.println("Failed test 17");

		//Test 18 (insert with position and item)
		System.out.println("\nTest 18: insert with position and item");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.insert(2,"C");
		if( list.get(2).equals("C") )
			System.out.println("Passed test 18");
		else
			System.out.println("Failed test 18");

		//Test 19 (set)
		System.out.println("\nTest 19: set");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.set(1, "C");
		actual = toArray(list);
		expected = new Object[]{"A","C","C"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 19");
		else
			System.out.println("Failed test 19");

		//Test 20 (set)
		System.out.println("\nTest 20:set");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.set(1,"C");
		if( list.get(1).equals("C") )
			System.out.println("Passed test 20");
		else
			System.out.println("Failed test 20");

		//Test 21 (set)
		System.out.println("\nTest 21:set");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.set(2,"C");
		if( list.get(2).equals("C") )
			System.out.println("Passed test 21");
		else
			System.out.println("Failed test 21");

		//Test 22 (get)
		System.out.println("\nTest 22:get");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		String getElement = list.get(0);
		if( getElement.equals("A") )
			System.out.println("Passed test 22");
		else
			System.out.println("Failed test 22");

		//Test 23 (get)
		System.out.println("\nTest 23:get");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		getElement = list.get(1);
		if( getElement.equals("B") )
			System.out.println("Passed test 23");
		else
			System.out.println("Failed test 23");

		//Test 24 (get)
		System.out.println("\nTest 24:get");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		getElement = list.get(0);
		if( getElement.equals("A") )
			System.out.println("Passed test 24");
		else
			System.out.println("Failed test 24");

		//Test 25 (remove)
		System.out.println("\nTest 25:remove position 0");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		removed = list.remove(0);
		if( getElement.equals("A") )
			System.out.println("Passed test 25");
		else
			System.out.println("Failed test 25");

		//Test 26 (remove)
		System.out.println("\nTest 26: remove");
		list.remove(1);
		actual = toArray(list);
		expected = new Object[]{"B"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 26");
		else
			System.out.println("Failed test 26");

		//Test 27 (remove)
		System.out.println("\nTest 27: remove");
		list.remove(0);
		actual = toArray(list);
		expected = new Object[]{};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 27");
		else
			System.out.println("Failed test 27");

		//Test 28 (remove boolean)
		System.out.println("\nTest 28: remove with boolean");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		boolean removeElement = list.remove("A");
		actual = toArray(list);
		expected = new Object[]{"B", "C"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) && removeElement == true )
			System.out.println("Passed test 28");
		else
			System.out.println("Failed test 28");	

		//Test 29 (remove boolean)
		System.out.println("\nTest 29: remove with boolean");
		removeElement = list.remove("C");
		actual = toArray(list);
		expected = new Object[]{"B"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) && removeElement == true )
			System.out.println("Passed test 28");
		else
			System.out.println("Failed test 28");	

		//Test 30 (remove boolean)
		System.out.println("\nTest 30: remove with boolean");
		removeElement = list.remove("B");
		actual = toArray(list);
		expected = new Object[]{};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) && removeElement == true )
			System.out.println("Passed test 30");
		else
			System.out.println("Failed test 30");	

		//Test 31 (getSubList)
		System.out.println("\nTest 31: get sublist");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		LinkedList<String> result = (LinkedList<String>) list.getSubList(0, 2);
		actual = toArray(result);
		expected = new Object[]{"A", "B"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 31");
		else
			System.out.println("Failed test 31");	

		//Test 32 (getSubList)
		System.out.println("\nTest 32: get sublist");
		result = (LinkedList<String>) list.getSubList(1, 4);
		actual = toArray(result);
		expected = new Object[]{"B","C","D"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 32");
		else
			System.out.println("Failed test 32");	

		//Test 33 (getSubList)
		System.out.println("\nTest 33: get sublist");
		result = (LinkedList<String>) list.getSubList(1, 1);
		actual = toArray(result);
		expected = new Object[]{};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 33");
		else
			System.out.println("Failed test 33");	

		//Test 34 (size)
		System.out.println("\nTest 34: get size");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		int listSize = list.size();
		if( listSize == 5 )
			System.out.println("Passed test 34");
		else
			System.out.println("Failed test 34");

		//Test 35 (size)
		System.out.println("\nTest 35: get size");
		list = new LinkedList<>();
		listSize = list.size();
		if( listSize == 0 )
			System.out.println("Passed test 35");
		else
			System.out.println("Failed test 35");


		//Test 36 (size)
		System.out.println("\nTest 36: get size");
		list = new LinkedList<>();
		list.add("A");
		listSize = list.size();
		if( listSize == 1 )
			System.out.println("Passed test 36");
		else
			System.out.println("Failed test 36");

		//Test 37 (IndexOf)
		System.out.println("\nTest 37: get index of without position");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		int index = list.indexOf("B");
		if( index == 1 )
			System.out.println("Passed test 37");
		else
			System.out.println("Failed test 37");

		//Test 38 (IndexOf)
		System.out.println("\nTest 38: get index of without position");
		index = list.indexOf("A");
		if( index == 0 )
			System.out.println("Passed test 38");
		else
			System.out.println("Failed test 38");

		//Test 39 (IndexOf)
		System.out.println("\nTest 39: get index of without position");
		index = list.indexOf("E");
		if( index == 4 )
			System.out.println("Passed test 39");
		else
			System.out.println("Failed test 39");

		//Test 40 (IndexOf with postion)
		System.out.println("\nTest 40: get index of position");
		index = list.indexOf("E" , 2);
		if( index == 4 )
			System.out.println("Passed test 40");
		else
			System.out.println("Failed test 40");

		//Test 41 (IndexOf with postion)
		System.out.println("\nTest 41: get index of position");
		index = list.indexOf("A" , 2 );
		if( index == -1 )
			System.out.println("Passed test 41");
		else
			System.out.println("Failed test 41");

		//Test 42 (IndexOf with postion)
		System.out.println("\nTest 42: get index of position");
		index = list.indexOf("B" , 1);
		if( index == 1 )
			System.out.println("Passed test 42");
		else
			System.out.println("Failed test 42");

		//Test 43 (makeEmpty)
		System.out.println("\nTest 43: Empty the list");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.makeEmpty();
		actual = toArray(list);
		expected = new Object[]{};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 43");
		else
			System.out.println("Failed test 43");	

		//Test 44 (makeEmpty)
		System.out.println("\nTest 44: Empty the list");
		list = new LinkedList<>();
		list.add("A");
		list.makeEmpty();
		actual = toArray(list);
		expected = new Object[]{};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 44");
		else
			System.out.println("Failed test 44");	

		//Test 45 (makeEmpty)
		System.out.println("\nTest 45: Empty the list");
		list = new LinkedList<>();
		list.makeEmpty();
		actual = toArray(list);
		expected = new Object[]{};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 45");
		else
			System.out.println("Failed test 45");	

		//Test 46 (Iterator hasNext)
		System.out.println("\nTest 46: Iterator hasNext");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		Iterator<?> listIt = list.iterator();
		boolean check = listIt.hasNext();
		if( check == true )
			System.out.println("Passed test 46");
		else
			System.out.println("Failed test 46");	

		//Test 47 (Iterator hasNext)
		System.out.println("\nTest 47: Iterator hasNext");
		listIt.next();
		check = listIt.hasNext();
		if( check == true )
			System.out.println("Passed test 47");
		else
			System.out.println("Failed test 47");	


		//Test 48 (Iterator hasNext)
		System.out.println("\nTest 48: Iterator hasNext");
		listIt.next();
		listIt.next();
		listIt.next();
		listIt.next();
		check = listIt.hasNext();
		if( check == false )
			System.out.println("Passed test 48");
		else
			System.out.println("Failed test 48");	

		//Test 49 (Iterator next)
		System.out.println("\nTest 49: Iterator next");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		listIt = list.iterator();
		String element = (String) listIt.next();
		if( element.equals("A")  )
			System.out.println("Passed test 49");
		else
			System.out.println("Failed test 49");	

		//Test 50 (Iterator next)
		System.out.println("\nTest 50: Iterator next");
		element = (String) listIt.next();
		if( element.equals("B")  )
			System.out.println("Passed test 50");
		else
			System.out.println("Failed test 50");	

		//Test 51 (Iterator next)
		System.out.println("\nTest 51: Iterator next");
		element = (String) listIt.next();
		if( element.equals("C")  )
			System.out.println("Passed test 51");
		else
			System.out.println("Failed test 51");
		
		//Test 52 (Iterator remove)
		System.out.println("\nTest 52: Iterator remove");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		listIt = list.iterator();
    	listIt.next();
		listIt.remove();
		actual = toArray(list);
		expected = new Object[]{"B","C","D","E"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 52");
		else
			System.out.println("Failed test 52");	
		
		//Test 53 (Iterator remove)
		System.out.println("\nTest 53: Iterator remove");
		listIt.next();
		listIt.remove();
		actual = toArray(list);
		expected = new Object[]{"C","D","E"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 53");
		else
			System.out.println("Failed test 53");	
		
		//Test 54 (Iterator remove)
		System.out.println("\nTest 54: Iterator remove");
		listIt.next();
		listIt.remove();
		listIt.next();
		listIt.remove();
		listIt.next();
		listIt.remove();
		actual = toArray(list);
		expected = new Object[]{};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 54");
		else
			System.out.println("Failed test 54");	
		
		//Test 55 (removeRange)
		System.out.println("\nTest 55: Remove element in the range");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.removeRange(1,3);
		actual = toArray(list);
		expected = new Object[]{"A","D","E"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 55");
		else
			System.out.println("Failed test 55");	

		//Test 56(removeRange)
		System.out.println("\nTest 56: Remove element in the range");
		list.removeRange(0,1);
		actual = toArray(list);
		expected = new Object[]{"D","E"};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 56");
		else
			System.out.println("Failed test 56");	
		
		//Test 57(removeRange)
		System.out.println("\nTest 57: Remove element in the range");
		list.removeRange(0,2);
		actual = toArray(list);
		expected = new Object[]{};
		System.out.println( "Expected result: " + Arrays.toString(expected) );
		System.out.println( "Actual result: " + Arrays.toString(actual) );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 57");
		else
			System.out.println("Failed test 57");	
		
		//Test 58(toString)
		System.out.println("\nTest 58: toString method");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		String listResult = list.toString();
		String expectedResult = "[A, B, C, D, E]";
		System.out.println( "Expected result: " + expectedResult);
		System.out.println( "Actual result: " + listResult );      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 58");
		else
			System.out.println("Failed test 58");	
		
		//Test 59(toString)
		System.out.println("\nTest 59: toString method");
		list = new LinkedList<>();
		listResult = list.toString();
		expectedResult = "[]";
		System.out.println( "Expected result: " + listResult);
		System.out.println( "Actual result: " + listResult);      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 59");
		else
			System.out.println("Failed test 59");	
		
		//Test 60(toString)
		System.out.println("\nTest 60: toString method");
		list = new LinkedList<>();
		list.add("AAA");
		list.add("AA");
		list.add("A");
		listResult = list.toString();
		expectedResult = "[AAA, AA , A]";
		System.out.println( "Expected result: " + listResult);
		System.out.println( "Actual result: " + listResult);      
		if( arraysSame(actual, expected) )
			System.out.println("Passed test 60");
		else
			System.out.println("Failed test 60");	
		
		//Test 61(equals)
		System.out.println("\nTest 61: equal method");
		LinkedList<String> otherList = new LinkedList<>();
		list = new LinkedList<>();
		list.add("A");
		otherList.add("A");
		if( list.equals(otherList) == true )
			System.out.println("Passed test 61");
		else
			System.out.println("Failed test 61");
		
		
		//Test 62(equals)
		System.out.println("\nTest 62: equal method");
		list.add("A");
		otherList.add("b");
		if( list.equals(otherList) == false )
			System.out.println("Passed test 62");
		else
			System.out.println("Failed test 62");
		//Test 63(equals)
		System.out.println("\nTest 63: equal method");
		LinkedList<Integer> secondOtherList = new LinkedList<>();
		list = new LinkedList<>();
		list.add("A");
		secondOtherList.add(1);
		if( list.equals(otherList) == false )
			System.out.println("Passed test 63");
		else
			System.out.println("Failed test 63");
	
		// CS314 Students:
		// uncomment the following line to run tests comparing
		// your LinkedList class to the java ArrayList class.
		comparison();
	}


	
	private static Object[] toArray(LinkedList<String> list) {
		Object[] result = new Object[list.size()];
		Iterator<String> it = list.iterator();
		int index = 0;
		while( it.hasNext() && index < list.size()){
			result[index] = it.next();
			index++;
		}
		return result;
	}

	//pre: none
	private static boolean arraysSame(Object[] one, Object[] two)  {
		boolean same;
		if( one == null || two == null ) {
			same = (one == two);
		}
		else {
			//neither one or two are null
			assert one != null && two != null;
			same = one.length == two.length;
			if( same ) {
				int index = 0;
				while( index < one.length && same ) {
					same = ( one[index].equals(two[index]) );
					index++;
				}
			}
		}
		return same;
	}


	public static final int NUM_DOUBLINGS_OF_N = 5;
	private static final int NUM_REPEATS_OF_TEST = 100;

	// A method to be run to compare the LinkedList you are completing and the Java ArrayList class
	public static void comparison() {
		Stopwatch s = new Stopwatch();

		int initialN = 30000;
		addEndArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
		addEndLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

		initialN = 2000;
		addFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
		initialN = 10000;
		addFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

		initialN = 2000;
		removeFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
		initialN = 5000;
		removeFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

		initialN = 10000;
		getRandomArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
		initialN = 1000;
		getRandomLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

		initialN = 50000;
		getAllArrayListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);
		getAllLinkedListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);

		initialN = 100000;
		getAllArrayListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);
		initialN = 1000;
		getAllLinkedListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);

	}

	// These pairs of method illustarte a failure to use polymorphism
	// If the students had implemented the Java list interface there
	// could be a single method. Also we could use function objects to
	// reduce the awful repitition of code.
	public static void addEndArrayList(Stopwatch s, int initialN, int numTests) {

		double[] totalTimes = new double[numTests];        
		for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
			int n = initialN;
			for (int i = 0; i < numTests; i++) {
				ArrayList<Integer> javaList = new ArrayList<>();
				s.start();
				for (int j = 0; j < n; j++) {
					javaList.add(j);
				}
				s.stop();
				totalTimes[i] += s.time();
				n *= 2;
			}
		}
		showResults("Adding at end: ArrayList", totalTimes, initialN);
	}

	private static void showResults(String title, double[] times, int initialN) {
		System.out.println();
		System.out.println("Num Repeats: " + NUM_REPEATS_OF_TEST);
		System.out.println(title);
		for (double time : times) {
			System.out.print("N = " + initialN + ", total time: ");
			System.out.printf("%7.4f\n", time);
			initialN *= 2;
		}
		System.out.println();
	}

	public static void addEndLinkedList(Stopwatch s, int initialN, int numTests){
		double[] totalTimes = new double[numTests];        
		for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
			int n = initialN;
			for (int i = 0; i < numTests; i++) {
				LinkedList<Integer> studentList = new LinkedList<>();
				s.start();
				for (int j = 0; j < n; j++) {
					studentList.add(j);
				}
				s.stop();
				totalTimes[i] += s.time();
				n *= 2;
			}
		}
		showResults("Adding at end: LinkedList", totalTimes, initialN);
	}

	public static void addFrontArrayList(Stopwatch s, int initialN, int numTests){

		double[] totalTimes = new double[numTests];        
		for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
			int n = initialN;
			for (int i = 0; i < numTests; i++) {
				ArrayList<Integer> javaList = new ArrayList<>();
				s.start();
				for (int j = 0; j < n; j++) {
					javaList.add(0, j);
				}
				s.stop();
				totalTimes[i] += s.time();
				n *= 2;
			}
		}
		showResults("Adding at front: ArrayList", totalTimes, initialN);
	}

	public static void addFrontLinkedList(Stopwatch s, int initialN, int numTests){
		double[] totalTimes = new double[numTests];        
		for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
			int n = initialN;
			for (int i = 0; i < numTests; i++) {
				LinkedList<Integer> studentList = new LinkedList<>();
				s.start();
				for (int j = 0; j < n; j++) {
					studentList.insert(0, j);
				}
				s.stop();
				totalTimes[i] += s.time();
				n *= 2;
			}
		}
		showResults("Adding at front: LinkedList", totalTimes, initialN);
	}

	public static void removeFrontArrayList(Stopwatch s, int initialN, int numTests){     
		double[] totalTimes = new double[numTests];        
		for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
			int n = initialN;
			for (int i = 0; i < numTests; i++) {
				ArrayList<String> javaList = new ArrayList<>();
				for (int j = 0; j < n; j++) {
					javaList.add(j + "");
				}
				s.start();
				while (!javaList.isEmpty()) {
					javaList.remove(0);
				}
				s.stop();
				totalTimes[i] += s.time();
				n *= 2;
			}
		}
		showResults("Removing from front: ArrayList", totalTimes, initialN);
	}

	public static void removeFrontLinkedList(Stopwatch s, int initialN, int numTests){
		double[] totalTimes = new double[numTests];        
		for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
			int n = initialN;    
			for (int i = 0; i < numTests; i++) {
				LinkedList<String> studentList = new LinkedList<>();
				for (int j = 0; j < n; j++) {
					studentList.add(j + "");
				}
				s.start();
				while (studentList.size() != 0) {
					studentList.removeFirst();
				}
				s.stop();
				totalTimes[i] += s.time();
				n *= 2;
			}
		}
		showResults("removing from front: LinkedList", totalTimes, initialN);
	}

	public static void getRandomArrayList(Stopwatch s, int initialN, int numTests){
		double[] totalTimes = new double[numTests];        
		for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
			int n = initialN;
			int total = 0;
			Random r = new Random();
			for (int i = 0; i < numTests; i++) {
				ArrayList<Integer> javaList = new ArrayList<>();
				for (int j = 0; j < n; j++) {
					javaList.add(j);
				}
				s.start();
				for (int j = 0; j < n; j++) {
					total += javaList.get(r.nextInt(javaList.size()));
				}
				s.stop();
				totalTimes[i] += s.time();
				n *= 2;
			}
		}
		showResults("Getting random: ArrayList", totalTimes, initialN);
	}

	public static void getRandomLinkedList(Stopwatch s, int initialN, int numTests){
		double[] totalTimes = new double[numTests];        
		for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
			int n = initialN;
			int total = 0;
			Random r = new Random();
			for (int i = 0; i < numTests; i++) {
				LinkedList<Integer> studentList = new LinkedList< >();
				for (int j = 0; j < n; j++) {
					studentList.add(j);
				}
				s.start();
				for (int j = 0; j < n; j++) {
					total += studentList.get( r.nextInt(studentList.size()) );
				}
				s.stop();
				totalTimes[i] += s.time();
				n *= 2;
			}
		}
		showResults("Getting random: LinkedList", totalTimes, initialN);
	}

	public static void getAllArrayListUsingIterator(Stopwatch s, int initialN, int numTests){

		double[] totalTimes = new double[numTests];        
		for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
			int n = initialN;
			int total = 0;
			for (int i = 0; i < numTests; i++) {
				ArrayList<Integer> javaList = new ArrayList<>();
				for (int j = 0; j < n; j++) {
					javaList.add(j);
				}
				Iterator<Integer> it = javaList.iterator();
				s.start();
				while (it.hasNext()) {
					total += it.next();
				}        
				s.stop();
				totalTimes[i] += s.time();
				n *= 2;
			}
		}
		showResults("Getting all using iterator: ArrayList", totalTimes, initialN);
	}

	public static void getAllLinkedListUsingIterator(Stopwatch s, int initialN, int numTests){
		double[] totalTimes = new double[numTests];        
		for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
			int n = initialN;
			int total = 0;
			for (int i = 0; i < numTests; i++) {
				LinkedList<Integer> studentList = new LinkedList<>();
				for (int j = 0; j < n; j++) {
					studentList.add(j);
				}
				Iterator<Integer> it = studentList.iterator();
				s.start();
				//I added a it.next() != null
				while (it.hasNext() && it.next() != null) {
					total += it.next();
				}
				s.stop();
				totalTimes[i] += s.time();
				n *= 2;
			}
		}
		showResults("Getting all using iterator: LinkedList", totalTimes, initialN);
	}

	public static void getAllArrayListUsingGetMethod(Stopwatch s, int initialN, int numTests){
		double[] totalTimes = new double[numTests];        
		for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
			int n = initialN;
			for (int i = 0; i < numTests; i++) {
				ArrayList<Integer> javaList = new ArrayList<>();
				for (int j = 0; j < n; j++) {
					javaList.add(j);
				}
				s.start();
				int x = 0;
				for (int j = 0; j < javaList.size(); j++) {
					x += javaList.get(j);
				}
				s.stop();
				totalTimes[i] += s.time();
				n *= 2;
			}
		}
		showResults("Getting all using get method: ArrayList", totalTimes, initialN);
	}

	public static void getAllLinkedListUsingGetMethod(Stopwatch s, int initialN, int numTests){
		double[] totalTimes = new double[numTests];        
		for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
			int n = initialN;
			for (int i = 0; i < numTests; i++) {
				LinkedList<Integer> studentList = new LinkedList<>();
				for (int j = 0; j < n; j++) {
					studentList.add(j);
				}
				s.start();
				int x = 0;
				for (int j = 0; j < studentList.size(); j++) {
					x += studentList.get(j);
				}
				s.stop();
				totalTimes[i] += s.time();
				n *= 2;
			}
		}
		showResults("Getting all using get method: LinkedList", totalTimes, initialN);
	}

	// for future changes
	private static interface ArrayListOp {
		public <E> void op(ArrayList<E> list, E data);
	}

	private ArrayListOp addAL = new ArrayListOp() {
		public <E> void op(ArrayList<E> list, E data) {
			list.add(data);
		}
	};

	private ArrayListOp addFrontAL = new ArrayListOp() {
		public <E> void op(ArrayList<E> list, E data) {
			list.add(0, data);
		}
	};

	private ArrayListOp removeFrontAL = new ArrayListOp() {
		public <E> void op(ArrayList<E> list, E data) {
			list.remove(0);
		}
	};

	private static interface LinkedListOp<E> {
		public void op(LinkedList<E> list);
	}
	
	
}
