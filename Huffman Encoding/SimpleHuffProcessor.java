
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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SimpleHuffProcessor implements IHuffProcessor {
	// gets bits per leaf
	private static final int BITS_PER_LEAF = BITS_PER_WORD + 1;
	//viewer
	private IHuffViewer myViewer;
	// array of frequencies
	private int[] freq;
	// huffman encoding tree
	private HuffmanTree tree;
	// array with values and associated codes
	private String[] codingScheme;
	// int to store header format
	private int headerFormat;
	// keep track of bits in original
	private int originalBits;
	// keep track of bits that are compressed
	private int compressedBits;

	/**
	 * Preprocess data so that compression is possible --- count characters/create
	 * tree/store state so that a subsequent call to compress will work. The
	 * InputStream is <em>not</em> a BitInputStream, so wrap it int one as needed.
	 * 
	 * @param in           is the stream which could be subsequently compressed
	 * @param headerFormat a constant from IHuffProcessor that determines what kind
	 *                     of header to use, standard count format, standard tree
	 *                     format, or possibly some format added in the future.
	 * @return number of bits saved by compression or some other measure Note, to
	 *         determine the number of bits saved, the number of bits written
	 *         includes ALL bits that will be written including the magic number,
	 *         the header format number, the header to reproduce the tree, AND the
	 *         actual data.
	 * @throws IOException if an error occurs while reading from the input file.
	 */
	public int preprocessCompress(InputStream in, int headerFormat) throws IOException {
		// reset original and compressed
		compressedBits = 0;
		originalBits = 0;
		// store header format for future use
		this.headerFormat = headerFormat;
		// build freq array
		BitInputStream bitStream = new BitInputStream(new BufferedInputStream(in));
		freq = new int[ALPH_SIZE + 1];
		// curr is current byte we are on
		int curr = bitStream.readBits(BITS_PER_WORD);
		while (curr != -1) {
			originalBits += BITS_PER_WORD;
			freq[curr]++;
			curr = bitStream.read();
		}
		// include pseudo EOF
		freq[ALPH_SIZE] = 1;
		bitStream.close();
		// call preprocess to create huffman tree
		tree = new HuffmanTree(freq);
		// get array of values with their associated codes
		codingScheme = tree.getMapArray();

		// get bits in original
		return this.getDiff();
	}

	/**
	 * Compresses input to output, where the same InputStream has previously been
	 * pre-processed via <code>preprocessCompress</code> storing state used by this
	 * call. <br>
	 * pre: <code>preprocessCompress</code> must be called before this method
	 * 
	 * @param in    is the stream being compressed (NOT a BitInputStream)
	 * @param out   is bound to a file/stream to which bits are written for the
	 *              compressed file (not a BitOutputStream)
	 * @param force if this is true create the output file even if it is larger than
	 *              the input file. If this is false do not create the output file
	 *              if it is larger than the input file.
	 * @return the number of bits written.
	 * @throws IOException if an error occurs while reading from the input file or
	 *                     writing to the output file.
	 */
	public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
		int predictedDiff = originalBits - compressedBits;
		// make sure within bounds
		if (predictedDiff > 0 || force == true) {
			compressedBits = 0;

			BitOutputStream output = new BitOutputStream(new BufferedOutputStream(out));
			// HEADER
			// write magic number
			output.writeBits(BITS_PER_INT, MAGIC_NUMBER);
			compressedBits += BITS_PER_INT;
			// write SCF or STF constant
			output.writeBits(BITS_PER_INT, headerFormat);
			compressedBits += BITS_PER_INT;

			// wrote info that allows huffman tree to be recreated
			if (headerFormat == STORE_COUNTS) {
				for (int k = 0; k < IHuffConstants.ALPH_SIZE; k++) {
					output.writeBits(BITS_PER_INT, freq[k]);
					compressedBits += BITS_PER_INT;
				}
			}
			// write tree using STF
			else if (headerFormat == STORE_TREE) {
				// add size of tree in btis
				int bitSize = tree.getBitSize();
				output.writeBits(BITS_PER_INT, bitSize);
				// keep track of bits being added
				compressedBits += BITS_PER_INT;
				compressedBits += tree.encodeTree(output);
			}
			// read in actual data and PEOF
			BitInputStream input = new BitInputStream(new BufferedInputStream(in));
			int curr = input.read();
			while (curr != -1) {
				String code = codingScheme[curr];
				writeCode(output, code);
				curr = input.read();
			}
			// store EOF
			writeCode(output, codingScheme[ALPH_SIZE]);
			// close all streams
			input.close();
			output.close();
		}
		return compressedBits;
	}

	/**
	 * Uncompress a previously compressed stream in, writing the uncompressed
	 * bits/data to out.
	 * 
	 * @param in  is the previously compressed data (not a BitInputStream)
	 * @param out is the uncompressed file/stream
	 * @return the number of bits written to the uncompressed file/stream
	 * @throws IOException if an error occurs while reading from the input file or
	 *                     writing to the output file.
	 */
	public int uncompress(InputStream in, OutputStream out) throws IOException {
		Decompress decompress = new Decompress();
		// create bitstreams
		BitOutputStream outputStream = new BitOutputStream(new BufferedOutputStream(out));
		BitInputStream inputStream = new BitInputStream(new BufferedInputStream(in));
		// run decompress from Decompress class
		return decompress.uncompress(outputStream, inputStream, myViewer);

	}

	public void setViewer(IHuffViewer viewer) {
		myViewer = viewer;
	}

	private void showString(String s) {
		if (myViewer != null)
			myViewer.update(s);
	}

	/**
	 * Convert String to Bits and write into assigned file
	 * 
	 * @param output output stream linked to target file
	 * @param code   string of bits to be written
	 */
	private void writeCode(BitOutputStream output, String code) {
		// get each individual char and write 0 or 1 depending on value
		for (int i = 0; i < code.length(); i++) {
			char curr = code.charAt(i);
			if (curr == '0') {
				output.writeBits(1, 0);
			} else if (curr == '1') {
				output.writeBits(1, 1);
			}
			// keep track of bits being written
			compressedBits += 1;
		}
	}

	/**
	 * Get predicted difference in bits between original file and compressed file
	 * 
	 * @return difference in bits
	 */
	private int getDiff() {
		// add bits for magic number
		compressedBits += BITS_PER_INT;
		// add bits for header format
		compressedBits += BITS_PER_INT;
		// if STF or SCF determine num bits
		if (headerFormat == STORE_COUNTS) {
			compressedBits += ALPH_SIZE * BITS_PER_INT;
		} else if (headerFormat == STORE_TREE) {
			// add 32 bits for int used to store size of tree
			compressedBits += BITS_PER_INT;
			// add all 1 bit for all nodes
			compressedBits += tree.size();
			// add 9 bits for each leaf for val
			compressedBits += tree.getNumLeaf() * BITS_PER_LEAF;

		}
		// get bits for actual data
		for (int i = 0; i < freq.length; i++) {
			String code = codingScheme[i];
			if (code != null) {
				compressedBits += code.length() * freq[i];
			}
		}

		return originalBits - compressedBits;
	}
}
