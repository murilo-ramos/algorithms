package tech.murilo.algorithms.arrayadt;

public class StudentChallenge {
	
	public static void findMissingElementSortedArray() {
		int[] array = new int[] {1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12};
		
		int sum = 0;
		
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		
		int expectedSum = (array[array.length - 1] * (array[array.length - 1] + 1)) / 2;
		
		int missingElement = expectedSum - sum;
		
		System.out.println("Missing element: " + missingElement);
	}
	
	public static void findMissingElementSortedArrayNotStartingFromOne() {
		int[] array = new int[] {6, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17};
		
		int diff = array[0] - 0;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] - i != diff) {
				int missingElement = i + diff;
				System.out.println("Missing element: " + missingElement);
				break;
			}
		}		
	}
	
	public static void findMissingElementsSortedArrayNotStartingFromOne() {
		int[] array = new int[] {6, 7, 8, 9, 11, 12, 15, 16, 17, 18, 19};
		
		int diff = array[0] - 0;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] - i != diff) {
				while(diff < array[i] - i) {
					int missingElement = i + diff;
					System.out.println("Missing elements: " + missingElement);
					diff++;
				}
			}
		}		
	}
	
	public static void findMissingElementsSortedArrayNotStartingFromOneWithHash() {
		int[] array = new int[] {3, 7, 4, 9, 12, 6, 1, 11, 2, 10};
		
		int l = array[0]; 
		int h = array[0];
		
		for (int i = 1; i < array.length; i++) {
			if (array[i] < l) {
				l = array[i];
			} else if (array[i] > h) {
				h = array[i];
			}
		}
		
		int[] hash = new int[h + 1];
		
		for (int i : array) {
			hash[i]++;
		}
		
		for (int i = l; i <=h; i++) {
			if (hash[i] == 0) {
				System.out.println("Missing elements 2: " + i);
			}
		}
	}
	
	public static void findDuplicatesSortedArray() {
		int[] array = new int[] {3, 6, 8, 8, 10, 12, 15, 15, 15, 20};
		
		int lastDuplicate = 0;
		
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] == array[i + 1] && array[i] != lastDuplicate) {				
				lastDuplicate = array[i];  
				System.out.println("Duplicates: " + lastDuplicate);
			}
		}
	}
	
	public static void findDuplicatesSortedArrayAndCount() {
		int[] array = new int[] {3, 6, 8, 8, 10, 12, 15, 15, 15, 20};
		
		
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] == array[i + 1]) {
				int j = i + 1;
				
				while(array[j] == array[i]) {
					j++;
				}
				
				System.out.println("Duplicates: " + array[i] + " Count: " + (j - i));
				
				i = j - 1;
			}
		}
	}
	
	public static void findDuplicatesSortedArrayAndCountWithHash() {
		int[] array = new int[] {3, 6, 8, 8, 10, 12, 15, 15, 15, 20};
		
		int[] hash = new int[20 + 1]; //should have written a for loop here
				
		for (int i = 0; i < array.length - 1; i++) {
			hash[array[i]]++;
		}
		
		for (int i = 0; i < hash.length; i++) {
			if (hash[i] > 1) {
				System.out.println("Duplicates: " + i + " Count: " + hash[i]);
			}
		}
	}
	
	public static void findDuplicatesUnsortedArrayAndCount() {
		int[] array = new int[] {8, 3, 6, 4, 6, 5, 6, 8, 2, 7};
		
		for (int i = 0; i < array.length - 1; i++) {
			int count = 1;
			
			if (array[i] != -1) {
				for (int j = i + 1; j  < array.length; j++) {
					if (array[i] == array[j]) {
						count++;
						array[j]  = -1;
					}
				}
			}
			
			if (count > 1)  {
				System.out.println("Duplicates: " + array[i] + " Count: " + count);
			}
		}
	}
	
	public static void findDuplicatesUnsortedArrayAndCountWithHash() {
		int[] array = new int[] {8, 3, 6, 4, 6, 5, 6, 8, 2, 7};
		
		int[] hash = new int[8 + 1]; //should have written a for loop here
		
		for (int i = 0; i < array.length; i++) {
			hash[array[i]]++;
		}
		
		for (int i = 0; i < hash.length; i++) {
			if (hash[i] > 1) {
				System.out.println("Duplicates: " + i + " Count: " + hash[i]);
			}
		}
	}
	
	public static void findPairWithSumK() {
		int[] array = new int[] {6, 3, 8, 10, 16, 7, 5, 2, 9, 14};
		int k = 10;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] + array[j] == k) {
					System.out.println("Number with sum K " + array[i] + ", " + array[j]);
				}
			}
		}
	}
	
	public static void findPairWithSumKWithHash() {
		int[] array = new int[] {6, 3, 8, 10, 16, 7, 5, 2, 9, 14};
		int k = 10;
		
		int[] hash = new int[16 + 1]; //should have written a for loop here
		
		for (int i = 0; i < array.length; i++) {
			int searchValue = k - array[i];
			
			if (searchValue >= 0 && hash[searchValue] != 0) {
				System.out.println("Number with sum K " + array[i] + ", " + searchValue);
			}
			
			hash[array[i]]++;
		}
	}
	
	public static void findPairWithSumKSortedArray() {
		int[] array = new int[] {1, 3, 4, 5, 6, 8, 9, 10, 12, 14};
		int k = 10;
		
		int i = 0;
		int j = array.length - 1;
		
		while (i < j) {
			if (array[i] + array[j] == k) {
				System.out.println("Number with sum K " + array[i] + ", " + array[j]);
				i++;
				j--;
			} else if(array[i] + array[j] < k) {
				i++;
			} else {
				j--;
			}
		}
	}
	
	public static void findMaxAndMinInSingleScan() {
		int[] array = new int[] {5, 8, 3, 9, 6, 2, 10, 7, -1, 4};
		
		int min = array[0];
		int max = array[0];
		
		for (int i = 1; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			} else if (array[i] > max) {
				max = array[i];
			}
		}
		
		System.out.println("Min: " + min + " Max:" + max);
	}
	
	public static void main(String[] args) {
		findMissingElementSortedArray();
		findMissingElementSortedArrayNotStartingFromOne();
		findMissingElementsSortedArrayNotStartingFromOne();
		findMissingElementsSortedArrayNotStartingFromOneWithHash();
		findDuplicatesSortedArray();
		findDuplicatesSortedArrayAndCount();
		findDuplicatesSortedArrayAndCountWithHash();
		findDuplicatesUnsortedArrayAndCount();
		findDuplicatesUnsortedArrayAndCountWithHash();
		findPairWithSumK();
		findPairWithSumKWithHash();
		findPairWithSumKSortedArray();
		findMaxAndMinInSingleScan();
	}

}
