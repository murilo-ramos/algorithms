package tech.murilo.algorithms.stack;

import java.util.Stack;

public class StudentChallengeInfixToPostfix {	
	 
	public static String convert(String infix){
	    var result = new StringBuilder();
	 
	    var stack = new Stack<Character>();
	 
	    int i = 0;
	    while (i < infix.length()){
	        if (isOperand(infix.charAt(i))){
	            result.append(infix.charAt(i++));
	        } else {
	            if (stack.isEmpty() || outPrecedence(infix.charAt(i)) > inPrecedence(stack.peek())){
	                stack.push(infix.charAt(i++));
	            } else if (outPrecedence(infix.charAt(i)) == inPrecedence(stack.peek())){
	                stack.pop();
	            }
	            else {
	                result.append(stack.pop());
	            }
	        }
	    }
	 
	    while (!stack.isEmpty() && stack.peek() != ')'){
	    	result.append(stack.pop());
	    }   
	 
	    return result.toString();
	}
	
	private static boolean isOperand(char x) {
	    if (x == '+' || x == '-' || x == '*' || x == '/' || x == '^' || x == '(' || x == ')') {
	        return false;
	    }
	    
	    return true;
	}
	 
	private static int outPrecedence(char x) {
	    if (x == '+' || x == '-') {
	        return 1;
	    } else if (x == '*' || x == '/') {
	        return 3;
	    } else if (x == '^') {
	        return 6;
	    } else if (x == '(') {
	        return 7;
	    } else if (x == ')') {
	        return 0;
	    }
	    
	    return -1;
	}
	 
	private static int inPrecedence(char x) {
	    if (x == '+' || x == '-') {
	        return 2;
	    } else if (x == '*' || x == '/') {
	        return 4;
	    } else if (x == '^') {
	        return 5;
	    } else if (x == '(') {
	        return 0;
	    }
	    
	    return -1;
	}
	
	public static void main(String[] args) {
		System.out.println("((a+b)*c)-d^e^f -> " + convert("((a+b)*c)-d^e^f"));
	}

}
