package CS3310.wmich.edu.PVarnerKHamelink;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CharacterCounter {

	HashMap<Character, Integer> charCountHashMap = new HashMap<Character, Integer>();

	Node hTree;

	PriorityQueue<Node> queue;

	void characterCount(String inputString) {
		char[] charArray = inputString.toCharArray();

		for (char c : charArray) {
			if (charCountHashMap.containsKey(c)) {
				charCountHashMap.put(c, charCountHashMap.get(c) + 1);
			} else {
				charCountHashMap.put(c, 1);
			}
		}

		PriorityQueue<Node> q = new PriorityQueue<Node>(charCountHashMap.size(), new MyComparator());

		for (Map.Entry<Character, Integer> e : charCountHashMap.entrySet()) {

			Node hn = new Node();
			hn.character = e.getKey();
			hn.value = e.getValue();

			q.add(hn);
			// System.out.println("The number of Char " + e.getKey() + " is " +
			// e.getValue());
		}
		queue = q;

		/*
		 * while (q.peek() != null) { System.out.println(q.poll().character); }
		 */
	}

	void generateHuffTree() {

		Node root = null;

		PriorityQueue<Node> q2 = queue;

		while (q2.size() > 1) {

			Node x = q2.peek();
			q2.poll();

			Node y = q2.peek();
			q2.poll();

			Node f = new Node();

			f.value = x.value + y.value;
			f.character = '-';

			f.left = x;
			f.right = y;

			root = f;

			q2.add(f);
		}
		hTree = root;
		HuffCode.printCode(root, "");
	}

}
