package tech.murilo.algorithms.sort;

public class QuickSort {
	
	private static void sort(int[] values) {
		sort(values, 0, values.length - 1);
	}
	
	private static void sort(int[] values, int low, int high) {
		if (low < high) {
			int p = partition(values, low, high);
			sort(values, low, p - 1);
			sort(values, p + 1, high);
		}
	}
	
	private static int partition(int[] values, int low, int high) {
		int pivot = values[low];
		
		int i = low + 1;
		int j = high;
		
		while (true) {
			while (i <= j && values[i] <= pivot) {
				i++;
			}
			while(values[j] >= pivot && j >= i) {
				j--;
			}
			
			if (j < i) {
				break;
			} else {
				SortUtil.swap(values, i, j);
			}
		}
		
		SortUtil.swap(values, low, j);
		
		return j;
	}
	
	public static void main(String[] args) {
		var values = new int[] {11, 13, 7, 12, 16, 9, 24, 5, 10, 3};
		
		SortUtil.print(values);
		sort(values);
		SortUtil.print(values);
	}

}
