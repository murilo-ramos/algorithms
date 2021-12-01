package tech.murilo.algorithms.recursion;

public class HanoiTower {
	
	/**
	 * O(2^n)
	 */
	public static void hanoi(int n, char a, char b, char c) {
		if (n > 0) {
			hanoi(n - 1, a, c, b);
			System.out.println("Move from " + a + " to " + c);
			hanoi(n - 1, b, a, c);
		}
	}
	
	public static void main(String[] args) {
		hanoi(3, 'a', 'b', 'c');
	}

}
