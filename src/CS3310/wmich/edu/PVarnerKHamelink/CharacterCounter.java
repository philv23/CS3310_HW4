package CS3310.wmich.edu.PVarnerKHamelink;

import java.util.HashMap;

public class CharacterCounter {

	HashMap<Character, Integer> charCountHashMap = new HashMap<Character, Integer>();

	void characterCount(String inputString) {
		char[] charArray = inputString.toCharArray();

		for (char c : charArray) {
			if (charCountHashMap.containsKey(c)) {
				charCountHashMap.put(c, charCountHashMap.get(c) + 1);
			} else {
				charCountHashMap.put(c, 1);
			}
		}

	}

}
