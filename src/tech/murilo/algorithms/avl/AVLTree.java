package tech.murilo.algorithms.avl;

public class AVLTree {
	
	private static class Node {
		private int value;
		private int height;
		private Node left;
		private Node right;
		
		public Node(int value) {
			this.value = value;
			this.height = 1;
		}
		
		public void updateHeight() {
			int vLeft = (left != null) ? left.height : 0;
			int vRight = (right != null) ? right.height : 0;
			
			this.height = (vLeft > vRight) ? vLeft + 1 : vRight + 1;
		}
		
		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}
	
	private Node root;
	
	public AVLTree() {
	}
	
	public void clear() {
		root = null;
	}
	
	public void insert(int value) {
		root = insertRecursive(root, value);
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
		
		node.updateHeight();
		
		if (calculateBalanceFactor(node) == 2 && calculateBalanceFactor(node.left) == 1) {
			return performLLRotation(node);
		} else if (calculateBalanceFactor(node) == 2 && calculateBalanceFactor(node.left) == -1) {
			return performLRRotation(node);
		} else if (calculateBalanceFactor(node) == -2 && calculateBalanceFactor(node.right) == -1) {
			return performRRRotation(node);
		} else if (calculateBalanceFactor(node) == -2 && calculateBalanceFactor(node.right) == 1) {
			return performRLRotation(node);
		}
		
		return node;
	}
	
	private void delete(int value) {
		root = delete(root, value);
	}
	
	private Node delete(Node node, int value) {
		if (node == null) {
			return null;
		}
		
		if (value == node.value && node.left == null && node.right == null) {
			return null;
		}
		
		if (value < node.value) {
			node.left = delete(node.left, value);
		} else if (value > node.value) {
			node.right = delete(node.right, value);
		} else {
			if (shouldDeleteByPredecessor(node)) {
				Node aux = getInOrderPredecessor(node.left);
				node.value = aux.value;
				node.left = delete(node.left, aux.value);
			} else {
				Node aux = getInOrderSuccessor(node.right);
				node.value = aux.value;
				node.right = delete(node.right, aux.value);
			}
		}
		
		node.updateHeight();
		
		if (calculateBalanceFactor(node) == 2 && calculateBalanceFactor(node.left) == 1) {
			return performLLRotation(node);
		} else if (calculateBalanceFactor(node) == 2 && calculateBalanceFactor(node.left) == -1) {
			return performLRRotation(node);
		} else if (calculateBalanceFactor(node) == 2 && calculateBalanceFactor(node.left) == 0) {
			return performLLRotation(node);
		} else if (calculateBalanceFactor(node) == -2 && calculateBalanceFactor(node.right) == -1) {
			return performRRRotation(node);
		} else if (calculateBalanceFactor(node) == -2 && calculateBalanceFactor(node.right) == 1) {
			return performRLRotation(node);
		} else if (calculateBalanceFactor(node) == -2 && calculateBalanceFactor(node.right) == 1) {
			return performRRRotation(node);
		}
		
		return node;
	}
	
	private boolean shouldDeleteByPredecessor(Node node) {
		if (node.left != null && node.right != null) {
			return node.left.height > node.right.height;
		}
		
		if (node.left != null) {
			return true;
		}
		
		return false;
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
	
	private Node performLLRotation(Node node) {
		Node nodeLeft = node.left;
		Node nodeLeftRight = nodeLeft.right;
		
		nodeLeft.right = node;
		node.left = nodeLeftRight;
		
		node.updateHeight();
		nodeLeft.updateHeight();
				
		return nodeLeft;
	}
	
	private Node performLRRotation(Node node) {
		Node nodeLeft = node.left;
		Node nodeLeftRight = nodeLeft.right;
		
		nodeLeft.right = nodeLeftRight.left;
		node.left = nodeLeftRight.right;
		
		nodeLeftRight.left = nodeLeft;
		nodeLeftRight.right = node;
		
		nodeLeft.updateHeight();
		node.updateHeight();
		nodeLeftRight.updateHeight();
		
		return nodeLeftRight;
	}
	
	private Node performRRRotation(Node node) {
		Node nodeRight = node.right;
		Node nodeRightLeft = nodeRight.left;
		
		nodeRight.left = node;
		node.right = nodeRightLeft;
		
		node.updateHeight();
		nodeRight.updateHeight();
				
		return nodeRight;
	}
	
	private Node performRLRotation(Node node) {
		Node nodeRight = node.right;
		Node nodeRightLeft = nodeRight.left;
		
		nodeRight.left = nodeRightLeft.right;
		node.right = nodeRightLeft.left;
		
		nodeRightLeft.right = nodeRight;
		nodeRightLeft.left = node;
		
		nodeRight.updateHeight();
		node.updateHeight();
		nodeRightLeft.updateHeight();
		
		return nodeRightLeft;
	}
	
	private int calculateBalanceFactor(Node node) {
		int left = (node != null && node.left != null) ? node.left.height : 0;
		int right = (node != null && node.right != null) ? node.right.height : 0;
		
		return left - right;
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
		var tree = new AVLTree();
		
		//LL
		tree.insert(30);
		tree.insert(20);
		tree.insert(10);
		
		tree.display();
		
		//RR
		tree.clear();
		tree.insert(10);
		tree.insert(20);
		tree.insert(30);
		
		tree.display();
		
		//LR
		tree.clear();
		tree.insert(50);
		tree.insert(10);
		tree.insert(20);
		
		tree.display();
		
		//RL
		tree.clear();
		tree.insert(20);
		tree.insert(50);
		tree.insert(30);
		
		tree.display();
		
		//delete
		tree.clear();
		tree.insert(10);
		tree.insert(20);
		tree.insert(30);
		tree.insert(25);
		tree.insert(28);
		tree.insert(27);
		tree.insert(5);
		
		tree.display();
		tree.delete(28);
		tree.display();
	}
}
