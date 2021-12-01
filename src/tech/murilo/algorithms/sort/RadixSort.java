package tech.murilo.algorithms.sort;


import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

public class RadixSort {
	
	@SuppressWarnings("unchecked")
	private static void sort(int[] values) {
		int max = IntStream.of(values).max().getAsInt();
		int stepsCount = String.valueOf(max).length();
		
		
		int n = 1;
		for (int i = 0; i < stepsCount; i++) {
			Queue<Integer>[] set = new Queue[10];
			
			for (int value : values) {
				int pos = (value/n) % 10;
				
				if (set[pos] == null) {
					set[pos] = new LinkedList<Integer>();
				}
				set[pos].add(value);
			}
			
			int x = 0;
			for (int j = 0; j < set.length; j++) {
				if (set[j] != null) {
					while (!set[j].isEmpty()) {
						values[x++] = set[j].poll();
					}
				}
			}
			
			n *= 10;
		}
	}
	
	public static void main(String[] args) {
		var values = new int[] {11, 13, 7, 12, 16, 9, 3, 24, 5, 10, 1, 3, 45, 45, 233, 432, 123, 89, 678};
		
		SortUtil.print(values);
		sort(values);
		SortUtil.print(values);
	}
}
