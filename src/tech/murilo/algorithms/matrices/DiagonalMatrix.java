package tech.murilo.algorithms.matrices;

public class DiagonalMatrix {
	
	private int n;
	private int[] matrix;
	
	public DiagonalMatrix(int n) {
		this.n = n;
		this.matrix = new int[n];
	}
	
	public void set(int value, int i, int j) {
		if (i == j) {
			matrix[i - 1] = value;
		}
	}
	
	public int get(int i, int j) {
		if (i != j) {
			return 0;
		}
		
		return matrix[i - 1];
	}
	
	public void display() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					System.out.print(matrix[i] + "  ");
				} else {
					System.out.print("0  ");
				}
			}
			System.out.println(" ");
		}
	}
	
	public static void main(String[] args) {
		var matrix = new DiagonalMatrix(5);
		matrix.set(1, 1, 1);
		matrix.set(2, 2, 2);
		matrix.set(3, 3, 3);
		matrix.set(4, 4, 4);
		matrix.set(15, 5, 5);
		matrix.display();
		System.out.println(matrix.get(2, 3));
		System.out.println(matrix.get(2, 2));
	}

}
