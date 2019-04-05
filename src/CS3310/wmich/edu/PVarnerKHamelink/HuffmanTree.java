package CS3310.wmich.edu.PVarnerKHamelink;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree {

	HashMap<Character, Integer> charCountHashMap = new HashMap<Character, Integer>();

	static String decoded = "";

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
		}
		queue = q;

	}

	void generateHuffTree(String text, String fileName) throws IOException {

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

		System.out.println("Original String:\n " + text);

		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < text.length(); j++) {
			sb.append(huffmanCode.get(text.charAt(j)));
		}

		byte[] byteCode = new byte[sb.length()];

		for (int z = 0; z < sb.length(); z++) {
			if (sb.charAt(z) == '0') {
				byteCode[z] = 0;
			}
			if (sb.charAt(z) == '1') {
				byteCode[z] = 1;
			}
		}

		FileWriter fileWriter = new FileWriter(fileName);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		for (int z = 0; z < byteCode.length; z++) {
			printWriter.print(byteCode[z]);
		}
		printWriter.close();

		File file3 = new File(fileName);

		Scanner scan1 = new Scanner(file3);
		String read = "";

		while (scan1.hasNext()) {
			read = read + scan1.next();
		}
		System.out.println(read);

		scan1.close();
		System.out.println("\nEncoded String is :\n" + sb);

		int in = -1;
		System.out.println("Decoded String is: ");

		while (in < read.length() - 2) {
			in = decode(root, in, read);
		}

		fileName = fileName.replace("_compressed.bin", "_decompressed.txt");

		FileWriter fileWriter2 = new FileWriter(fileName);
		PrintWriter printWriter2 = new PrintWriter(fileWriter2);

		printWriter2.print(decoded);
		printWriter2.close();
		System.out.println(decoded);
	}

	public static void encode(Node root, String str, Map<Character, String> huffmanCode) {
		if (root == null) {
			return;
		}
		huffmanCode.put(root.character, str);
		encode(root.left, str + "0", huffmanCode);
		encode(root.right, str + "1", huffmanCode);
	}

	public static int decode(Node root, int index, String sb) {
		if (root == null) {
			return index;
		}

		if (root.left == null && root.right == null) {

			decoded = decoded + root.character;
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
