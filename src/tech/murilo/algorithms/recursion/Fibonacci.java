package tech.murilo.algorithms.recursion;

public class Fibonacci {
	
	private static int memory[]; //iniciar com tamanho de N e -1
	
	/**
	 * O(2^n)
	 */
	public static int fibo(int n) {
		if (n <= 1) {
			return n;
		}
		
		return fibo(n - 2) + fibo(n - 1);
	}
	
	/**
	 * O(n)
	 */
	public static int memoFibo(int n) {
		if (n <= 1) {
			memory[n] = n;
			return memory[n];
		} else {
			if (memory[n - 2] == -1) {
				memory[n - 2] = memoFibo(n - 2);
			}
			if (memory[n - 1] == -1) {
				memory[n - 1] = memoFibo(n - 1);
			}
			
			return memory[n - 2] + memory[n - 1];
		}
		
	}

}
