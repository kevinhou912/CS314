
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

public class Decompress {
	// frequency array of vals
	private int[] freq;
	// stores huffman tree of compressed file
	private HuffmanTree tree;
	private IHuffViewer viewer;

	/**
	 * Overall main for uncompressing the compressed file
	 * 
	 * @param outputStream stream linked to target file being written in
	 * @param inputStream  stream linked to compressed file being read in
	 * @param viewer
	 * @return number of bits being written into target file
	 * @throws IOException
	 */
	public int uncompress(BitOutputStream outputStream, BitInputStream inputStream, IHuffViewer viewer)
			throws IOException {
		this.viewer = viewer;
		// Reading in ints
		int curr = inputStream.readBits(IHuffConstants.BITS_PER_INT);
		// make sure magic number present
		if (curr != IHuffConstants.MAGIC_NUMBER) {
			viewer.showError("Error reading compressed file. \n" + "File did not start with the huff magic number.");
			return -1;
		}
		// check for if header format is SCF or STF
		curr = inputStream.readBits(IHuffConstants.BITS_PER_INT);
		// if SCF
		if (curr == IHuffConstants.STORE_COUNTS) {
			buildFreq(inputStream);
			tree = new HuffmanTree(freq);
		}
		// if STF
		else if (curr == IHuffConstants.STORE_TREE) {
			// read in bitsize
			inputStream.readBits(IHuffConstants.BITS_PER_INT);
			// create huffman tree from encoded data
			tree = new HuffmanTree(inputStream);
		}

		// uncompress and write actual data
		return this.writeUncompressedData(outputStream, inputStream);

	}

	/**
	 * Build frequency table from compressed file
	 * 
	 * @param inputStream compressed file being read
	 * @throws IOException
	 */
	private void buildFreq(BitInputStream inputStream) throws IOException {
		freq = new int[IHuffConstants.ALPH_SIZE + 1];
		// read in SCF *code from assignment*
		for (int k = 0; k < IHuffConstants.ALPH_SIZE; k++) {
			int bits = inputStream.readBits(IHuffConstants.BITS_PER_INT);
			freq[k] = bits;
		}
		// set PEOF
		freq[IHuffConstants.ALPH_SIZE] = 1;
	}

	/**
	 * Write compressed data into target file in uncompressed format by using Tree
	 * to decode
	 * 
	 * @param outputStream stream linked to file being written in
	 * @param inputStream  stream linked to compressed file being read in
	 * @return number of bits written in target file
	 * @throws IOException
	 */
	private int writeUncompressedData(BitOutputStream outputStream, BitInputStream inputStream) throws IOException {

		int bitsWritten = 0;
		int currBit = 0;
		TreeNode currNode = tree.getRoot();
		while ((currBit = inputStream.readBits(1)) != -1) {
			// if 0 go left
			if (currBit == 0) {
				currNode = currNode.getLeft();
			}
			// if 1 go right
			else if (currBit == 1) {
				currNode = currNode.getRight();
			}
			if (currNode.isLeaf()) {
				int val = currNode.getValue();
				// if PEOF then stop uncompressing
				if (val == IHuffConstants.PSEUDO_EOF) {
					// we are done close everything
					outputStream.close();
					inputStream.close();
					return bitsWritten;
				}
				// write val in leaf
				outputStream.writeBits(IHuffConstants.BITS_PER_WORD, val);
				// reset node
				currNode = tree.getRoot();
				// add bits
				bitsWritten += IHuffConstants.BITS_PER_WORD;
			}

		}
		// failure bc curr = -1 here
		viewer.showError("Error reading compressed file. \n" + "unexpected end of input. No PSEUDO_EOF value.");
		return -1;
	}

}
