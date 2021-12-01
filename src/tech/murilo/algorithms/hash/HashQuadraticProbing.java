package tech.murilo.algorithms.hash;

public class HashQuadraticProbing {
	
	private static final int SIZE = 10;
	
	private int[] hash = new int[10];
	
	public void insert(int value) {
		int key = getHashKey(value);
		
		if (hash[key] == 0) {
			hash[key] = value;
			return;
		}
		
		int i = 1;
		while (!isValidKey(getQuadraticProbingKey(value, i)) || hash[getQuadraticProbingKey(value, i)] != 0) {
			i++;
		}
		
		key = getQuadraticProbingKey(value, i);
		
		hash[key] = value;
	}
	
	public int search(int value) {
		int key = getHashKey(value);
		
		if (hash[key] == 0) {
			return -1;
		}
		
		int i = 0;
		while (!isValidKey(getQuadraticProbingKey(value, i)) || hash[getQuadraticProbingKey(value, i)] != value) {
			i++;
			
			if (isValidKey(getQuadraticProbingKey(value, i)) && hash[getQuadraticProbingKey(value, i)] == 0) {
				return -1;
			}
		}
		
		return getQuadraticProbingKey(value, i);
	}
	
	public void display() {
		for (int i = 0; i < hash.length; i++) {
			System.out.println(String.format("%s: %s", i, hash[i]));
		}
	}
	
	private static int getQuadraticProbingKey(int value, int i) {
		int key = getHashKey(value);
		return (key + i * i) % SIZE;
	}
	
	private static int getHashKey(int value) {
		return value % SIZE;
	}
	
	private static boolean isValidKey(int key) {
		return key >= 0 && key < SIZE;
	}
	
	public static void main(String[] args) {
		int[] values = new int[] {26, 30, 45, 23, 25, 43, 74, 19, 29};
		
		var hash = new HashQuadraticProbing();
		
		for (int value : values) {
			hash.insert(value);
		}
		
		hash.display();
		
		System.out.println("Search 25 found at: " + hash.search(25));
		System.out.println("Search 35 found at: " + hash.search(35));
		
		
	}

}
