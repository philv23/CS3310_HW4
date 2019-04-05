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

	void generateHuffTree(String text, String fileName, int key) throws IOException {

		key = key % 2;
		
		String binName = fileName.replace(".txt", "_compressed.bin");
		
		System.out.print("Compressing “" + fileName + "” into “" + binName + "”... " );
		
		characterCount(text);

		Node root = null;

		PriorityQueue<Node> q2 = queue;

		while (q2.size() > 1) {
			
			
			Node left = q2.peek();
			q2.poll();
			Node right = q2.peek();
			q2.poll();

			Node f = new Node();

			f.value = left.value + right.value;
			f.character = '-';

			f.left = left;
			f.right = right;
			root = f;
			
			q2.add(f);
		}

		hTree = root;

		Map<Character, String> huffmanCode = new HashMap<>();
		encode(root, "", huffmanCode, key);
		

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

		FileWriter fileWriter = new FileWriter(binName);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		for (int z = 0; z < byteCode.length; z++) {
			printWriter.print(byteCode[z]);
		}
		printWriter.close();
		
		System.out.print("[Done]\n");
		
		String unName = binName.replace("_compressed.bin", "_decompressed.txt");
		
		System.out.print("Decompressing “" + binName + "” into “" + unName + "”... " );

		File file3 = new File(binName);

		Scanner scan1 = new Scanner(file3);
		String read = "";

		while (scan1.hasNext()) {
			read = read + scan1.next();
		}

		scan1.close();

		int in = -1;

		while (in < read.length() - 2) {
			in = decode(root, in, read, key);
		}

		

		FileWriter fileWriter2 = new FileWriter(unName);
		PrintWriter printWriter2 = new PrintWriter(fileWriter2);

		printWriter2.print(decoded);
		printWriter2.close();
		System.out.print("[Done]\n");
		
		
	}

	public static void encode(Node root, String str, Map<Character, String> huffmanCode, int key) {
		if (root == null) {
			return;
		}
		if(key == 0) {
		huffmanCode.put(root.character, str);
		encode(root.left, str + "0", huffmanCode, key);
		encode(root.right, str + "1", huffmanCode, key);
		}
		else {
			huffmanCode.put(root.character, str);
			encode(root.left, str + "1", huffmanCode, key);
			encode(root.right, str + "0", huffmanCode, key);
		}
	}

	public static int decode(Node root, int index, String sb, int key) {
		if (root == null) {
			return index;
		}

		if (root.left == null && root.right == null) {

			decoded = decoded + root.character;
			return index;
		}

		index++;
		
		if(key == 0) {

		if (sb.charAt(index) == '0') {
			index = decode(root.left, index, sb, key);
		} else {
			index = decode(root.right, index, sb, key);
		}
		}
		else {
			if (sb.charAt(index) == '1') {
				index = decode(root.left, index, sb, key);
			} else {
				index = decode(root.right, index, sb, key);
			}
			
		}
		
		return index;

	}
	
	public void printChars() {
		int count = 0;
		for (Map.Entry<Character, Integer> e: charCountHashMap.entrySet()) {	        
	            System.out.println(e.getKey() + "        " + e.getValue());
	            count = count + e.getValue();
	    }
		System.out.println("Total Characters: " + count);
	}
	
	public void printEncodings() {
		HuffCode.printCode(hTree, "");
		
	}

}
