package CS3310.wmich.edu.PVarnerKHamelink;

import java.util.PriorityQueue;

public class HuffmanTree {


	HuffmanTree() {

		int n = 6;
		char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f' };
		int[] charfreq = { 5, 9, 12, 13, 16, 45 };

		PriorityQueue<Node> q = new PriorityQueue<Node>(n, new MyComparator());

		for (int i = 0; i < n; i++) {

			Node hn = new Node();

			hn.character = charArray[i];
			hn.value = charfreq[i];

			hn.left = null;
			hn.right = null;
			q.add(hn);
		}

		Node root = null;

		while (q.size() > 1) {

			Node x = q.peek();
			q.poll();

			Node y = q.peek();
			q.poll();

			Node f = new Node();

			f.value = x.value + y.value;
			f.character = '-';

			f.left = x;
			f.right = y;

			root = f;

			q.add(f);
		}

		HuffCode.printCode(root, "");

	}

}
