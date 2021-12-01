package tech.murilo.algorithms.queue;

public class Dequeue {
	
	private final int size;
	private final int queue[];
	private int front = -1;
	private int rear = -1;
	
	public Dequeue(int size) {
		this.size = size;
		this.queue = new int[this.size];
	}
	
	public void enqueueFront(int value) {
	    if (front == -1) {
	    	throw new StackOverflowError("Dequeue overflow: queue is full");
	    } 
	    
	    queue[front] = value;
        front--;
	}
	 
	public void enqueueRear(int value) {
	    if (isFull()) {
	    	throw new StackOverflowError("Dequeue overflow: queue is full");
	    }
	    
	    rear++;
        queue[rear] = value;
	}
	 
	public int dequeueFront() {
	    if (isEmpty()) {
	    	throw new StackOverflowError("Dequeue underflow: queue is empty");
	    } 
	    
	    front++;
	    return queue[front];
	}
	 
	public int dequeueRear() {
	    if (rear == -1) {
	    	throw new StackOverflowError("Dequeue underflow: queue is empty");
	    } 
	    
	    var result = queue[rear];
        rear--;
        
	    return result;
	}
	
	public boolean isEmpty() {
	    if (front == rear) {
	        return true;
	    }
	    
	    return false;
	}
	 
	public boolean isFull() {
	    if (rear == size - 1) {
	        return true;
	    }
	    
	    return false;
	}
	 
	public void display() {
	    for (int i = front + 1; i <= rear; i++) {
	    	System.out.print(String.format("%s ", queue[i]));
	    }
	    
	    System.out.println();
	}
	
	public static void main(String[] args) {
		var dequeue = new Dequeue(10);
		
		dequeue.enqueueRear(1);
		dequeue.enqueueRear(3);
		dequeue.enqueueRear(5);
		dequeue.display();
		
		dequeue.dequeueFront();
		dequeue.dequeueFront();
		dequeue.dequeueFront();
		dequeue.display();
		
		dequeue.enqueueFront(2);
		dequeue.enqueueFront(4);
		dequeue.enqueueFront(6);
		dequeue.display();
	}

}
