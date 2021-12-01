package tech.murilo.algorithms.bst;

import java.util.Stack;

public class BinarySearchTree {
	
	private static class Node {
		private int value;
		private Node left;
		private Node right;
		
		public Node(int value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}
	
	private Node root;
	
	public BinarySearchTree() {
	}
	
	public void initializeFromPreOrder(int[] preorder) {
		int i = 0;
		
		root = new Node(preorder[i++]);
		
		var stack = new Stack<Node>();
		Node node = root;
		
		while (i < preorder.length) {
			if (preorder[i] < node.value) {
				var newNode = new Node(preorder[i++]);
				node.left = newNode;
				stack.push(node);
				node = newNode;
			} else {
				if (preorder[i] > node.value && (stack.isEmpty() || preorder[i] < stack.peek().value)) {
					var newNode = new Node(preorder[i++]);
					node.right = newNode;
					node = newNode;
				} else {
					node = stack.pop();
				}
			}
		}
	}
	
	public void clear() {
		root = null;
	}
	
	public void insert(int value) {
		insert(value, false);
	}
	
	private void insert(int value, boolean recursive) {
		if (recursive) {
			insertInteractive(value);
		} else {
			root = insertRecursive(root, value);
		}
	}
	
	private void insertInteractive(int value) {
		if (root == null) {
			root = new Node(value);
			return;
		}
		
		Node node = root;
		
		while (node != null) {
			if (value > node.value) {
				if (node.right == null) {
					node.right = new Node(value);
					return;
				}
				node = node.right;
			} else {
				if (node.left == null) {
					node.left = new Node(value);
					return;
				}
				node = node.left;
			}
		}
	}
	
	private Node insertRecursive(Node node, int value) {
		if (node == null) {
			node = new Node(value);
			return node;
		}
		
		if (value < node.value) {
			node.left = insertRecursive(node.left, value);
		} else if (value > node.value) {
			node.right = insertRecursive(node.right, value);
		}
		
		return node;
	}
	
	public Node search(int value) {
		Node node = root;
		
		while (node != null) {
			if (node.value == value) {
				return node;
			}
			
			if (value > node.value) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		
		return null;
	}
	
	public void delete(int value) {
		root = delete(root, value);
	}
	
	private Node delete(Node node, int value) {
		if (node == null) {
			return null;
		}
		
		if (value == node.value && node.left == null && node.right == null) {
			/*if (node == root) {
				root = null;
			}*/
			
			return null;
		}
		
		if (value < node.value) {
			node.left = delete(node.left, value);
		} else if (value > node.value) {
			node.right = delete(node.right, value);
		} else {
			if (height(node.left) > height(node.right)) {
				Node aux = getInOrderPredecessor(node.left);
				node.value = aux.value;
				node.left = delete(node.left, aux.value);
			} else {
				Node aux = getInOrderSuccessor(node.right);
				node.value = aux.value;
				node.right = delete(node.right, aux.value);
			}
		}
		
		return node;
	}
	
	private Node getInOrderPredecessor(Node node) {		
		while (node != null && node.right != null) {
			node = node.right;
		}
		
		return node;
	}
	
	private Node getInOrderSuccessor(Node node) {		
		while (node != null && node.left != null) {
			node = node.left;
		}
		
		return node;
	}
	
	
	public int height() {
		return height(root);
	}
	
	private int height(Node node) {
		if (node == null) {
			return 0;
		}
		
		int x = height(node.left);
		int y = height(node.right);
		
		if (x > y) {
			return x + 1;
		} else {
			return y + 1;
		}
	}
	
	public void display() {
		displayInOrder(root);
		System.out.println();
	}
	
	private void displayInOrder(Node node) {
		if (node != null) {
			displayInOrder(node.left);
			System.out.print(String.format("%s ", node.value));
			displayInOrder(node.right);
		}
	}
	
	public static void main(String[] args) {
		var tree = new BinarySearchTree();
		
		tree.insert(10);
		tree.insert(5);
		tree.insert(20);
		tree.insert(8);
		tree.insert(30);
		
		tree.display();
		System.out.println("Search X:" + tree.search(5).value);
		System.out.println("Search X:" + tree.search(11));
		System.out.println("Search X:" + tree.search(30).value);
		
		tree.clear();
		tree.insert(10, true);
		tree.insert(5, true);
		tree.insert(20, true);
		tree.insert(8, true);
		tree.insert(30, true);
		
		tree.display();
		System.out.println("Search X:" + tree.search(5).value);
		System.out.println("Search X:" + tree.search(11));
		System.out.println("Search X:" + tree.search(30).value);
		
		tree.display();
		tree.delete(30);
		tree.display();
		
		tree.clear();
		tree.initializeFromPreOrder(new int[] {30, 20, 10, 15, 25, 40, 50, 45});
		tree.display();
	}
}
