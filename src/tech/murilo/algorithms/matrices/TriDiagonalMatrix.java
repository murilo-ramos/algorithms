package tech.murilo.algorithms.matrices;

public class TriDiagonalMatrix {
	
	private int n;
	private int[] matrix;
	
	public TriDiagonalMatrix(int n) {
		this.n = n;
		this.matrix = new int[3 * n - 2];
	}
	
	public void set(int value, int i, int j) {
		if (i - j == 1) {
			matrix[i - 2] = value;
		} else if (i - j == 0) {
			matrix[n - 1 + i - 1] = value;
		} else if (i - j == -1) {
			matrix[2 * n - 1 + i - 1] = value;
		}
	}
	
	public int get(int i, int j) {
		if (i - j == 1) {
			return matrix[i - 2];
		} else if (i - j == 0) {
			return matrix[n - 1 + i - 1];
		} else if (i - j == -1) {
			return matrix[2 * n - 1 + i - 1];
		} else {
			return 0;
		}
	}
	
	public void display() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(String.format("%3d",  get(i, j)));
			}
			System.out.println(" ");
		}
	}
	
	public static void main(String[] args) {
		var matrix = new TriDiagonalMatrix(5);
		matrix.set(1, 1, 1);
		matrix.set(2, 2, 1);
		matrix.set(3, 1, 2);
		matrix.set(4, 2, 2);
		matrix.set(5, 3, 2);
		matrix.set(6, 2, 3);
		matrix.set(7, 3, 3);
		matrix.set(8, 4, 3);
		matrix.set(9, 3, 4);
		matrix.set(10, 4, 4);
		matrix.set(11, 5, 4);
		matrix.set(12, 4, 5);
		matrix.set(13, 5, 5);
		
		matrix.display();
		
		System.out.println();
	}

}
