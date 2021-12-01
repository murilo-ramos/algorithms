package tech.murilo.algorithms.stack;

public class StackLinkedList {
	
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
	
	private Node top;
	
	public StackLinkedList() {
	}
	
	public void push(int data) {
		var node = new Node(data);
		
		// java will throw exception if memory limit is reached
		
		if (top == null) {
			top = node;
		} else {
			node.next = top;
			top = node;
		}
	}
	
	public int pop() {
		if (top == null) {
			return -1;
		}
		
		var node = top;
		top = top.next;
		
		return node.data;
	}
	
	public void display() {
		var node = top;
		
		while (node != null) {
			System.out.print(String.format("%s ", node.data));
			node = node.next;
		}
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		var stack = new StackLinkedList();
		
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		stack.push(50);
		
		stack.display();
		
		System.out.println(stack.pop());
		
		stack.display();
	}
}
