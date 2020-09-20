
public class BSTDictionary<E, K extends Sortable> implements Dictionary<E, K> {	
	
	BSTNode<E, K> root;

	public BSTDictionary() {
		this(null);
	}

	public BSTDictionary(BSTNode<E, K> root) {
		this.root = root;
	}
	
	/**
	 * Iterates through the binary tree  calling the searchNode() method to get
	 * to a specific key and returns the element at that position
	 * 
	 * @param key
	 * @return
	 */
	public E search(K key) {
		BSTNode<E, K> nodeFound = searchNode(key);
		if(nodeFound == null) {
			return null;
		}
		else {
			return nodeFound.getElement();
		}
	}
	 
	/**
	 * A method that calls the insertBelow() method, and inserts a specified element at
	 * a specified key.
	 * 
	 * @param key
	 * @param element
	 */
	public void insert(K key, E element) {
		if(root == null) {
			root = new BSTNode<E, K>(key, element, null, null);
		}
		else {
			insertKey(root, key, element);
		}		
	}

	/**
	 * Deletes the element at the specified key, but calling the recursive deleteR() method
	 * 
	 * @param key
	 */
	public void delete(K key) {
		this.root = deleteRight(root, key);		
	}
	
	/**
	 * Calls the print function method to print the binary tree in ascending order.
	 */
	public void printTree() {
		System.out.println("The Binary Search Tree:");
		printHelper(root);
	}
	
	/**
	 * A method that calls the postOrderDepth() method to return the integer value of the number
	 * of elements that are inside the tree/
	 * 
	 * @return
	 */
	public int depth() {
		return depthHelper(root, 0);
	}
	
	/**
	 * A Method that calls searchBelow() to find the element with the specified key
	 * 
	 * @param key
	 * @return
	 */
	public BSTNode<E, K> searchNode(K key) {
		if(key == null) {
			return null; //looking for nothing
		}
		if(root == null) {
			return null; //tree is empty
		}
		//if the keys are equal
		if(key.compareTo(root.getKey()) == 0) {
			return root;
		}
		//if key is less than the root key
		else if(key.compareTo(root.getKey()) < 0) {
			return searchKey(root.getLeft(), key);
		}
		//if key is greater than the root key
		else if(key.compareTo(root.getKey()) > 0) {
			return searchKey(root.getRight(), key);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Recursive method to fine the location of a specific key
	 * 
	 * @param n
	 * @param key
	 * @return
	 */
	public BSTNode<E,K> searchKey(BSTNode<E, K> node, K key) {
		if(node == null) {
			return null; 
		}
		//found key looking for
		if(key.compareTo(node.getKey()) == 0) {
			return node;
		}
		//given key greater than key of current node
		else if (key.compareTo(node.getKey()) > 0) {
			return searchKey(node.getRight(), key);
		}
		//given key less than key of current node
		else if(key.compareTo(node.getKey()) < 0) {
			return searchKey(node.getLeft(), key);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Recursive method that iterates thought the tree until getting to the right key and
	 * creates a node with that element.
	 * 
	 * @param root
	 * @param key
	 * @param element
	 */
	public void insertKey(BSTNode<E, K> root, K key, E element) {
		
		//if the key equals the root.
		if(key.compareTo(root.getKey()) == 0) {
			
		}
		//The key is greater than the current node's key so it goes to the right 
		else if(key.compareTo(root.getKey()) > 0) {
			if(root.getRight() == null) {
				//Creates and sets the right child node
				root.setRight(new BSTNode<E, K>(key, element, null, null));
			}
			else {
				insertKey(root.getRight(), key, element);
			}
		}
		//The key is less than the current node's key so it goes to the left
		else if(key.compareTo(root.getKey()) < 0) {
			if(root.getLeft() == null) {
				//Creates and sets the left child node
				root.setLeft(new BSTNode<E, K>(key, element, null, null));
			}
			else {
				insertKey(root.getLeft(), key, element);
			}
		}		
	}
	
	/**
	 * A Recursive method that calls the deleteDoubleNode() method and iterates through the tree,
	 * starting at the specified node, until it gets to the specified key and deletes the element 
	 * at that key location.
	 * 
	 * @param n
	 * @param key
	 * @return
	 */
	public BSTNode<E, K> deleteRight(BSTNode<E, K> node, K key) {
		//Checks to see if at the specified key location
		if(key.compareTo(node.getKey()) == 0) {
			//If the node is a leaf
			if(node.getLeft() == null && node.getRight() == null) {
				return null;
			}
			//Node with only one child; the right child
			else if((node.getLeft() == null) && (node.getRight() != null)) {
				return node.getRight();
			}
			//Node with only on child; the left child
			else if((node.getLeft() != null) && (node.getRight() == null)) {
				return node.getLeft();
			}
			//If it is a node with 2 children
			else if((node.getLeft() != null) && (node.getRight() != null)) {
				BSTNode<E, K> newNode = leftMostNode(node.getRight()); 
				BSTNode<E, K> newLeft = node.getLeft();
				newNode.setRight(deleteDoubleNode(node.getRight())); 
				newNode.setLeft(newLeft);
				return newNode;
			}
			else {
				return node;
			}
		}
		//Checks if to go to the left child
		else if(key.compareTo(node.getKey()) < 0) {
			if(node.getLeft() != null) {
				node.left = deleteRight(node.getLeft(), key);
				return node;
			}
		}
		//Checks if to go the right child
		else {
			if(node.getRight() != null) {
				node.right = deleteRight(node.getRight(), key);
				return node;
			}
		}
		return node;		
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public BSTNode<E, K> deleteDoubleNode(BSTNode<E, K> node) {
		if(node.getLeft() == null) {
			return node.getRight();
		}
		else {
			node.setLeft(deleteDoubleNode(node.getLeft()));
		}
		return node;
	}
	
	/**
	 * A recursive method, that goes through the binary tree and prints the respective element in order.
	 * 
	 * @param node
	 */
	public void printHelper(BSTNode<E,K> node) {
		if(node != null) {
			printHelper(node.getLeft()); //get the left keys 
			System.out.println("Element: " + node.getElement().toString());
			printHelper(node.getRight()); //get the right keys.
		}
	}
	
	/**
	 * A Recursive counting method that goes through the tree counting the number of node that it
	 * visits until getting the bottom of of the tree from the initial specified node.
	 * 
	 * @param node
	 * @param count
	 * @return
	 */
	private int depthHelper(BSTNode<E, K> node, int count) {
		if(node != null) {
			return Math.max(depthHelper(node.getLeft(), count + 1), depthHelper(node.getRight(), count + 1));
		}
		else {
			return count;
		}
	}
	
	/**
	 * Iterates through the binary tree until it gets the left most node,
	 * which would be the smallest node in the tree.
	 * 
	 * @param n
	 * @return
	 */
	public BSTNode<E, K> leftMostNode(BSTNode<E, K> node) {
		while(node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;
	}
}