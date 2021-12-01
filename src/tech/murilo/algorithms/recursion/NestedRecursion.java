package tech.murilo.algorithms.recursion;

public class NestedRecursion {
	
	/**
	 * O(n) ou no pior O(2 ^n)
	 */
	public static int fun(int n) {
		if (n > 100) {
			return n - 10;
		} else {
			return fun(fun(n + 11));
		}		
	}

}
