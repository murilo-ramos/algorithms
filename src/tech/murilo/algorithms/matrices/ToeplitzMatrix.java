package tech.murilo.algorithms.matrices;

public class ToeplitzMatrix {
	
	private int n;
	private int[] matrix;
	
	public ToeplitzMatrix(int n) {
		this.n = n;
		this.matrix = new int[n + n - 1];
	}
	
	public void set(int value, int i, int j) {
		if (i <=j ) {
			matrix[j - i] = value;
		} else {
			matrix[n + i -j -1] = value;
		}	
	}
	
	public int get(int i, int j) {
		if (i <=j ) {
			return matrix[j - i];
		} else {
			return matrix[n + i -j -1];
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
		var matrix = new ToeplitzMatrix(5);
		matrix.set(1, 5, 1);
		matrix.set(2, 4, 1);
		matrix.set(3, 3, 1);
		matrix.set(4, 2, 1);
		matrix.set(5, 1, 1);
		matrix.set(6, 1, 2);
		matrix.set(7, 1, 3);
		matrix.set(8, 1, 4);
		matrix.set(9, 1, 5);		
		matrix.display();
		
		System.out.println();
	}

}
