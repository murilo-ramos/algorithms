package tech.murilo.algorithms.sort;

public class SelectionSort {
	
	private static void sort(int values[]){
	    for (int i = 0; i < values.length - 1; i++){
	        int k = i;
	        
	        for (int j = i; j < values.length; j++){
	            if (values[j] < values[k]) {
	                k = j;
	            }
	        }
	        SortUtil.swap(values, i, k);
	    }
	}
	
	public static void main(String[] args) {
		var values = new int[] {3, 7, 9, 10, 6, 5, 12, 4, 11, 2};
		
		SortUtil.print(values);
		sort(values);
		SortUtil.print(values);
		
		System.out.println();
	}
}
