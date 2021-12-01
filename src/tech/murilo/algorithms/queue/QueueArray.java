package tech.murilo.algorithms.queue;

public class QueueArray {
	
	private final int size;
	private final int queue[];
	private int front = -1;
	private int rear = -1;
	
	public QueueArray(int size) {
		this.size = size;
		this.queue = new int[this.size];
	}
	
	public void enqueue(int value) {
		if (rear == this.size - 1) {
			if (front == -1) {
				throw new StackOverflowError("Queue overflow: queue is full");
			} else {
				rearrange();
			}
		}
		
		rear++;
		queue[rear] = value; 
	}
	
	private void rearrange() {
		int x = 0;
		int i;
		
		for (i = front + 1; i <= rear; i++) {
			queue[x++] = queue[i];
		}
		
		front = -1;
		rear = x - 1;
	}
	
	public int dequeue() {
		if (front == rear) {
			throw new StackOverflowError("Queue underflow: queue is empty");
		}
		 
		front++;
		var result = queue[front];
		
		return result;
	}
	
	public void display() {
		for (int i = front + 1; i <= rear ; i++) {
			System.out.print(String.format("%s ", queue[i]));
		}
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		var queue = new QueueArray(7);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);		
		queue.display();
		
		System.out.println(queue.dequeue());
		queue.display();
		
		queue.enqueue(4);
		queue.display();
		
		System.out.println(queue.dequeue());
		queue.display();
		
		queue = new QueueArray(5);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.dequeue();
		queue.dequeue();
		queue.enqueue(4);
		queue.enqueue(5);
		queue.enqueue(6);
		queue.enqueue(7);
		queue.display();
		
	}

}
