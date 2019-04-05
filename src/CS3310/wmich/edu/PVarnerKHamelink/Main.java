package CS3310.wmich.edu.PVarnerKHamelink;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

	public static void main(String[] args) throws Exception {

		Scanner scan1 = new Scanner(System.in);
		
		System.out.println("===========\nHuffman Code –\n===========");
		System.out.println("> Enter the number of characters: ");
		int num = scan1.nextInt();
		System.out.println("> Enter the integer key:  ");
		int key = scan1.nextInt();
		System.out.print("> Writing to file... ");
		String fileName = "myfile.txt";
		generateFile(num, fileName );
		System.out.print("[Done]\n");
		File file1 = new File(fileName);
		
		Scanner scan2 = new Scanner(file1);
		String ls = "";
		while(scan2.hasNext()) {
		ls = ls + scan2.next();
		}
		
		
		HuffmanTree cCount = new HuffmanTree();	
		
		cCount.generateHuffTree(ls, fileName, key);
		
		scan1.close();
		scan2.close();
		
		System.out.println("\nResults...\nCharacter Frequencies\n-----------");
		cCount.printChars();
		
		System.out.println("\nCharacter Encodings\n-----------");
		cCount.printEncodings();

	}

	public static void generateFile(int n, String name) throws IOException {
		String alpha = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!\\#$%&()*+,-./:;<=>?@[]^_`{|}~";
		String s = "";

		for (int i = 0; i < n; i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, alpha.length());
			char k = alpha.charAt(randomNum);
			s = s + k;
		}

		FileWriter fileWriter = new FileWriter(name);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		printWriter.print(s);
		printWriter.close();
	}

}