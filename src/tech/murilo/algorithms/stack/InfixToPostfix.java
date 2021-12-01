package tech.murilo.algorithms.stack;

import java.util.Stack;

public class InfixToPostfix {
	
	public static String convert(String infix) {
		var result = new StringBuilder();
		
		var stack = new Stack<Character>();
		
		int i = 0;
		while (i < infix.length()) {
			if (isOperand(infix.charAt(i))) {
				result.append(infix.charAt(i++));
			} else  {
				if (stack.isEmpty() || getPrecedence(infix.charAt(i)) > getPrecedence(stack.peek())) {
					stack.push(infix.charAt(i++));
				} else {
					result.append(stack.pop());
				}
			}
		}
		
		while (!stack.isEmpty()) {
			result.append(stack.pop());
		}
		
		return result.toString();
	}
	
	private static int getPrecedence(char c) {
		if (c == '+' || c == '-') {
			return 1;
		}
		if (c == '*' || c == '/') {
			return 2;
		}
		
		return 0;
	}
	
	private static boolean isOperand(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/') {
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println("a+b*c -> " + convert("a+b*c"));
		System.out.println("a+b*c-d/e -> " + convert("a+b*c-d/e"));
	}

}
