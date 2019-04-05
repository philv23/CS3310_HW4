package CS3310.wmich.edu.PVarnerKHamelink;

import java.util.Comparator;

public class MyComparator implements Comparator<Node> {

	@Override
	public int compare(Node a, Node b) {
		return a.value - b.value;
	}

}
