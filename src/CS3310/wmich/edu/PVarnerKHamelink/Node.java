package CS3310.wmich.edu.PVarnerKHamelink;

public class Node {
	char character;
	// VALUE == FREQUENCY USED
	int value;
	Node left;
	Node right;

	/**
	 * Constructor Used to generate nodes, to be used for Huffman tree
	 * 
	 * @param character
	 * @param value
	 * @param left
	 * @param right
	 * 
	 */

	Node(int value) {
		this.value = value;
		right = null;
		left = null;
	}

	/**
	 * Constructor Used to generate nodes, to be used for Huffman tree
	 * 
	 * @param character
	 * @param value
	 * @param left
	 * @param right
	 * 
	 */

	Node() {
		right = null;
		left = null;
	}

}