package tech.murilo.algorithms.queue;

import java.util.Stack;

public class QueueTwoStacks {
	
	private final Stack<Integer> stackA = new Stack<Integer>();
	private final Stack<Integer> stackB = new Stack<Integer>();
	
	public QueueTwoStacks() {
	}
	
	public void enqueue(int value) {
		stackA.push(value);
	}
	
	public int dequeue() {
		if (!stackB.isEmpty()) {
			return stackB.pop();
		}
		
		if (!stackA.isEmpty()) {
			while (!stackA.isEmpty()) {
				stackB.push(stackA.pop());
			}
			
			return stackB.pop();
		}
		
		throw new StackOverflowError("Queue is empty.");
	}
	
	public void display() {
		if (!stackB.isEmpty()) {
			for (int i = stackB.size() - 1; i >= 0; i--) {
				System.out.print(String.format("%s ", stackB.get(i)));
			}
		}
		
		if (!stackA.isEmpty()) {
			for (int i = 0; i < stackA.size(); i++) {
				System.out.print(String.format("%s ", stackA.get(i)));
			}
		}
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		var queue = new QueueTwoStacks();
		
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		queue.display();
		
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		queue.display();
		
		queue.enqueue(6);
		queue.enqueue(7);
		queue.display();
		
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		
		queue.display();
	}

}
