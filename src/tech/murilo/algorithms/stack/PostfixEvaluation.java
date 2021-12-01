package tech.murilo.algorithms.stack;

import java.util.Stack;

public class PostfixEvaluation {
	
	public static int eval(String postfix) {
		var stack = new Stack<Integer>();
		
		for (var c : postfix.toCharArray()) {
			if (isOperand(c)) {
				stack.push(Character.getNumericValue(c));
			} else {
				int b = stack.pop();
				int a = stack.pop();
				int result = 0;
				
				switch (c) {
				case '+':
					result = a + b;
					break;
				case '-':
					result = a - b;
					break;
				case '*':
					result = a * b;
					break;
				case '/':
					result = a / b;
					break;
				}
				
				stack.push(result);
			}
		}
		
		return stack.peek();
	}
	
	private static boolean isOperand(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/') {
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println("234*+82/- -> " + eval("234*+82/-"));
	}

}
