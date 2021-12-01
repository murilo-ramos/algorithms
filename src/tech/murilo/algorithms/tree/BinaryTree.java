package tech.murilo.algorithms.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BinaryTree {
	
	private static int preIndex = 0;
	
	private static class Node {
		public final int value;
		public Node left;
		public Node right;
		
		public Node(int value) {
			this.value = value;
		}
	}
	
	private enum DisplayOrder {
		LEVELORDER,
		PREORDER,
		INORDER,
		POSTORDER
	}
	
	private Node root;
	
	public BinaryTree() {
	}
	
	public void initializeFromInput() {
		try (var scanner = new Scanner(System.in)) {
			System.out.println("Enter root value:");		
			int value = scanner.nextInt();
			
			root = new Node(value);
			
			Queue<Node> queue = new LinkedList<>();
			queue.add(root);
			
			Node p;
			while (!queue.isEmpty()) {
				p = queue.poll();
				
				System.out.println(String.format("Enter left value from %s: ", p.value));		
				value = scanner.nextInt();
				
				if (value != -1) {
					var node = new Node(value);
					p.left = node;
					queue.add(node);
				}
				
				System.out.println(String.format("Enter right value from %s: ", p.value));		
				value = scanner.nextInt();
				
				if (value != -1) {
					var node = new Node(value);
					p.right = node;
					queue.add(node);
				}
			}
		}
	}
	
	public void initializeFromTraversal(int[] inorder, int[] preorder) {
		preIndex = 0;
		root = generateFromTraversal(inorder, preorder, 0, inorder.length - 1);
	}
	
	private Node generateFromTraversal(int[] inorder, int[] preorder, int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			return null;
		}
		
		var node = new Node(preorder[preIndex++]);
		
		if (startIndex == endIndex) {
			return node;
		}
		
		int splitIndex = searchInOrder(inorder, startIndex, endIndex, node.value);
		node.left = generateFromTraversal(inorder, preorder, startIndex, splitIndex - 1);
		node.right = generateFromTraversal(inorder, preorder, splitIndex + 1, endIndex);
		
		return node;
	}
	
	private int searchInOrder(int[] values, int startIndex, int endIndex, int value) {
		for (int i = startIndex; i <= endIndex; i++) {
			if (values[i] == value) {
				return i;
			}
		}
		
		return -1;
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
	
	public int count() {
		return count(root);
	}
	
	private int count(Node node) {
		if (node == null) {
			return 0;
		}
		
		int x = count(node.left);
		int y = count(node.right);
		
		return x + y + 1;
	}
	
	public int countLeaf() {
		return countLeaf(root);
	}
	
	private int countLeaf(Node node) {
		if (node == null) {
			return 0;
		}
		
		int x = countLeaf(node.left);
		int y = countLeaf(node.right);
		
		if (node.right == null && node.left == null) {
			return x + y + 1;
		} else {
			return x + y;
		}
	}
	
	public int sum() {
		return sum(root);
	}
	
	private int sum(Node node) {
		if (node == null) {
			return 0;
		}
		
		int x = sum(node.left);
		int y = sum(node.right);
		
		return x + y + node.value;
	}
	
	public void display() {
		display(DisplayOrder.PREORDER, false);
	}
	
	public void display(DisplayOrder order, boolean iterative) {
		if (root == null) {
			System.out.println("Tree is empty.");
			return;
		}
		
		switch (order) {
		case LEVELORDER:
			displayLevelOrder();
			break;
		case PREORDER:
			if (iterative) {
				displayPreOrderIterative();
			} else {
				displayPreOrder(root);
			}
			break;
		case INORDER:
			if (iterative) {
				displayInOrderIterative();
			} else {
				displayInOrder(root);
			}
			break;
		case POSTORDER:
			if (iterative) {
				displayPostOrderIterative();
			} else {
				displayPostOrder(root);
			}
			break;
		}
		
		System.out.println();
	}
	
	private void displayLevelOrder() {
		Queue<Node> queue = new LinkedList<>();
		Node node = root;
		
		System.out.print(String.format("%s ", node.value));
		queue.add(node);
		
		while (!queue.isEmpty()) {
			node = queue.poll();
			
			if (node.left != null) {
				System.out.print(String.format("%s ", node.left.value));
				queue.add(node.left);
			}
			
			if (node.right != null) {
				System.out.print(String.format("%s ", node.right.value));
				queue.add(node.right);
			}
		}
	}
	
	private void displayPreOrder(Node node) {
		if (node != null) {
			System.out.print(String.format("%s ", node.value));
			displayPreOrder(node.left);
			displayPreOrder(node.right);
		}
	}
	
	private void displayInOrder(Node node) {
		if (node != null) {
			displayInOrder(node.left);
			System.out.print(String.format("%s ", node.value));
			displayInOrder(node.right);
		}
	}
	
	private void displayPostOrder(Node node) {
		if (node != null) {
			displayPostOrder(node.left);
			displayPostOrder(node.right);
			System.out.print(String.format("%s ", node.value));
		}
	}
	
	private void displayPreOrderIterative() {
		var stack = new Stack<Node>();
		var node = root;
		
		while (node != null || !stack.isEmpty()) {
			if (node != null) {
				System.out.print(String.format("%s ", node.value));
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				node = node.right;
			}
		}
	}
	
	private void displayInOrderIterative() {
		var stack = new Stack<Node>();
		var node = root;
		
		while (node != null || !stack.isEmpty()) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				System.out.print(String.format("%s ", node.value));
				node = node.right;
			}
		}
	}
	
	private void displayPostOrderIterative() {
		class PostNode extends Node { //an object oriented way to have a difference between nodes
			public PostNode(Node node) {
				super(node.value);
				left = node.left;
				right = node.right;
			}
		}
		
		var stack = new Stack<Node>();
		var node = root;
		
		while (node != null || !stack.isEmpty()) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				
				if (node instanceof PostNode) {
					System.out.print(String.format("%s ", node.value));
					node = null;
				} else {
					stack.push(new PostNode(node));
					node = node.right;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		var tree = new BinaryTree();
		
		//tree.initializeFromInput();
		tree.initializeFromTraversal(new int[] {7, 6, 9, 3, 4, 5, 8, 2, 1}, 
				                     new int[] {4, 7, 9, 6, 3, 2, 5, 8, 1});
		
		System.out.println("Height: " + tree.height());
		System.out.println("Sum: " + tree.sum());
		System.out.println("Count: " + tree.count());
		System.out.println("Leafs: " + tree.countLeaf());
		
		tree.display(DisplayOrder.LEVELORDER, false);
		tree.display(DisplayOrder.PREORDER, false);
		tree.display(DisplayOrder.INORDER, false);
		tree.display(DisplayOrder.POSTORDER, false);
		
		tree.display(DisplayOrder.LEVELORDER, true);
		tree.display(DisplayOrder.PREORDER, true);
		tree.display(DisplayOrder.INORDER, true);
		tree.display(DisplayOrder.POSTORDER, true);
	}
}
