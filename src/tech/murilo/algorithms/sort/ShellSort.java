package tech.murilo.algorithms.sort;

public class ShellSort {
	
	private static void sort(int[] values) {
		for (int gap = values.length / 2; gap >= 1; gap /= 2) {
			for (int i = gap; i < values.length; i++) {
				int temp = values[i];
				int j = i - gap;
				
				while (j >= 0 && values[j] > temp) {
					values[j + gap] = values[j];
					j =  j - gap;
				}
				values[j  + gap] = temp;
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
