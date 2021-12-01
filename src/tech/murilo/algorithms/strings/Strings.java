package tech.murilo.algorithms.strings;

public class Strings {
	
	public static void countSpace() {
		String word = "How are you";
		
		int count = 0;
		
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == ' ' && word.charAt(i - 1) != ' ') {
				count++;
			}
		}
		
		System.out.println("Spaces count: " + count);
	}
	
	public static void validate() {
		String word = "Ani?321"; //Java Strings are UTF-16, but it works
				
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) < 33 || word.charAt(i) > 126) {
				System.out.println("Not valid string");
				return;
			}
		}
		
		System.out.println("Valid string");
	}
	
	public static void reverseWithAdditionalArray() {
		String word = "python";
		
		char[] reversed = new char[word.length()];
		
		int i = word.length() - 1;
		int j = 0;
				
		for (j = 0; i >=0; i--, j++) {
			reversed[j] = word.charAt(i);
		}
		
		System.out.println("Reversed: " + new String(reversed));
	}
	
	public static void reverse() {
		String word = "python";
		
		char[] reversed = word.toCharArray(); 
		
		int i = 0;
		int j = word.length() - 1;
				
		for (i = 0; i < j; i++, j--) {
			char temp = reversed[i];
			reversed[i] = reversed[j];
			reversed[j] = temp;
		}
		
		System.out.println("Reversed: " + new String(reversed));
	}
	
	public static void compare() {
		String a = "Painter";
		String b = "Painting";
		
		int i, j;
		
		for (i = 0, j = 0; i < a.length() && j < b.length(); i++, j++) {
			if (a.charAt(i) != b.charAt(j)) {
				break;
			}
		}
		
		if (a.charAt(i) == b.charAt(j)) {
			System.out.println("Strings " + a + ", " + b + " are equals");
		} else if (a.charAt(i) < b.charAt(j)) {
			System.out.println("String " + a + " smaller than " + b + "");
		} else {
			System.out.println("String " + a + " greater than " + b + "");
		}
	}
	
	public static void isPalindrome() {
		String word = "madam"; 
		
		int i = 0;
		int j = word.length() - 1;
				
		for (i = 0; i < j; i++, j--) {
			if (word.charAt(i) != word.charAt(j)) {
				System.out.println(word + " is not Palindrome");
				return;
			}
		}
		
		System.out.println(word + " is Palindrome");
	}
	
	public static void  findDuplicatesWithHash() {
		String word = "finding";
		int[] hash = new int[26]; //only lower cases
		
		for (int i = 0; i < word.length(); i++) {
			hash[word.charAt(i) - 97] ++;
		}
		
		for (int i = 0; i < 26; i++) {
			if (hash[i] > 1) {
				System.out.println("Word " + (char)(i + 97) + " repeated " + hash[i] + " times");
			}
		}
	}
	
	public static void  findDuplicatesWithBitwise() {
		String word = "finding";
		long hash = 0;
		
		for (int i = 0; i < word.length(); i++) {
			long aux = 1;
			aux = aux << word.charAt(i) - 97;
			
			long check = aux & hash;			
			if (check > 0) {
				System.out.println("Word " + word.charAt(i) + " repeated");
			} else {
				hash = aux | hash;
			}
		}
		
	}
	
	public static void anagram() {
		String word1 = "decimal";
		String word2 = "medical";
		
		if (word1.length() != word2.length()) {
			System.out.println("Words are not anagram");
			return;
		}
		
		int[] hash = new int[26];
		
		for (int i = 0; i < word1.length(); i++) {
			hash[word1.charAt(i) - 97]++;
		}
		
		int i;
		for (i = 0; i < word2.length(); i++) {
			hash[word2.charAt(i) - 97]--;
			if (hash[word2.charAt(i) - 97] < 0 ) {
				System.out.println("Words are not anagram");
				break;
			}
		}
		
		if (i == word2.length()) {
			System.out.println("Words are anagram");
		}
	}
	
	public static void permutation(String word, int k, int[] a, char[] res) {
		if (k == word.length()) {
			System.out.println(new String(res));
		} else {
			for (int i = 0; i < word.length(); i++) {
				if (a[i] == 0) {
					res[k] = word.charAt(i);
					a[i] = 1;
					permutation(word,  k + 1, a, res);
					a[i] = 0;
				}
			}
		}
	}
	
	public static void permutation(char[] word, int l, int h) {
		if (l == h) {
			System.out.println(new String(word));
		} else {
			for (int i = l; i <= h; i++) {
				swap(word, l, i);
				permutation(word, l + 1, h);
				swap(word, l, i);
			}
		}
	}
	
	public static void swap(char[] text, int from, int to) {
		char temp = text[to];
		text[to] = text[from];
		text[from] = temp;
	}
	
	public static void main(String[] args) {
		countSpace();
		System.out.println();
		validate();
		System.out.println();
		reverseWithAdditionalArray();
		System.out.println();
		reverse();
		System.out.println();
		compare();
		System.out.println();
		isPalindrome();
		System.out.println();
		findDuplicatesWithHash();
		System.out.println();
		findDuplicatesWithBitwise();
		System.out.println();
		anagram();
		System.out.println();
		permutation("abc", 0, new int[10], new char[10]);
		System.out.println();
		permutation("abc".toCharArray(), 0, 2);
	}

}
