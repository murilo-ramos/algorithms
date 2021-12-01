package tech.murilo.algorithms.sort;


import java.util.Stack;
import java.util.stream.IntStream;

public class BinBucketSort {
	
	private static void sort(int[] values) {
		int max = IntStream.of(values).max().getAsInt();
		
		@SuppressWarnings("unchecked") // :(
		Stack<Integer>[] set = new Stack[max + 1]; //lecture says to use queue, but there's no difference
		
		for (int value : values) {
			if (set[value] == null) {
				set[value] = new Stack<Integer>();
			}
			set[value].push(value);
		}
		
		int x = 0;
		for (int i = 0; i < set.length; i++) {
			if (set[i] != null) {
				while (!set[i].isEmpty()) {
					values[x++] = set[i].pop();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		var values = new int[] {11, 13, 7, 12, 16, 9, 3, 24, 5, 10, 1, 3, 45, 45};
		
		SortUtil.print(values);
		sort(values);
		SortUtil.print(values);
	}
}
