package tech.murilo.algorithms.stack;

public class StackArray {
	
	private final int size;
	private final int[] stack;
	private int top;
	
	public StackArray(int size) {
		this.size = size;
		this.stack = new int[this.size];
		this.top = -1;
	}
	
	public void push(int data) {
		if (top == size - 1) {
			throw new StackOverflowError("Stack overflow: stack is full");
		}
		
		stack[++top] = data;
	}
	
	public int pop() {
		if (top == -1) {
			throw new StackOverflowError("Stack underflow: stack is empty");
		}
		
		return stack[top--];
	}
	
	public int peek(int position) {
		int index = top - position;
		
		if (top == -1 || index < 0 || index > size -1) {
			throw new IndexOutOfBoundsException(String.format("Position %s not found", position));
		}
		
		return stack[index];
	}
	
	public int top() {
		if (top == -1) {
			throw new StackOverflowError("Stack underflow: stack is empty");
		}
		
		return stack[top];
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	public boolean isFull() {
		return top == size - 1;
	}
	
	public void display() {
		for (int i = top; i >= 0; i--) {
			System.out.print(String.format("%s ", stack[i]));
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		var stack = new StackArray(5);
		stack.display();
		
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		stack.push(50);
		
		stack.display();
		System.out.println(stack.pop());
		stack.display();
		
		stack.push(60);
		System.out.println(stack.peek(0));
		System.out.println(stack.peek(1));
		System.out.println(stack.peek(2));
		System.out.println(stack.peek(3));
		System.out.println(stack.peek(4));
		
		System.out.println("Empty: " + stack.isEmpty());
		System.out.println("Full: " + stack.isFull());
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
		System.out.println("Empty: " + stack.isEmpty());
		
		
		stack.display();
	}

}
