package tech.murilo.algorithms.linkedlist;

public class CircularLinkedList {
	
	private static class Node {
		int data;
		Node next;
		
		public Node(int data) {
			this.data = data;
		}
		
		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}
	
	private static int recursiveDisplauAuxiliarFlag = 0;
	
	private Node head;
	
	
	public CircularLinkedList(int[] values) {
		if (values == null || values.length == 0) {
			return;
		}
		
		head = new Node(values[0]);		
		Node node = head;
		
		for (int i = 1; i < values.length; i++) {
			node.next = new Node(values[i]);
			node = node.next;
		}
		
		node.next = head;
	}
	
	public CircularLinkedList() {
	}
	
	public void clear() {
		head = null;
	}
	
	public void append(int value) {
		if (head == null) {
			insert(value, 0);
		} else {
			Node node = head;
			
			while (node.next != head) {
				node = node.next;
			}
			
			node.next = new Node(value);
			node.next.next = head;
		}
	}
	
	public void insert(int value, int position) {
		var listLength = count();
		
		if (position < 0 || (listLength > 0 && position >= listLength)) {
			return;
		}
		
		Node node = head;
		var newNode = new Node(value);
		
		if (position == 0) {
			if (head == null) {
				head = newNode;
				head.next = head;
			} else {
				while (node.next != head) {
					node = node.next;
				}
				
				node.next = newNode;
				newNode.next = head;
				head = newNode;
			}
		} else {
			for (int i = 0; i < position - 1; i++) {
				node = node.next;
			}
			
			newNode.next = node.next;
			node.next = newNode;
		}
	}
	
	public Node delete(int position) {
		var listLength = count();
		
		if (position < 0 || position >= listLength) {
			return null;
		}
		
		Node node = head;
		Node result;
		
		if (position == 0) {
			while(node.next != head) {
				node = node.next;
			}
			
			if (head == node) {
				head = null;
				result = node;
			} else {
				result = head;
				node.next = head.next;
				head = node.next;
			}
		} else {
			for (int i = 0; i <position - 1; i++) {
				node = node.next;
			}
			
			result = node.next;
			node.next = node.next.next;
		}
		
		return result;
		
	}
	
	public int count() {
		int count = 0;
		
		if (head == null) {
			return count;
		}
		
		Node node = head;
		
		do {
			count++;
			node = node.next;
		} while (node != head);
		
		return count;
	}
	
	public void display() {
		display(false);
	}
	
	public void display(boolean recursive) {
		if (recursive) {
			recursiveDisplauAuxiliarFlag = 0;
			displayRecursive(head);
			System.out.println();
			return;
		}
		
		Node node = head;
		
		do {
			System.out.print(String.format("%d ", node.data));
			node = node.next;
		} while (node != head);
		
		System.out.println();
	}
	
	private void displayRecursive(Node node) {
		if (node == head && recursiveDisplauAuxiliarFlag != 0) {
			return;
		}
		
		recursiveDisplauAuxiliarFlag = 1;
		System.out.print(String.format("%d ", node.data));
		displayRecursive(node.next);
	}
	
	
	
	public static void main(String[] args) {
		var values = new int[] {1, 23, 0, 4, 5, 45};
		
		var linkedList = new CircularLinkedList(values);
		
		linkedList.display();
		linkedList.display(true);
		
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
		
		linkedList.clear();
		linkedList.insert(3, 0);
		linkedList.display();
		
		linkedList = new CircularLinkedList(values);
		linkedList.display();
		
		linkedList.delete(0);
		linkedList.display();
		
		linkedList.delete(4);
		linkedList.display();
		
		linkedList.delete(1);
		linkedList.display();
		
		linkedList.append(20);
		linkedList.display();
		
		linkedList.append(21);
		linkedList.display();
		
		linkedList.append(22);
		linkedList.display();
	}
}
