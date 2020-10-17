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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * Experiment:
 * 
 * BST:
 * Unsorted
 *     N             size             height              time
 *   1,000          1000.0             20.8            3.327599999999826E-4
 *   2,000          2000.0             24.2            6.517599999998623E-4
 *   4,000          4000.0             26.6            0.001087590000000239
 *   8,000          8000.0             29.5            0.0019877399999992046
 *   16,000         16000.0            31.3            0.0035264399999984245
 *   32,000         32000.0            35.2            0.006911200000019847
 *   64,000         63999.8            38.6            0.015498270000142231
 *   128,000        127998.1           41.6            0.033612210000581635
 *   256,000        255993.1           44.2            0.08070062999902472
 *   512,000        511970.3           47.2            0.1971261699895485
 *   1,024,000      1023875.8          50.8            0.5101487799688204
 *  
 * Sorted:
 *   1,000          1000.0             999.0           0.0020817699999999993
 *   2,000          2000.0             1999.0          0.007236510000000043
 *   4,000          4000.0             3999.0          0.029554640000000888
 *   8,000          8000.0             7999.0          0.1272178699999971   
 *   16,000         16000.0            stacksoverflow  0.23745053999999022
 *   32,000         32000.0            stacksoverflow  0.9863362099998942
 *   64,000         63999.8            stacksoverflow  3.9660883000003544       
 *   128,000        127998.1           stacksoverflow  15.984959620001229 
 *   256,000        255993.1           stacksoverflow  65.54124781000483
 *   512,000        512000.0           stacksoverflow  518.1739501400389   
 *   1,024,000      1023875.8          stacksoverflow  I still don't get a answer for after about 1 hour
 *   
 * TreeSet:
 * Unsorted:
 *     N             size                 time
 *   1,000          1000.0           3.327599999999826E-4
 *   2,000          2000.0           7.698599999998654E-4
 *   4,000          4000.0           0.001444869999999857
 *   8,000          8000.0           0.0030202400000023974
 *   16,000         16000.0          0.005650130000004001
 *   32,000         32000.0          0.009625270000025003
 *   64,000         63999.2          0.01731572000016296
 *   128,000        127997.8         0.035256670000589616
 *   256,000        255992.7         0.07882555999903311
 *   512,000        511969.9         0.20381246998979297
 *   1,024,000      1023869.9        0.5456643999697497
 * 
 * Sorted:
 *   1,000          1000.0           5.000499999999606E-4
 *   2,000          2000.0           8.479899999999108E-4
 *   4,000          4000.0           0.0015854199999997716
 *   8,000          8000.0           0.002575499999999924
 *   16,000         16000.0          0.00355911000000559
 *   32,000         32000.0          0.006469920000055934
 *   64,000         64000.0          0.008455710000116284
 *   128,000        128000.0         0.0152286200002737
 *   256,000        256000.0         0.028892960000621682
 *   512,000        512000.0         0.06471144999604353
 *   1,024,000      1024000.0        0.1267617999946644
 *   
 *   Base on the time from BST and TreeSet, the time for Binary Search Tree is almost the same as the TreeSet for unsorted data.
 *   The time for TreeSet is much faster when the data is sorted
 *   This is maybe because that the binary tree take more space in memory.
 */
public class BSTTester {

	/**
	 * The main method runs the tests.
	 * @param args Not used
	 */
	public static void main(String[] args) {
		int n = 1024000;
		BinarySearchTree<String> t = new BinarySearchTree<>();
		//test 1 size 
		System.out.println("Test 1: empty tree created.");
		showTestResults(t.size() == 0, 1);

		//test 2 add and size
		boolean expected = t.add("hi");
		System.out.println("Test 2: add method and size");
		showTestResults(expected == true && t.size() == 1, 2);

		//test 3 add and remove
		expected = t.add("hi");
		t.remove("hi");
		System.out.println("Test 3: add method and remove method");
		showTestResults(expected == false && t.size() == 0, 3);

		//test 4. isPresent
		t.add("CS");
		t.add("314");
		t.add("BST");
		System.out.println("Test 4: isPresent method");
		showTestResults(t.isPresent("CS") == true, 4);

		//test 5, isPresent
		System.out.println("Test 5: isPresent method");
		showTestResults(t.isPresent("311") == false, 5);

		//test 6, height
		System.out.println("Test 6: height method");
		showTestResults(t.height() == 2, 6);


		BinarySearchTree<Integer> t1 = new BinarySearchTree<>();

		//test 7. height
		t1.add(1);
		t1.add(2);
		t1.add(3);
		System.out.println("Test 7: height method");
		showTestResults(t1.height() == 2, 6);

		//test 8. getAll
		t1.add(4);
		ArrayList<Integer> list = new  ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		System.out.println("Test 8: getAll method");
		showTestResults(t1.getAll().equals(list), 8);

		//test 9, getMax
		System.out.println("Test 9:Max method");
		showTestResults(t1.max() == 4, 9);

		//test 10. getMin
		System.out.println("Test 7:Min method");
		showTestResults(t1.min() == 1, 10);

		// 11, iterative add and getAll
		expected = t1.iterativeAdd(5);
		list.add(5);
		System.out.println("Test 11: getAll method");
		showTestResults(t1.getAll().equals(list) && expected == true, 11);

		//test 12, getMax
		System.out.println("Test 9:Max method");
		showTestResults(t1.max() == 5, 12);

		//test 13, getMin
		t1.add(-2);
		System.out.println("Test 9:Max method");
		showTestResults(t1.min() == -2, 13);

		//test 14, iterative add and getKth
		t1.iterativeAdd(-1);
		System.out.println("Test 14: Iterative add and getKth method");
		showTestResults(t1.get(2) == 1, 14);

		//test 15, getKth
		System.out.println("Test 15:  getKth method");
		showTestResults(t1.get(0) == -2, 15);

		//test 16, getAllLess
		list.clear();
		list.add(-2);
		list.add(-1);
		list.add(1);
		System.out.println("Test 16: getAllLess method");
		showTestResults(t1.getAllLessThan(2).equals(list), 16);

		//test 17, getAllLess
		list.add(2);
		list.add(3);
		list.add(4);
		System.out.println("Test 17: getAllLess method");
		showTestResults(t1.getAllLessThan(5).equals(list), 17);

		//test 18, getAllGreat
		list.clear();
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		System.out.println("Test 18: getAllLess method");
		showTestResults(t1.getAllGreaterThan(1).equals(list), 18);

		//test 19, getAllGreat
		list.add(0,1);
		System.out.println("Test 19: getAllLess method");
		showTestResults(t1.getAllGreaterThan(0).equals(list), 19);

		//test 20, depth
		System.out.println("Test 20: getAllLess method");
		showTestResults(t1.numNodesAtDepth(2) == 2, 20);

		//test 21, depth
		System.out.println("Test 21: getAllLess method");
		showTestResults(t1.numNodesAtDepth(4) == 1, 21);
		
		
		
		//BSTExp1(n);
		System.out.println();
		//BSTExp2(n);
		System.out.println();
		BSTExp3(n);
		System.out.println();
		BSTExp4(n);
	}

	private static void showTestResults(boolean passed, int testNum) {
		if (passed) {
			System.out.println("Test " + testNum + " passed.");
		} else {
			System.out.println("TEST " + testNum + " FAILED.");
		}
	}


	private static void BSTExp1(int n) {
		double totalHeight = 0.0;
		double totalTime = 0.0;
		double totalSize = 0.0;
		Random r = new Random();
		for(int i = 0; i < 10;i++) {
			BinarySearchTree<Integer> b = new BinarySearchTree();
			for(int j = 0; j < n; j++) {
				Stopwatch s = new Stopwatch();
				s.start();
				b.add( new Integer( r.nextInt() ) ); 
				s.stop();
				totalTime += s.time();
			}
			totalHeight += b.height();
			totalSize += b.size();
		}
		System.out.println("Average Time:"  + totalTime / 10);
		System.out.println("Average Height:"  + totalHeight / 10);
		System.out.println("Average Size:"  + totalSize / 10);
	}
	
	private static void BSTExp2(int n) {
		double totalHeight = 0.0;
		double totalTime = 0.0;
		double totalSize = 0.0;
		Random r = new Random();
		for(int i = 0; i < 10;i++) {
			TreeSet<Integer> t = new TreeSet<>();
			for(int j = 0; j < n; j++) {
				Stopwatch s = new Stopwatch();
				s.start();
				t.add( new Integer( r.nextInt() ) ); 
				s.stop();
				totalTime += s.time();
			}
			totalSize += t.size();
		}
		System.out.println("Average Time:"  + totalTime / 10);
		System.out.println("Average Size:"  + totalSize / 10);
	}
	
	
	private static void BSTExp3(int n) {
		double totalHeight = 0.0;
		double totalTime = 0.0;
		double totalSize = 0.0;
		Random r = new Random();
		for(int i = 0; i < 10;i++) {
			BinarySearchTree<Integer> b = new BinarySearchTree();
			for(int j = 0; j < n; j++) {
				Stopwatch s = new Stopwatch();
				s.start();
				b.iterativeAdd(j); 
				s.stop();
				totalTime += s.time();
			}
			//totalHeight += b.height();
			totalSize += b.size();
		}
		System.out.println("Average Time:"  + totalTime / 10);
	//	System.out.println("Average Height:"  + totalHeight / 10);
		System.out.println("Average Size:"  + totalSize / 10);
	}
	
	private static void BSTExp4(int n) {
		double totalHeight = 0.0;
		double totalTime = 0.0;
		double totalSize = 0.0;
		for(int i = 0; i < 10;i++) {
			TreeSet<Integer> t = new TreeSet<>();
			for(int j = 0; j < n; j++) {
				Stopwatch s = new Stopwatch();
				s.start();
				t.add(j); 
				s.stop();
				totalTime += s.time();
			}
			totalSize += t.size();
		}
		System.out.println("Average Time:"  + totalTime / 10);
		System.out.println("Average Size:"  + totalSize / 10);
	}
	
}