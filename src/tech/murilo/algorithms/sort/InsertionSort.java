package tech.murilo.algorithms.sort;

public class InsertionSort {
	
	private static void sort(int values[]){
	    for (int i = 1; i < values.length; i++){
	        int j = i - 1;
	        int x = values[i];
	        
	        while (j > -1 && values[j] > x){
	        	values[j + 1] = values[j];
	            j--;
	        }
	        
	        values[j + 1] = x;
	    }
	}
	
	public static void main(String[] args) {
		var values = new int[] {19, 17, 15, 13, 11, 9, 7, 5, 3, 1};
		
		SortUtil.print(values);
		sort(values);
		SortUtil.print(values);
	}

}
