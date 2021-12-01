package tech.murilo.algorithms.recursion;

public class Exercise {
	
	public static int fun4(int n) {
		int x = 1, k; 

		if (n == 1) {
			return x; 
		}

		for (k=1; k<n; ++k) {
			x= x + fun4(k) * fun4(n - k); 
		}

		return x; 

	}
	
	public static void main(String[] args) {
		System.out.println(fun4(6));
	}

}
