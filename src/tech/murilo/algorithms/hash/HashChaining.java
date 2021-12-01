package tech.murilo.algorithms.hash;

public class HashChaining {
	
	private static class Node {
		int value;
		Node next;
		
		public Node(int value) {
			this.value = value;
		}
	}
	
	private Node[] hash = new Node[10];
	
	public void insert(int value) {
		int key = getHashKey(value);
		hash[key] = insertInList(hash[key], value);
	}
	
	public int search(int value) {
		int result = -1;
		
		int key = getHashKey(value);
		
		if (existsInList(hash[key], value)) {
			result = value;
		}
		
		return result;
	}
	
	public void display() {
		for (int i = 0; i < hash.length; i++) {
			System.out.print(String.format("%s: ", i));
			if (hash[i] == null) {
				System.out.print("Empty");
			} else {
				Node node = hash[i];				
				while (node != null) {
					System.out.print(String.format("%s ", node.value));
					node = node.next;
				}
			}
			System.out.println();
		}
	}
	
	private Node insertInList(Node root, int value) {
		var newNode = new Node(value);
		
		if (root == null) {
			return newNode;
		}
		
		if (newNode.value < root.value) {
			newNode.next = root;
			return newNode;
		}
		
		Node node = root;
		
		while (true) {
			if (node.next == null) {
				node.next = newNode;
				break;
			}
			if (newNode.value < node.next.value) {
				newNode.next = node.next;
				node.next = newNode;
				break;
			}
			node = node.next;
		}
		
		return root;
	}
	
	private boolean existsInList(Node root, int value) {
		if (root == null) {
			return false;
		}
		
		Node node = root;
		
		while (node != null) {
			if (value == node.value) {
				return true;
			}
			node = node.next;
		}
		
		return false;
	}
	
	private static int getHashKey(int value) {
		return value % 10;
	}
	
	public static void main(String[] args) {
		int[] values = new int[] {16, 12, 25, 39, 6, 122, 5, 68, 75, 10, 24, 53, 123};
		
		var hash = new HashChaining();
		
		for (int value : values) {
			hash.insert(value);
		}
		
		hash.display();
		
		System.out.println("Search 6:" + hash.search(6));
		System.out.println("Search 95:" + hash.search(95));
		
		
	}

}
