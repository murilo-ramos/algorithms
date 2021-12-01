package tech.murilo.algorithms.linkedlist;

public class SparseMatrix {
	
	private static class Node {
		int column;
		int data;
		Node next;
		
		public Node(int column, int data) {
			this.column = column;
			this.data = data;
		}
	}
	
	private int m;
	private int n;
	private Node[] matrix;
	
	public SparseMatrix(int m, int n) {
		this.m = m;
		this.n = n;
		this.matrix = new Node[m];
	}
	
	public void insert(int row, int column, int data) {
		if (row < 0 || row >= m || column < 0 || column >= n) {
			return;
		}
		
		if (matrix[row] == null) {
			matrix[row] = new Node(column, data);
			return;
		}
		
		Node node = matrix[row];
		
		if (column == node.column) {
			node.data = data;
		} else if (column < node.column) {
			var newNode = new Node(column, data);
			newNode.next = node;
			matrix[row] = newNode;
		} else {
			while (node.next != null) {
				if (node.next.column == column) {
					node.next.data = data;
					return;
				} else if (node.next.column < column) {
					node = node.next;
				} else {
					var newNode = new Node(column, data);
					newNode.next = node.next;
					node.next = newNode;
					return;
				}
			}
			
			node.next = new Node(column, data);
		}
	}
	
	public void display() {
		for (int i = 0; i < m; i++) {
			Node node = matrix[i];
			
			for (int j = 0 ; j < n; j++) {
				if (node != null && j == node.column) {
					System.out.print(String.format("%3d", node.data));
					node = node.next;
				} else {
					System.out.print(String.format("%3d", 0));
				}
			}
			
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		var matrix = new SparseMatrix(5, 5);
		
		matrix.insert(0, 0, 1);
		matrix.insert(0, 1, 2);
		matrix.insert(0, 2, 3);
		matrix.insert(0, 3, 4);
		matrix.insert(0, 4, 5);
		matrix.insert(1, 0, 6);
		matrix.insert(1, 1, 7);
		matrix.insert(1, 2, 8);
		matrix.insert(1, 3, 9);
		matrix.insert(1, 4, 10);
		matrix.insert(2, 0, 11);
		matrix.insert(2, 1, 12);
		matrix.insert(2, 2, 13);
		matrix.insert(2, 3, 14);
		matrix.insert(2, 4, 15);
		matrix.insert(3, 0, 16);
		matrix.insert(3, 1, 17);
		matrix.insert(3, 2, 18);
		matrix.insert(3, 3, 19);
		matrix.insert(3, 4, 20);
		matrix.insert(4, 0, 21);
		matrix.insert(4, 1, 22);
		matrix.insert(4, 2, 23);
		matrix.insert(4, 3, 24);
		matrix.insert(4, 4, 55);
		
		
		matrix.display();
		
	}

}
