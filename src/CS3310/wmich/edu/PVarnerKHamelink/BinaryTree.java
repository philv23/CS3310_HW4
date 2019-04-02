package CS3310.wmich.edu.PVarnerKHamelink;

public class BinaryTree {
	Node root;
	int k;
	public void add(int value)
	{
		root = addRecursively(root, value);
	}
	
	private Node addRecursively(Node current, int value)
	{
		if (current == null)
		{
			return new Node(value);
		}
		
		if (value < current.value) 
		{
			current.left = addRecursively(current.left, value);
		}
		else if (value > current.value) 
		{
			current.right = addRecursively(current.right, value);
		}
		else 
		{
			return current;
		}
		
		return current; 
		
	}
	
	public boolean containsNode(int value)
	{
		return containsNodeRecursive(root, value);
	}
	
	private boolean containsNodeRecursive(Node current, int value)
	{
		if (current == null)
		{
			return false;
		}
		
		if (value == current.value) 
		{
			return true;
		}
		
		return value < current.value
				? containsNodeRecursive(current.left, value)
						: containsNodeRecursive(current.right, value);
	}
	
	private Node deleteRecursive(Node current, int value)
	{
		if (current == null)
		{
			return null;
		}
		
		if (value == current.value) 
		{
			//
		}
		if (value < current.value)
		{
			current.left = deleteRecursive(current.left, value);
			return current;
		}
		
		current.right = deleteRecursive(current.right, value);
		return current;
	}

}








