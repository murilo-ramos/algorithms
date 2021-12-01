package tech.murilo.algorithms.heap;

import java.util.Arrays;

public class Heap {
	
	private int[] heap;
	private int pos = 0;
	
	public Heap(int[] heap, int length) {
		this.heap = heap;
		this.pos = length;
	}
	
	public Heap() {
		this.heap = new int[50];
	}
	
	public static Heap fromHeapify(int array[]) {
	    for (int i = (array.length / 2) - 1; i >= 0; i--){
	 
	        int j = 2 * i + 1;
	 
	        while (j < array.length - 1) {
	            if (array[j] < array[j+1]){
	                j = j+1;
	            }
	             
	            if (array[i] < array[j]){
	                swap(array, i, j);
	                i = j;
	                j = 2 * i + 1;
	            } else {
	                break;
	            }
	        }
	    }
	    
	    return new Heap(array, array.length);
	}
	
	public void clear() {
		this.heap = new int[50];
		this.pos = 0;
	}
	
	public void insert(int value) {
		if (pos == heap.length) {
			throw new StackOverflowError("Heap is full.");
		}
		
		int index = pos++;
		heap[index] = value;
		
		int i = index;
		int temp = heap[index];
		
		while (i > 0 && temp > heap[i % 2 == 0 ? (i / 2) - 1 : i / 2]) {
			heap[i] = heap[i % 2 == 0 ? (i / 2) - 1 : i / 2];
			i = i % 2 == 0 ? (i / 2) - 1 : i / 2;
		}
		
		heap[i] = temp;
	}
	
	public int delete() {
		if (pos == 0) {
			throw new StackOverflowError("Heap is empty.");
		}
		
		int value = heap[0];

		heap[0] = heap[pos - 1];
		heap[pos - 1] = value;
		
		int i = 0;
		int j = (i * 2) + 1;
		 
		while(j  <= pos - 2) {
		    if (j < pos - 2 && heap[j + 1] > heap[j]) {
		    	j = j  + 1;
		    }
		 
		    if (heap[i] < heap[j]) {
				swap(heap, i, j);
				i = j;
				j = 2 * j;
		    } else {
		    	break;
		    }
		}
		pos--;
		
		 
		return value;
	}
	
	public int size() {
		return pos;
	}
	
	public void display() {
		for (int i = 0; i < pos; i++) {
			System.out.print(heap[i] + " ");
		}
		
		System.out.println();
	}
	
	public int[] getArrayHeap() {
		return Arrays.copyOf(heap, heap.length);
	}
	
	private static void swap(int array[], int i, int j){
	    int temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
	}
	
	public static void main(String[] args) {
		var heap = new Heap(new int[] {45, 35, 15, 30, 10, 12, 6, 5, 20, 0}, 9); //weird, but aligned to lecture
		heap.display();
		
		heap.insert(50);
		heap.display();
		
		heap.clear();
		heap.insert(10);
		heap.insert(20);
		heap.insert(30);
		heap.insert(25);
		heap.insert(5);
		heap.insert(40);
		heap.insert(35);
		
		heap.display();
		
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		
		System.out.println();
		
		var arrayHeap = heap.getArrayHeap();
		
		for (int value : arrayHeap) {
			System.out.print(value + " ");
		}
		
		System.out.println();
		System.out.println();
		
		heap = Heap.fromHeapify(new int[] {5, 10, 30, 20});
		heap.display();
	}

}
