package CS3310.wmich.edu.PVarnerKHamelink;

public class HuffCode {

	/**
	 * Prints encodings from given Huffman tree
	 * 
	 * @param Node   root
	 * @param String s
	 * @void
	 * 
	 */

	public static void printCode(Node root, String s) {
		if (root.left == null && root.right == null) {
			System.out.println(root.character + "\t" + s);
			return;
		}
		printCode(root.left, s + "0");
		printCode(root.right, s + "1");
	}

}
