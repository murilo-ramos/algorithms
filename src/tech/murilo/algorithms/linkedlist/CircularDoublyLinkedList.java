package tech.murilo.algorithms.linkedlist;

public class CircularDoublyLinkedList {
	
	private static int recursiveDisplauAuxiliarFlag = 0;
	
	@SuppressWarnings("unused")
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
	
	private Node head;
	
	public CircularDoublyLinkedList(int[] values) {
		if (values == null || values.length == 0) {
			return;
		}
		
		head = new Node(values[0]);		
		Node node = head;
		
		for (int i = 1; i < values.length; i++) {
			node.next = new Node(values[i]);
			node.next.previous = node;
			node = node.next;
		}
		
		node.next = head;
		head.previous = node;
	}
	
	public CircularDoublyLinkedList() {
	}
	
	public void clear() {
		head = null;
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
		
		var linkedList = new CircularDoublyLinkedList(values);
		System.out.println(linkedList.count());
		linkedList.display();
	}
}
