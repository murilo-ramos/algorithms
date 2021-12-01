package tech.murilo.algorithms.sort;

public class BubbleSort {
	
	private static void sort(int values[]){
	    for (int i = 0; i<values.length - 1; i++){
	        for (int j = 0; j < values.length - 1 - i; j++){
	            if (values[j] > values[j+1]){
	                SortUtil.swap(values, j, j + 1);
	                
	            }
	        }
	    }
	}
	
	private static void sortWithFlag(int values[]){
	    int flag = 0;
	    for (int i = 0; i<values.length - 1; i++){
	        for (int j = 0; j < values.length - 1 - i; j++){
	            if (values[j] > values[j+1]){
	                SortUtil.swap(values, j, j + 1);
	                flag = 1;
	            }
	        }
	        
	        if (flag == 0){
	            return;
	        }
	    }
	}
	
	public static void main(String[] args) {
		var values = new int[] {3, 7, 9, 10, 6, 5, 12, 4, 11, 2};
		
		SortUtil.print(values);
		sort(values);
		SortUtil.print(values);
		
		System.out.println();
		
		values = new int[] {3, 7, 9, 10};
		
		SortUtil.print(values);
		sortWithFlag(values);
		SortUtil.print(values);
		
	}
}
