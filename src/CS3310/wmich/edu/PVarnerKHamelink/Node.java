package CS3310.wmich.edu.PVarnerKHamelink;

public class Node {
	char character;
	//VALUE == FREQUENCY USED
	int value;
	Node left; 
	Node right;
	
	Node(int value)
	{
		
		this.value = value;
		right = null;
		left = null;
	}
	
}