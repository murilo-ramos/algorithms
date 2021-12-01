package tech.murilo.algorithms.hash;

public class HashDouble {
	
	private static final int SIZE = 10;
	private static final int PRIME = 7;
	
	private int[] hash = new int[10];
	
	public void insert(int value) {
		int key = getHashKey(value);
		
		if (hash[key] == 0) {
			hash[key] = value;
			return;
		}
		
		int i = 1;
		while (!isValidKey(getDoubleHashKey(value, i)) || hash[getDoubleHashKey(value, i)] != 0) {
			i++;
		}
		
		key = getDoubleHashKey(value, i);
		
		hash[key] = value;
	}
	
	public int search(int value) {
		int key = getHashKey(value);
		
		if (hash[key] == 0) {
			return -1;
		}
		
		int i = 0;
		while (!isValidKey(getDoubleHashKey(value, i)) || hash[getDoubleHashKey(value, i)] != value) {
			i++;
			
			if (isValidKey(getDoubleHashKey(value, i)) && hash[getDoubleHashKey(value, i)] == 0) {
				return -1;
			}
		}
		
		return getDoubleHashKey(value, i);
	}
	
	public void display() {
		for (int i = 0; i < hash.length; i++) {
			System.out.println(String.format("%s: %s", i, hash[i]));
		}
	}
	
	private static int getDoubleHashKey(int value, int i) {
		int key = getHashKey(value);
		return (getHashKey(key) + i * (PRIME - (key % PRIME))) % SIZE;
	}
	
	private static int getHashKey(int value) {
		return value % SIZE;
	}
	
	private static boolean isValidKey(int key) {
		return key >= 0 && key < SIZE;
	}
	
	public static void main(String[] args) {
		int[] values = new int[] {5, 25, 15, 35, 95};
		
		var hash = new HashDouble();
		
		for (int value : values) {
			hash.insert(value);
		}
		
		hash.display();
		
		System.out.println("Search 25 found at: " + hash.search(25));
		System.out.println("Search 35 found at: " + hash.search(35));
	}

}
