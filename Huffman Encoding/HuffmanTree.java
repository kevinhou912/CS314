
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

import java.io.IOException;

public class HuffmanTree {
	// parent root of tree
	private TreeNode root;
	// size of tree
	private int size;
	// keeps track of leaf nodes
	private int numLeaf;

	// create HuffmanTree from frequencies of all vals
	public HuffmanTree(int[] freq) {
		PQueue<TreeNode> queue = new PQueue<TreeNode>();
		// read in from array, create TreeNode and feed into PQueue
		for (int val = 0; val < freq.length; val++) {
			int currFreq = freq[val];
			if (currFreq > 0) {
				TreeNode currNode = new TreeNode(val, currFreq);
				queue.add(currNode);
				// keep track of num items in queue as these are how many leaf nodes
				numLeaf++;
			}
		}
		// trace through queue and add nodes to each other and assign left and right
		// nodes, dont add last (EOF)
		while (queue.size() > 1) {
			TreeNode leftNode = queue.remove();
			TreeNode rightNode = queue.remove();
			int freqSum = leftNode.getFrequency() + rightNode.getFrequency();
			queue.add(new TreeNode(leftNode, freqSum, rightNode));
			size += 2;
		}
		// last unaffected node remaining in queue is EOF
		root = queue.remove();
		size++;
	}

	private void getMapHelper(TreeNode n, String s, String[] map) {
		// add to map
		if (n.isLeaf()) {
			map[n.getValue()] = s;
		} else {
			// right append one left append 0
			getMapHelper(n.getRight(), s + "1", map);
			getMapHelper(n.getLeft(), s + "0", map);
		}
	}

	/**
	 * Get an array with the codes for the files
	 * 
	 * @return
	 */
	public String[] getMapArray() {
		String[] result = new String[IHuffConstants.ALPH_SIZE + 1];
		getMapHelper(this.root, "", result);
		return result;
	}

	// kick off for encoding tree into file
	public int encodeTree(BitOutputStream outputStream) {
		int bitsAdded = 0;
		encodeTreeHelper(outputStream, root, bitsAdded);
		return bitsAdded;
	}

	/**
	 * Recursive helper for encoding tree
	 * 
	 * @param outputStream
	 * @param n
	 */
	private void encodeTreeHelper(BitOutputStream outputStream, TreeNode n, int bitsAdded) {
		// perform pre-order traversal to write output
		if (n.isLeaf()) {
			// if leaf add 1
			outputStream.writeBits(1, 1);
			bitsAdded += 1;

			// also want to write 9 digit code for val in node
			outputStream.writeBits(IHuffConstants.BITS_PER_WORD + 1, n.getValue());
			bitsAdded += IHuffConstants.BITS_PER_WORD + 1;

		} else {
			// internal node append 0
			outputStream.writeBits(1, 0);

			bitsAdded += 1;
			// traverse to next node
			encodeTreeHelper(outputStream, n.getLeft(), bitsAdded);
			encodeTreeHelper(outputStream, n.getRight(), bitsAdded);

		}
	}

	/**
	 * Get number of leaf nodes in tree
	 * 
	 * @return int with num leaf nodes
	 */
	public int getNumLeaf() {
		return numLeaf;
	}

	/**
	 * Get overall size of binary tree
	 * 
	 * @return int with size
	 */
	public int size() {
		return size;
	}

	public HuffmanTree(BitInputStream inputStream) throws IOException {
		// reset size
		size = 0;
		root = buildTree(inputStream, root);
	}

	private TreeNode buildTree(BitInputStream inputStream, TreeNode currNode) throws IOException {

		int curr = inputStream.readBits(1);
		// if -1 then we have issues
		if (curr == -1) {
			throw new IllegalArgumentException("something fucked");
		}
		// if 0 internal node
		else if (curr == 0) {
			size++;
			// create new empty node
			currNode = new TreeNode(-1, 0);
			// recurse down left subtree
			currNode.setLeft(buildTree(inputStream, currNode.getLeft()));
			// recurse down right sub tree
			currNode.setRight(buildTree(inputStream, currNode.getRight()));
			return currNode;
		}
		// must be leaf node bc curr is 1
		else {
			size++;
			// read in next nine bits to get val
			// we dont need freq bc freq needed to build tree in first place
			// however here we already have tree built and encoded
			int val = inputStream.readBits(IHuffConstants.BITS_PER_WORD + 1);
			currNode = new TreeNode(val, 0);
			return currNode;
		}

	}

	public int getBitSize() {
		return size + (numLeaf * (IHuffConstants.BITS_PER_WORD + 1));
	}

	public TreeNode getRoot() {
		return root;
	}
}
