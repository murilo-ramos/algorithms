package tech.murilo.algorithms.stack;

public class ParenthesisMatching {
	
	private static class Node {
		char data;
		Node next;
		
		public Node(char data) {
			this.data = data;
		}
		
		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}
	
	private static class StackLinkedList {
		private Node top;
		
		public StackLinkedList() {
		}
		
		public void push(char data) {
			var node = new Node(data);
			
			// java will throw exception if memory limit is reached
			
			if (top == null) {
				top = node;
			} else {
				node.next = top;
				top = node;
			}
		}
		
		public char pop() {
			if (top == null) {
				return ' ';
			}
			
			var node = top;
			top = top.next;
			
			return node.data;
		}
		
		public boolean isEmpty() {
			return top == null;
		}
		
		
	}
	
	private static boolean isBalancedWithParenthesis(String expression) {
		var stack = new StackLinkedList();
		
		for (char c : expression.toCharArray()) {
			if (c == '(') {
				stack.push(c);
			}
			if (c == ')') {
				if (stack.isEmpty()) {
					return false;
				}
				stack.pop();
			}
		}
		
		return stack.isEmpty();
	}
	
	private static boolean isBalanced(String expression) {
		var stack = new StackLinkedList();
		
		for (char c : expression.toCharArray()) {
			if (c == '{' || c == '[' || c == '(') {
				stack.push(c);
			}
			if (c == '}') {
				if (stack.isEmpty() || stack.pop() != '{') {
					return false;
				}
			}
			if (c == ']') {
				if (stack.isEmpty() || stack.pop() != '[') {
					return false;
				}
			}
			if (c == ')') {
				if (stack.isEmpty() || stack.pop() != '(') {
					return false;
				}
			}
		}
		
		return stack.isEmpty();
	}
	
	
	public static void main(String[] args) {
		System.out.println("((a+b) + (t*c)): " + isBalancedWithParenthesis("((a+b) + (t*c))"));
		System.out.println("((a+b) + (t*c))): " + isBalancedWithParenthesis("((a+b) + (t*c)))"));
		System.out.println("((a+b) + (t*c): " + isBalancedWithParenthesis("((a+b) + (t*c)"));
		
		System.out.println("{(a+b) + [a * (t*c)]}: " + isBalanced("{(a+b) + [a * (t*c)]}"));
		System.out.println("{(a+b) + [a * (t*c)]: " + isBalanced("{(a+b) + [a * (t*c)]"));
		System.out.println("{(a+b) + [a * (t*c]]}: " + isBalanced("{(a+b) + [a * (t*c]]}"));
	}
}
