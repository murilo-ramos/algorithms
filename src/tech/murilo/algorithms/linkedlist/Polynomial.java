package tech.murilo.algorithms.linkedlist;

public class Polynomial {
	
	private static class Node {
		int coefficient;
		int expoent;
		Node next;
		
		public Node(int coefficient, int expoent) {
			this.coefficient = coefficient;
			this.expoent = expoent;
		}
	}
	
	private Node polynomial;
	
	public Polynomial() {
		
	}
	
	public void add(int coefficient, int expoent) {
		if (polynomial == null) {
			polynomial = new Node(coefficient, expoent);
			return;
		}
		
		Node node = polynomial;
		
		while (node.next != null) {
			node = node.next;
		}
		
		node.next = new Node(coefficient, expoent);
	}
	
	public int eval(int value) {
		int result = 0;
		
		Node node = polynomial;
		
		while (node != null) {
			result += node.coefficient * Math.pow(value, node.expoent);
			node = node.next;
		}
		
		return result;
	}
	
	public void display() {
		Node node = polynomial;
		
		while (node != null) {
			System.out.print(String.format("%dx%d" + ((node.next != null) ? "+" : ""), node.coefficient, node.expoent));
			node = node.next;
		}
	}
	
	public static void main(String[] args) {
		var poly = new Polynomial();
		
		poly.add(1, 3);
		poly.add(1, 2);
		poly.add(1, 1);
		
		System.out.println("eval of 5: " + poly.eval(5));
		
		poly.display();
	}
}
