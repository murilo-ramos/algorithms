package tech.murilo.algorithms.matrices;

public class SymmetricMatrix {
	
	private int n;
	private int[] matrix;
	
	
	public SymmetricMatrix(int n) {
		this.n = n;
		this.matrix = new int[n * (n + 1) / 2];
	}
	
	public void set(int value, int i, int j) {
		if (i >= j) {
			matrix[getIndex(i, j)] = value;
		}
	}
	
	public int get(int i, int j) {
		if (i < j) {
			return matrix[getIndex(j, i)];
		}
		
		return matrix[getIndex(i, j)];
	}
	
	public void display() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i >= j) {
					System.out.print(String.format("%3d",  matrix[getIndex(i, j)]));
				} else {
					System.out.print(String.format("%3d",  matrix[getIndex(j, i)]));
				}
			}
			System.out.println(" ");
		}
	}
	
	private int getIndex(int i, int j) {
		return i * (i - 1) / 2 + j - 1;
	}
	
	public static void main(String[] args) {
		var matrix = new SymmetricMatrix(5);
		matrix.set(2, 1, 1);
		matrix.set(2, 2, 1);
		matrix.set(2, 3, 1);
		matrix.set(2, 4, 1);
		matrix.set(2, 5, 1);
		matrix.set(3, 2, 2);
		matrix.set(3, 3, 2);
		matrix.set(3, 4, 2);
		matrix.set(3, 5, 2);
		matrix.set(4, 3, 3);
		matrix.set(4, 4, 3);
		matrix.set(4, 5, 3);
		matrix.set(5, 4, 4);
		matrix.set(5, 5, 4);
		matrix.set(6, 5, 5);
		
		matrix.display();
		
		System.out.println();
	}

}
