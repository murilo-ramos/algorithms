package tech.murilo.algorithms.sort;


import java.util.stream.IntStream;

public class CountSort {
	
	private static void sort(int[] values) {
		int max = IntStream.of(values).max().getAsInt();
		
		int[] set = new int[max + 1];
		
		for (int value : values) {
			set[value] = set[value] + 1; 
		}
		
		int x = 0;
		for (int i = 0; i < set.length; i++) {
			if (set[i] > 0) {
				for (int j = 0; j < set[i]; j++) {
					values[x++] = i;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		var values = new int[] {11, 13, 7, 12, 16, 9, 3, 24, 5, 10, 1, 3};
		
		SortUtil.print(values);
		sort(values);
		SortUtil.print(values);
	}
}
