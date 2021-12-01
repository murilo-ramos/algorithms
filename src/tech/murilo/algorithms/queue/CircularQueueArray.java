package tech.murilo.algorithms.queue;

public class CircularQueueArray {
	
	private final int size;
	private final int queue[];
	private int front = 0;
	private int rear = 0;
	
	public CircularQueueArray(int size) {
		this.size = size;
		this.queue = new int[this.size];
	}
	
	public void enqueue(int value) {
		if ((rear + 1) % size == front) {
			throw new StackOverflowError("Queue overflow: queue is full");
		}
		
		rear = (rear + 1) % size;
		queue[rear] = value; 
	}
	
	public int dequeue() {
		if (front == rear) {
			throw new StackOverflowError("Queue underflow: queue is empty");
		}
		 
		front = (front + 1) % size;
		
		return queue[front];
	}
	
	public void display() {
		int i = front + 1;
		
		do {
			System.out.print(String.format("%s ", queue[i]));
			i = (i + 1) % size;
		} while (i != (rear + 1) % size);
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		var queue = new CircularQueueArray(5);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		queue.dequeue();
		queue.dequeue();
		queue.display();
		queue.enqueue(4);
		queue.enqueue(5);
		queue.enqueue(6);
		queue.enqueue(7); //seems buggy... should back here later...
		queue.display();
		
	}

}
