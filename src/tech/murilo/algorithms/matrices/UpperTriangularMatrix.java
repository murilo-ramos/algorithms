package tech.murilo.algorithms.matrices;

public class UpperTriangularMatrix {
	
	public enum Mode {
		ROW_MAJOR,
		COLUMN_MAJOR;
	}
	
	private int n;
	private int[] matrix;
	private Mode mode;
	
	public UpperTriangularMatrix(int n, Mode mode) {
		this.n = n;
		this.matrix = new int[n * (n + 1) / 2];
		this.mode = mode;
	}
	
	public void set(int value, int i, int j) {
		if (i <= j) {
			matrix[getIndexByMode(i, j)] = value;
		}
	}
	
	public int get(int i, int j) {
		if (i > j) {
			return 0;
		}
		
		return matrix[getIndexByMode(i, j)];
	}
	
	public void display() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i <= j) {
					System.out.print(String.format("%3d",  matrix[getIndexByMode(i, j)]));
				} else {
					System.out.print("  0");
				}
			}
			System.out.println(" ");
		}
	}
	
	private int getIndexByMode(int i, int j) {
		if (mode == Mode.ROW_MAJOR) {
			return (((i - 1) * n) - (((i - 2) * (i - 1)) / 2)) + (j - i);
		} else {
			return ((j * (j - 1)) / 2) + i - 1;
		}
	}
	
	public static void main(String[] args) {
		var matrix = new UpperTriangularMatrix(5, Mode.ROW_MAJOR);
		matrix.set(1, 1, 1);
		matrix.set(2, 1, 2);
		matrix.set(3, 2, 2);
		matrix.set(4, 1, 3);
		matrix.set(5, 2, 3);
		matrix.set(6, 3, 3);
		matrix.set(7, 1, 4);
		matrix.set(8, 2, 4);
		matrix.set(9, 3, 4);
		matrix.set(10, 4, 4);
		matrix.set(11, 1, 5);
		matrix.set(12, 2, 5);
		matrix.set(13, 3, 5);
		matrix.set(14, 4, 5);
		matrix.set(15, 5, 5);
		
		matrix.display();
		
		System.out.println();
		
		matrix = new UpperTriangularMatrix(5, Mode.COLUMN_MAJOR);
		matrix.set(15, 1, 1);
		matrix.set(14, 1, 2);
		matrix.set(13, 2, 2);
		matrix.set(12, 1, 3);
		matrix.set(11, 2, 3);
		matrix.set(10, 3, 3);
		matrix.set(9, 1, 4);
		matrix.set(8, 2, 4);
		matrix.set(7, 3, 4);
		matrix.set(6, 4, 4);
		matrix.set(5, 1, 5);
		matrix.set(4, 2, 5);
		matrix.set(3, 3, 5);
		matrix.set(2, 4, 5);
		matrix.set(1, 5, 5);
		
		matrix.display();
	}

}
