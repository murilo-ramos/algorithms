package tech.murilo.algorithms.queue;

public class QueueLinkedList {
	
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
	
	private Node front;
	private Node rear;
	
	public QueueLinkedList() {
	}
	
	public void enqueue(int data) {
		var node = new Node(data);
		
		// java will throw exception if memory limit is reached
		
		if (front == null) {
			front = node;
			rear = node;
		} else {
			rear.next = node;
			rear = rear.next;
		}
	}
	
	public int dequeue() {
		if (front == null) {
			throw new StackOverflowError("Queue underflow: queue is empty");
		}
		
		var node = front;
		front = front.next;
		
		if (front == null) {
			rear = front;
		}
		
		return node.data;
	}
	
	public void display() {
		var node = front;
		
		while (node != null) {
			System.out.print(String.format("%s ", node.data));
			node = node.next;
		}
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		var queue = new QueueLinkedList();
		
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		
		queue.display();
		System.out.println(queue.dequeue());
		
		queue.enqueue(4);
		queue.enqueue(5);
		queue.display();
	}
}
