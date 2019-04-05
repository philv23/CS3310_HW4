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

	void generateHuffTree(String text) {

		characterCount(text);

		Node root = null;

		PriorityQueue<Node> q2 = queue;

		while (q2.size() != 1) {
			Node left = q2.poll();
			Node right = q2.poll();

			Node f = new Node();

			f.value = left.value + right.value;
			f.character = '\0';

			f.left = left;
			f.right = right;
			q2.add(f);
		}

		root = q2.peek();

		Map<Character, String> huffmanCode = new HashMap<>();
		encode(root, "", huffmanCode);

		System.out.println("Original String: " + text);

		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < text.length(); j++) {
			sb.append(huffmanCode.get(text.charAt(j)));
		}

		System.out.println("\nEncoded String is :\n" + sb);

		int in = -1;
		System.out.println("Decoded String is: ");

		while (in < sb.length() - 2) {
			in = decode(root, in, sb);
		}
		
		System.out.println("");

		/*
		 * Node root = null;
		 * 
		 * PriorityQueue<Node> q2 = queue;
		 * 
		 * while (q2.size() > 1) {
		 * 
		 * Node x = q2.peek(); q2.poll();
		 * 
		 * Node y = q2.peek(); q2.poll();
		 * 
		 * Node f = new Node();
		 * 
		 * f.value = x.value + y.value; f.character = '-';
		 * 
		 * f.left = x; f.right = y;
		 * 
		 * root = f; q2.add(f); } hTree = root; HuffCode.printCode(root, "");
		 */
	}

	public static void encode(Node root, String str, Map<Character, String> huffmanCode) {
		if (root == null) {
			return;
		}

		huffmanCode.put(root.character, str);

		encode(root.left, str + "0", huffmanCode);
		encode(root.right, str + "1", huffmanCode);
	}

	public static int decode(Node root, int index, StringBuilder sb) {
		if (root == null) {
			return index;
		}

		if (root.left == null && root.right == null) {
			System.out.print(root.character);
			return index;
		}

		index++;

		if (sb.charAt(index) == '0') {
			index = decode(root.left, index, sb);
		} else {
			index = decode(root.right, index, sb);
		}
		return index;

	}

}
