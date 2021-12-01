package tech.murilo.algorithms.sparsematrixpolynomial;

public class SparseMatrix {
	
	public static class Element {
		final int i;
		final int j;
		final int x;
		
		public Element(int i, int j, int x) {
			this.i = i;
			this.j = j;
			this.x = x;
		}		
	}
	
	private final int m;
	private final int n;
	private final int num;
	private final Element[] elements;
	
	public SparseMatrix(int m, int n, int num) {
		this.m = m;
		this.n = n;
		this.num = num;
		
		this.elements = new Element[this.num];
	}
	
	public SparseMatrix(int m, int n, int num, Element[] elements) {
		this.m = m;
		this.n = n;
		this.num = num;
		this.elements = elements;
	}
	
	public Element[] getElements() {
		return elements;
	}
	
	public SparseMatrix sum(SparseMatrix s) {
		var sumElements = new Element[this.num + s.num];
		
		int i, j, k;
		i = j = k = 0;
		
		while (i < num && j < s.num) {
			if (elements[i].i < s.elements[j].i) {
				sumElements[k++] = elements[i++];
			} else if (elements[i].i > s.elements[j].i) {
				sumElements[k++] = elements[j++];
			} else {
				if (elements[i].j < s.elements[j].j) {
					sumElements[k++] = elements[i++];
				} else if (elements[i].j > s.elements[j].j) {
					sumElements[k++] = elements[j++];
				} else {
					var element = new Element(elements[i].i, elements[i].j, elements[i++].x + s.elements[j++].x);
					sumElements[k++] = element;
				}
			}
		}
		
		for (; i < num; i++) {
			sumElements[k++] = elements[i];
		}
		
		for (; j < s.num; j++) {
			sumElements[k++] = s.elements[j];
		}
		
		return new SparseMatrix(this.m, this.n, k, sumElements);
	}
	
	public void display() {
		int k = 0;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0 ; j < n; j++) {
				if (k < num && i == elements[k].i && j == elements[k].j) {
					System.out.print(String.format("%3d", elements[k++].x));
				} else {
					System.out.print(String.format("%3d", 0));
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		var matrix01 = new SparseMatrix(5, 5, 5);
		matrix01.getElements()[0] = new Element(0, 0, 1);
		matrix01.getElements()[1] = new Element(1, 1, 1);
		matrix01.getElements()[2] = new Element(2, 2, 1);
		matrix01.getElements()[3] = new Element(3, 3, 1);
		matrix01.getElements()[4] = new Element(4, 4, 1);
		
		var matrix02 = new SparseMatrix(5, 5, 5);
		matrix02.getElements()[0] = new Element(0, 0, 5);
		matrix02.getElements()[1] = new Element(1, 1, 5);
		matrix02.getElements()[2] = new Element(2, 2, 5);
		matrix02.getElements()[3] = new Element(3, 3, 5);
		matrix02.getElements()[4] = new Element(4, 4, 5);
		
		var matrix03 = matrix01.sum(matrix02);
		
		System.out.println("First matrix");
		matrix01.display();
		
		System.out.println("Second matrix");
		matrix02.display();
		
		System.out.println("Sum matrix");
		matrix03.display();
	}	
}
