package tech.murilo.algorithms.sort;

public class MergeSort {
	
	private static void sortInteractive(int[] values) {
		int p;
		
		for (p = 2; p <= values.length; p *= 2) {
			for (int i = 0; i + p - 1 < values.length; i = i + p) {
				int low = i;
				int high = i + p - 1;
				int mid = (low + high) / 2;
				merge(values, low, mid, high);
			}
		}
		
		if (p / 2 < values.length) {
			merge(values, 0, p / 2 -1, values.length - 1);
		}
	}
	
	private static void sortRecursive(int[] values) {
		sortRecursive(values, 0, values.length - 1);
	}
	
	private static void sortRecursive(int[] values, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			sortRecursive(values, low, mid);
			sortRecursive(values, mid + 1, high);
			merge(values, low, mid, high);
		}
	}
	
	private static void merge(int[] values, int low, int mid, int high) {
		int i = low;
		int j = mid + 1;
		int k = low;
		
		int[] newValues = new int[high + 1];
		
		while (i <= mid && j <= high) {
			if (values[i] < values[j]) {
				newValues[k++] = values[i++];
			} else {
				newValues[k++] = values[j++];
			}
		}
		
		for (; i <= mid; i++) {
			newValues[k++] = values[i];
		}
		for (; j <= high; j++) {
			newValues[k++] = values[j];
		}
		
		for (i = low; i <= high; i++) {
			values[i] = newValues[i];
		}
	}
	
	public static void main(String[] args) {
		var values = new int[] {2, 5, 8, 12, 3, 6, 7, 10};
		
		SortUtil.print(values);
		sortInteractive(values);
		SortUtil.print(values);
		
		values = new int[] {11, 13, 7, 12, 16, 9, 24, 5, 10, 1, 3};
		
		SortUtil.print(values);
		sortInteractive(values);
		SortUtil.print(values);
		
		System.out.println("-");
		
		values = new int[] {2, 5, 8, 12, 3, 6, 7, 10};
		
		SortUtil.print(values);
		sortRecursive(values);
		SortUtil.print(values);
		
		values = new int[] {11, 13, 7, 12, 16, 9, 24, 5, 10, 1, 3};
		
		SortUtil.print(values);
		sortRecursive(values);
		SortUtil.print(values);
	}

}
