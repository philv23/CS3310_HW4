package CS3310.wmich.edu.PVarnerKHamelink;

import java.util.Comparator;

public class MyComparator implements Comparator<Node> {

	/**
	 * Compare method to be sued during Huffman tree creation
	 * 
	 * @param Node a
	 * @param Node b
	 * @return a.value - b.value
	 * 
	 */

	@Override
	public int compare(Node a, Node b) {
		return a.value - b.value;
	}

}
