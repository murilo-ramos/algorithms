package tech.murilo.algorithms.linkedlist;

public class DoublyLinkedList {
	
	private static class Node {
		int data;
		Node previous;
		Node next;
		
		public Node(int data) {
			this.data = data;
		}
		
		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}
	
	private Node first;
	
	public DoublyLinkedList(int[] values) {
		if (values == null || values.length == 0) {
			return;
		}
		
		first = new Node(values[0]);		
		Node node = first;
		
		for (int i = 1; i < values.length; i++) {
			node.next = new Node(values[i]);
			node.next.previous = node;
			node = node.next;
		}
	}
	
	public DoublyLinkedList() {
	}
	
	public void clear() {
		first = null;
	}
	
	public void append(int value) {
		if (first == null) {
			insert(value, 0);
		} else {
			Node node = first;
			
			while (node.next != null) {
				node = node.next;
			}
			
			node.next = new Node(value);
			node.next.previous = node;
		}
	}
	
	public void insert(int value, int position) {
		var listLength = count();
		
		if (position < 0 || (listLength > 0 && position >= listLength)) {
			return;
		}
		
		var newNode = new Node(value);
		
		if (position == 0) {
			if (first == null) {
				first = newNode;
			} else {
				newNode.next = first;
				first.previous = newNode;
				first = newNode;
			}
		} else {
			Node node = first;
			
			for (int i = 0; i < position - 1; i++) {
				node = node.next;
			}
			
			newNode.next = node.next;
			node.next.previous = newNode;
			node.next = newNode;
			newNode.previous = node;			
		}
	}
	
	public Node delete(int position) {
		var listLength = count();
		
		if (first == null || position < 0 || position >= listLength) {
			return null;
		}
		
		if (position == 0) {
			var node = first;
			if (first.next == null) {
				first = null;
			} else {
				first = first.next;
				first.previous = null;
			}
			
			return node;
		} else {
			Node node = first;
			
			for (int i = 0; i < position; i++) {
				node = node.next;
			}
			
			node.previous.next = node.next;
			
			if (node.next != null) {
				node.next.previous = node.previous;
			} 
			
			return node;
		}
	}
	
	public void reverse() {
		var node = first;
		
		while (node != null) {
			var temp = node.next;
			node.next = node.previous;
			node.previous = temp;
			node = node.previous;
			
			if (node != null && node.next == null) {
				first = node;
			}
		}
	}
	
	public int count() {
		return count(false);
	}
	
	public int count(boolean recursive) {
		if (recursive) {
			return countRecursive(first);
		}
		
		int count = 0;		
		Node node = first;
		
		while (node != null) {
			count++;
			node = node.next;
		}
		
		return count;
	}
	
	public void display() {
		display(false);
	}
	
	public void display(boolean recursive) {
		if (recursive) {
			displayRecursive(first);
			System.out.println();
			return;
		}
		
		Node node = first;
		
		while (node != null) {
			System.out.print(String.format("%d ", node.data));
			node = node.next;
		}
		
		System.out.println();
	}
	
	private void displayRecursive(Node node) {
		if (node != null) {
			System.out.print(String.format("%d ", node.data));
			displayRecursive(node.next);
		}
	}
	
	private int countRecursive(Node node) {
		if (node != null) {
			return countRecursive(node.next) + 1;
		} 
		
		return 0;
	}
	
	public static void main(String[] args) {
		var values = new int[] {1, 23, 0, 4, 5, 45};
		
		var linkedList = new DoublyLinkedList(values);
		System.out.println(linkedList.count());
		linkedList.display();
		
		linkedList.insert(10, -1);
		linkedList.display();
		
		linkedList.insert(10, 6);
		linkedList.display();
		
		linkedList.insert(10, 0);
		linkedList.display();
		
		linkedList.insert(11, 6);
		linkedList.display();
		
		linkedList.insert(3, 3);
		linkedList.display();
		
		linkedList.append(20);
		linkedList.display();
		
		linkedList.append(21);
		linkedList.display();
		
		linkedList.append(22);
		linkedList.display();
		
		System.out.println();
		
		linkedList.delete(0);
		linkedList.display();
		
		linkedList.delete(1);
		linkedList.display();
		
		linkedList.delete(5);
		linkedList.display();
		
		linkedList.delete(7);
		linkedList.display();
		
		linkedList.delete(7);
		linkedList.display();
		
		linkedList.clear();
		linkedList.delete(0);
		
		linkedList.display();
		
		linkedList = new DoublyLinkedList(values);
		linkedList.display();
		
		linkedList.reverse();
		linkedList.display();
	}
}
