package tech.murilo.algorithms.linkedlist;

import java.util.Stack;

public class StudentChallenge {
	
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
	
	public static class LinkedList {
		private Node first;
		private Node last;
		
		public LinkedList(int[] values) {
			if (values == null || values.length == 0) {
				return;
			}
			
			first = new Node(values[0]);		
			Node node = first;
			
			for (int i = 1; i < values.length; i++) {
				node.next = new Node(values[i]);
				node = node.next;
				last = node;
			}
		}
		
		public Node getMiddleUsingLength() {
			if (first == null || first.next == null) {
				return first;
			}
			
			Node node = first;
			int count = 0;
			
			while (node != null) {
				count++;
				node = node.next;
			}
			
			int middlePosition = (int) Math.ceil(count / 2.0);
			
			node = first;
			
			for (int i = 0; i < middlePosition - 1; i++) {
				node = node.next;
			}
			
			return node;
		}
		
		public Node getMiddleUsingPointers() {
			Node node, sonic;
			
			node = sonic = first;
			
			while (sonic != null) {
				sonic = sonic.next;
				
				if (sonic != null) {
					sonic = sonic.next;
				}
				
				if (sonic != null) {
					node = node.next;
				}
			}
			
			return node;
		}
		
		public Node getMiddleUsingStack() {
			var stack = new Stack<Node>();
			
			Node node = first;
			
			while (node != null) {
				stack.push(node);
				node = node.next;
			}
			
			int toRemove = (int) Math.floor(stack.size() / 2.0);
			
			while (toRemove != 0) {
				stack.pop();
				toRemove--;
			}
			
			return stack.pop();
		}
		
		public Node getIntersection(LinkedList linkedList) {
			var stackA = new Stack<Node>();
			var stackB = new Stack<Node>();
			
			var node = first;
			
			while (node != null) {
				stackA.push(node);
				node = node.next;
			}
			
			node = linkedList.first;
			
			while (node != null) {
				stackB.push(node);
				node = node.next;
			}
			
			while (stackA.peek() == stackB.peek()) {
				node = stackA.pop();
				stackB.pop();
			}
			
			return node;
		}
	}
	
	public static void middles() {
		var values = new int[] {1, 23, 0, 8, 4, 5, 45};
		
		var linkedList = new LinkedList(values);
		System.out.println("Middle 01: " + linkedList.getMiddleUsingLength().data);
		System.out.println("Middle 02: " + linkedList.getMiddleUsingPointers().data);
		System.out.println("Middle 03: " + linkedList.getMiddleUsingStack().data);
	}
	
	public static void intersection() {
		var linkedList01 = new LinkedList(new int[] {1, 23, 0, 8, 4, 5, 45});
		var linkedList02 = new LinkedList(new int[] {11, 22, 33});
		linkedList02.last.next = linkedList01.first.next.next.next.next;
		
		System.out.println("Intersection: " + linkedList01.getIntersection(linkedList02).data);
	}
	
	public static void main(String[] args) {
		middles();
		intersection();
	}

}
