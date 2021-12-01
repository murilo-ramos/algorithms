package tech.murilo.algorithms.recursion;

public class TailHeadRecursion {
	
	/**
	 * O(n)
	 * @param n
	 */
	public static void tail(int n) {
		if (n > 0) {
			System.out.println(n);
			tail(n -1);
		}
	}
	
	/**
	 * O(n)
	 * @param n
	 */
	public static void head(int n) {
		if (n > 0) {
			tail(n -1);
			System.out.println(n);
		}
	}
	
	

}
