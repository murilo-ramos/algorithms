package tech.murilo.algorithms.arrayadt;

public class ArrayADT {
	
	public static class Array {
		private int[] array;
		private int size;
		private int length;
		
		public Array(int[] array, int size, int length) {
			this.array = new int[size];
			this.size = size;
			this.length = length;
			
			for (int i = 0; i < length; i++) {
				this.array[i] = array[i];
			}
		}
		
		public void display() {
			System.out.print("S: " + size);
			System.out.print(" ");
			System.out.print("L: " + length);
			System.out.print(" - ");
			
			for (int i = 0; i < length; i++) {
				System.out.print(array[i] + " ");
			}
			
			System.out.println();
		}
		
		public void append(int value) {
			if (length < size) {
				array[length++] = value;
			}
		}
		
		public void insert(int value, int position) {
			if (position >= 0 && position <= length) {
				for (int i = length; i > position; i--) {
					array[i] = array[i - 1];
				}
				
				array[position] = value;
				length++;
			}
		}
		
		public int delete(int position) {
			if (position >= 0 && position < length) {
				int result = array[position];
				
				for (int i = position; i < length - 1; i++) {
					array[i] = array[i + 1];
				}
				
				length--;
				
				return result;
			}
			
			return 0;
		}
		
		public int get(int position) {
			if (position >=0 && position < length) {
				return array[position];
			}
			
			return -1;
		}
		
		public void set(int value, int position) {
			if (position >=0 && position < length) {
				array[position] = value;;
			}
		}
		
		public int minimum() {
			int result = array[0];
			
			for (int i = 1; i < length; i++) {
				if (array[i] < result) {
					result = array[i]; 
				}
			}
			
			return result;
		}
		
		public int maximum() {
			int result = array[0];
			
			for (int i = 1; i < length; i++) {
				if (array[i] > result) {
					result = array[i]; 
				}
			}
			
			return result;
		}
		
		public int sum() {
			int result = 0;
			
			for (int i = 0; i < length; i++) {
				result += array[i];
			}
			
			return result;
		}
		
		public float average() {
			return (float) sum() / length;
		}
		
		public int linearSearch(int value) {
			return linearSearch(value, false, false);
		}
		
		public int linearSearch(int value, boolean transpose, boolean moveToFront) {
			for (int i = 0; i < length; i++) {
				if (array[i] == value) {
					if (transpose && i > 0) {
						swap(i, i-1);
					} else if (moveToFront) {
						swap(i, 0);
					}
					
					return i;
				}
			}
			
			return -1;
		}
		
		public int binarySearch(int key) {
			int low = 0;
			int high = length - 1;
			
			while (low <= high) {
				int mid = (low + high) / 2;
				
				if (key == array[mid]) {
					return mid;
				} else if (key < array[mid]) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			}
			
			return -1;
		}
		
		public int recursiveBinarySearch(int key) {
			return recursiveBinarySearch(key, 0, length - 1);
		}
		
		public void reverse() {
			reverse(false);
		}
		
		public void reverse(boolean inPlace) {
			if (inPlace) {
				reverseInPlace();
			} else {
				int[] reversed = new int[size];
				
				int j = 0;
				for (int i = length - 1; i >= 0; i--) {
					reversed[j++] = array[i];
				}
				
				this.array = reversed;
			}
		}
		
		public boolean isSorted() {
			for (int i = 0; i < length - 1; i++) {
				if (array[i] > array[i + 1]) {
					return false;
				}
			}
			
			
			return true;
		}
		
		public void insertSorted(int value) {
			if (length == size) {
				return;
			}
			
			int i = length;
			while (i > 0 && array[i - 1] > value) {
				array[i] = array[i - 1];
				i--;
			}
			
			array[i] = value;
			length++;
		}
		
		public void rearrage() {
			int i = 0;
			int j = length - 1;
			
			while (i < j) {
				while (array[i] < 0 && i < length) {
					i++;
				}
				while (array[j] >= 0 && j > 0) {
					j--;
				}
				
				if (i < j) {
					swap(i, j);
				}
			}
		}
		
		public Array merge(Array mergeArray) {
			int[] newArray = new int[this.size + mergeArray.size];
			
			int i = 0;
			int j = 0;
			int k = 0;
			while (i < this.length && j < mergeArray.length) {
				if (this.array[i] < mergeArray.array[j]) {
					newArray[k++] = this.array[i++];
				} else {
					newArray[k++] = mergeArray.array[j++];
				}
			}
						
			for ( ; i < this.length; i++) {
				newArray[k++] = this.array[i];
			}
			for ( ; j < mergeArray.length; j++) {
				newArray[k++] = this.array[j];
			}
			
			return new Array(newArray, this.size + mergeArray.size, this.length + mergeArray.length);
		}
		
		public Array union(Array outArray) {
			int[] newArray = new int[this.size + outArray.size];
			
			int i = 0;
			int j = 0;
			int k = 0;
			while (i < this.length && j < outArray.length) {
				if (this.array[i] < outArray.array[j]) {
					newArray[k++] = this.array[i++];
				} else if (outArray.array[j] < this.array[i]) {
					newArray[k++] = outArray.array[j++];
				} else {
					newArray[k++] = this.array[i++];
					j++;
				}
			}
						
			for ( ; i < this.length; i++) {
				newArray[k++] = this.array[i];
			}
			for ( ; j < outArray.length; j++) {
				newArray[k++] = this.array[j];
			}
			
			
			return new Array(newArray, this.size + outArray.size, k);
		}
		
		public Array intersection(Array outArray) {
			int[] newArray = new int[this.size + outArray.size];
			
			int i = 0;
			int j = 0;
			int k = 0;
			while (i < this.length && j < outArray.length) {
				if (this.array[i] < outArray.array[j]) {
					i++;
				} else if (outArray.array[j] < this.array[i]) {
					j++;
				} else {
					newArray[k++] = this.array[i++];
					j++;
				}
			}
			
			return new Array(newArray, this.size + outArray.size, k);
		}
		
		public Array difference(Array outArray) {
			int[] newArray = new int[this.size + outArray.size];
			
			int i = 0;
			int j = 0;
			int k = 0;
			while (i < this.length && j < outArray.length) {
				if (this.array[i] < outArray.array[j]) {
					newArray[k++] = this.array[i++];
				} else if (outArray.array[j] < this.array[i]) {
					j++;
				} else {
					i++;
					j++;
				}
			}
						
			for ( ; i < this.length; i++) {
				newArray[k++] = this.array[i];
			}
			
			return new Array(newArray, this.size + outArray.size, k);
		}
		
		private void reverseInPlace() {
			int i = 0;
			int j = length - 1;			
			
			while (i < j) {
				swap(i++, j--);
			}
		}
		
		private int recursiveBinarySearch(int key, int low, int high) {
			if (low > high) {
				return - 1;
			}
			
			int mid = (low + high) / 2;
			
			if (key == array[mid]) {
				return mid;
			} else if (key < array[mid]) {
				return recursiveBinarySearch(key, low, mid - 1);
			} else {
				return recursiveBinarySearch(key, mid + 1, high);
			} 
		}
		
		private void swap(int from, int to) {
			int temp = array[to];
			array[to] = array[from];
			array[from] = temp;
		}
	}
	
	public static void main(String[] args) {
		var array = new Array(new int[] {2, 6, 10, 15, 25}, 10, 5);
		var array2 = new Array(new int[] {3, 6, 7, 15, 20}, 10, 5);
		
		array.display();
		var out = array.difference(array2);
		out.display();
		
	}

}
