package CS3310.wmich.edu.PVarnerKHamelink;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	/**
	 * MAIN METHOD
	 * 
	 * Takes Input for number of Characters from user, calls method to generate
	 * random File
	 * 
	 * Reads file to string, calls methods from HuffmanTree class using user inputed
	 * key to encode/decode file
	 * 
	 * Prints results
	 * 
	 * @param scan1
	 * @param num
	 * @param key
	 * @param scan2
	 * @param fileName
	 * @param ls
	 * @param cCount
	 * 
	 */

	public static void main(String[] args) throws Exception {

		Scanner scan1 = new Scanner(System.in);

		// Takes user input, create file of chars

		System.out.println("===========\nHuffman Code –\n===========");
		System.out.println("> Enter the number of characters: ");
		int num = scan1.nextInt();
		System.out.println("> Enter the integer key:  ");
		int key = scan1.nextInt();
		System.out.print("> Writing to file... ");
		String fileName = "myfile.txt";
		generateFile(num, fileName);
		System.out.print("[Done]\n");
		File file1 = new File(fileName);

		// Scan file, ads all chars to string

		Scanner scan2 = new Scanner(file1);
		String ls = "";
		while (scan2.hasNext()) {
			ls = ls + scan2.next();
		}

		HuffmanTree cCount = new HuffmanTree();
		// Passes through strings/key, generates huffman tree and files

		cCount.generateHuffTree(ls, fileName, key);

		scan1.close();
		scan2.close();

		// Prints Results and Encodings

		System.out.println("\nResults...\nCharacter Frequencies\n-----------");
		cCount.printChars();

		System.out.println("\nCharacter Encodings\n-----------");
		cCount.printEncodings();
		
		cCount.getTimes();

	}

	/**
	 * 
	 * Creates file of random characters based on user given input size
	 * 
	 * @param alpha
	 * @param s
	 * @param randomNum
	 * @param k
	 * @param fileWriter
	 * @param printWriter
	 * @void
	 */

	public static void generateFile(int n, String name) throws IOException {

		// Generates random int, which is used to pull random char from alpha
		String alpha = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!\\#$%&()*+,-./:;<=>?@[]^_`{|}~";
		String s = "";

		for (int i = 0; i < n; i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, alpha.length());
			char k = alpha.charAt(randomNum);
			s = s + k;
		}
		// Adds random chars to string, then adds strings to file

		FileWriter fileWriter = new FileWriter(name);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		printWriter.print(s);
		printWriter.close();
	}

}