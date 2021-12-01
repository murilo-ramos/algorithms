package tech.murilo.algorithms.sort;

public class SortUtil {
	
	public static void swap(int[] array, int i, int j){
	    int temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
	}
	
	public static void print(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(String.format("%d ", array[i]));
		}
		
		System.out.println();
	}

}
