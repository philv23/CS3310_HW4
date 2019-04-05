package CS3310.wmich.edu.PVarnerKHamelink;
import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//String fileContents = new Scanner(new File("/idk")).useDelimiter("\\Z").next();
		
		Scanner scan1 = new Scanner(System.in);
		
		System.out.println("===========\nHuffman Code –\n===========");
		System.out.println("> Enter the number of characters: ");
		int num = scan1.nextInt();
		generateFile(num, "myfile.txt");
		System.out.println("> Enter the integer key: ");


	}
	
	public static void generateFile(int n, String name) throws IOException {
		String alpha = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!\\#$%&()*+,-./:;<=>?@[]^_`{|}~";
		String s = "";
		
		for(int i = 0;i < n; i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, alpha.length() + 1);
			char k = alpha.charAt(randomNum);
			
			s = s + k;
		}
		
		FileWriter fileWriter = new FileWriter(name);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
				
	    printWriter.print(s);
	    printWriter.close();
		
	}

}