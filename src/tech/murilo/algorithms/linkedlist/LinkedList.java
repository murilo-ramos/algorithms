package tech.murilo.algorithms.linkedlist;

public class LinkedList {
	
	private static class Node {
		int data;
		Node next;
		
		public Node(int data) {
			this.data = data;
		}
		
		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}
	
	private Node first;
	private Node last;
	
	public LinkedList(int[] values) {
		if (values == null || values.length == 0) {
			return;
		}
		
		first = new Node(values[0]);		
		Node node = first;
		
		for (int i = 1; i < values.length; i++) {
			node.next = new Node(values[i]);
			node = node.next;
			last = node;
		}
	}
	
	public LinkedList() {
	}
	
	public void clear() {
		first = null;
		last = null;
	}
	
	public void append(int value) {
		if (first == null) {
			insert(value, 0);
		} else {
			last.next = new Node(value);
			last = last.next;
		}
	}
	
	public void insertSorted(int value) {
		if (first == null) {
			insert(value, 0);
			return;
		}
		
		var newNode = new Node(value);
		
		//implementation using last pointer
		
		if (first.data > newNode.data) {
			newNode.next = first;
			first = newNode;
			return;
		}
		
		var node = first;
		
		while (node != null) {
			if (node.next != null && node.next.data > newNode.data) {
				newNode.next = node.next;
				node.next = newNode;
				return;
			}
			node = node.next;
		}
		
		last.next = newNode;
		last = newNode;
	}
	
	public void insert(int value, int position) {
		var listLength = count();
		
		if (position < 0 || (listLength > 0 && position >= listLength)) {
			return;
		}
		
		if (position == 0) {
			var node = new Node(value);
			node.next = first;
			first = node;
			
			if (first.next == null) {
				last = first;
			}
		} else {
			Node node = first.next;
			
			for (int i = 1; i < position - 1; i++)  {
				node = node.next;
			}
			
			if (node != null) {
				var newNode = new Node(value);
				newNode.next = node.next;
				node.next = newNode;
			}			
		}
	}
	
	public Node delete(int position) {
		var listLength = count();
		
		if (position < 0 || position >= listLength || first == null) {
			return null;
		}
		
		if (position == 0) {
			var node = first;
			
			first = first.next;
			if (first == null) {
				last = first;
			}
			
			return node;
		}
		
		Node node = first.next;
		Node prior = first;
		
		int i;
		for (i = 1; i < position; i++) {
			prior = node;
			node = node.next;
		}
		
		Node result = node;
		
		if (i == listLength - 1) {
			prior.next = null;
			last = prior;
		} else {
			prior.next = node.next;
		}
		
		return result;
	}
	
	public boolean isSorted() {
		int value = Integer.MIN_VALUE;		
		Node node = first;
		
		while (node != null) {
			if (node.data < value) {
				return false;
			}
			value = node.data;
			node = node.next;
		}
		
		return true;
	}
	
	public int count() {
		return count(false);
	}
	
	public int count(boolean recursive) {
		if (recursive) {
			return countRecursive(first);
		}
		
		int count = 0;		
		Node node = first;
		
		while (node != null) {
			count++;
			node = node.next;
		}
		
		return count;
	}
	
	public int sum() {
		return sum(false);
	}
	
	public int sum(boolean recursive) {
		if (recursive) {
			return sumRecursive(first);
		}
		
		int sum = 0;		
		Node node = first;
		
		while (node != null) {
			sum += node.data;
			node = node.next;
		}
		
		return sum;
	}
	
	public int max() {
		return max(false);
	}
	
	public int max(boolean recursive) {
		if (recursive) {
			return maxRecursive(first);
		}
		
		int max = Integer.MIN_VALUE;		
		Node node = first;
		
		while (node != null) {
			if (node.data > max) {
				max = node.data;
			}
			node = node.next;
		}
		
		return max;
	}
	
	public int min() {
		return min(false);
	}
	
	public int min(boolean recursive) {
		if (recursive) {
			return minRecursive(first);
		}
		
		int max = Integer.MAX_VALUE;		
		Node node = first;
		
		while (node != null) {
			if (node.data < max) {
				max = node.data;
			}
			node = node.next;
		}
		
		return max;
	}
	
	public Node search(int value) {
		return search(value, false);
	}
	
	public Node search(int value, boolean recursive) {
		if (recursive) {
			return searchRecursive(value, first);
		}
				
		Node node = first;
		Node prior = null;
		
		while (node != null) {
			if (node.data == value) {
				if (node != first) { //transpose
					prior.next = node.next;
					node.next = first;
					first = node;
				}
				if (node == last) {
					last = prior;
				}
				
				return node;
			}
			prior = node;
			node = node.next;
		}
		
		return null;
	}
	
	public void removeDuplicates() {
		if (first == null || first.next == null) {
			return;
		}
		
		Node node = first;
		Node nextNode = node.next;
		
		while (nextNode != null) {
			if (node.data != nextNode.data) {
				node = nextNode;
				nextNode = node.next;
			} else {
				node.next = nextNode.next;
				nextNode = node.next;
			}
		}
		
		last = node;
	}
	
	public void reverse() {
		reverse(false);
	}
	
	public void reverse(boolean recursive) {
		if (first == null || first.next == null) {
			return;
		}
		
		if (recursive) {
			reverseRecursive(null, first);
			return;
		}
		
		int[] auxArray = new int[count()];
		
		Node node = first;
		int i = 0;
		
		while (node != null) {
			auxArray[i++] = node.data;
			node = node.next;
		}
		
		first = new Node(auxArray[auxArray.length - 1]);
		
		node = first;
		
		for (i = auxArray.length - 2; i >= 0; i--) {
			node.next = new Node(auxArray[i]);
			node = node.next;
		}
		
		last = node;
	}
	
	public void reverseLinks() {
		if (first == null || first.next == null) {
			return;
		}
		
		last = first;
		
		Node prior = null;
		Node node = first;
		Node next = null;
		
		while (node != null) {
			next = prior;
			prior = node;
			node = node.next;
			prior.next = next;
		}
		
		first = prior;
	}
	
	public void concat(LinkedList linkedList) {
		if (first == null) {
			return;
		}
		
		// doing it in N is easy as display, but as 'last' is implemented here I will use it (constant time)
		last.next = linkedList.first;
	}
	
	public void merge(LinkedList linkedList) {
		Node nodeFirst = first;
		Node nodeSecond = linkedList.first;
		Node third;
		
		Node backupFirstLast = last;
		Node backupSecondLast = linkedList.last;
		
		if (nodeFirst.data < nodeSecond.data) {			
			third = nodeFirst;
			nodeFirst = nodeFirst.next;
			
		} else {
			third = nodeSecond;
			nodeSecond = nodeSecond.next;
		}
				
		third.next = null;
		first = third;
		
		while (nodeFirst != null && nodeSecond != null) {
			if (nodeFirst.data < nodeSecond.data) {			
				third.next = nodeFirst;
				nodeFirst = nodeFirst.next;
				
			} else {
				third.next = nodeSecond;
				nodeSecond = nodeSecond.next;
			}
			
			third = third.next;
		}
		
		last = third;
		
		if (nodeFirst != null) {
			third.next = nodeFirst;
			last = backupFirstLast;
		}
		
		if (nodeSecond != null) {
			third.next = nodeSecond;
			last = backupSecondLast;
		}
	}
	
	public boolean isLoop() {
		Node node, aux;
		node = aux = first;
		
		do {
			node = node.next;
			aux = aux.next;
			
			if (aux != null) {
				aux = aux.next;
			}
		} while(node != null && aux != null && node != aux);
		
		return node == aux ? true : false;
	}
	
	public void display() {
		display(false);
	}
	
	public void display(boolean recursive) {
		if (recursive) {
			displayRecursive(first);
			System.out.println();
			return;
		}
		
		Node node = first;
		
		while (node != null) {
			System.out.print(String.format("%d ", node.data));
			node = node.next;
		}
		
		System.out.println();
	}
	
	private void displayRecursive(Node node) {
		if (node != null) {
			System.out.print(String.format("%d ", node.data));
			displayRecursive(node.next);
		}
	}
	
	private int countRecursive(Node node) {
		if (node != null) {
			return countRecursive(node.next) + 1;
		} 
		
		return 0;
	}
	
	private int sumRecursive(Node node) {
		if (node != null) {
			return sumRecursive(node.next) + node.data;
		} 
		
		return 0;
	}
	
	private int maxRecursive(Node node) {
		if (node != null) {
			var value = maxRecursive(node.next);
			return (node.data > value) ? node.data : value;
		} 
		
		return Integer.MIN_VALUE;
	}
	
	private int minRecursive(Node node) {
		if (node != null) {
			var value = minRecursive(node.next);
			return (node.data < value) ? node.data : value;
		} 
		
		return Integer.MAX_VALUE;
	}
	
	private Node searchRecursive(int value, Node node) {
		if (node == null) {
			return null;
		}
		
		if (node.data == value) {
			return node;
		} else {
			return searchRecursive(value, node.next);
		}
	}
	
	private void reverseRecursive(Node prior, Node node) {
		if (node != null) {
			reverseRecursive(node, node.next);
			node.next = prior;
			last = node;
		} else {
			first = prior; 
		}
	}
	
	public static void main(String[] args) {
		var values = new int[] {1, 23, 0, 4, 5, 45};
		
		var linkedList = new LinkedList(values);
		
		linkedList.display();
		
		linkedList.clear();
		
		linkedList.insert(45, 0);
		linkedList.insert(5, 0);
		linkedList.insert(4, 0);
		linkedList.insert(0, 0);
		linkedList.insert(23, 0);
		linkedList.insert(1, 0);
		linkedList.display(true);
		
		System.out.println("Count: " + linkedList.count());
		System.out.println("Count: " + linkedList.count(true));
		
		System.out.println("Sum: " + linkedList.sum());
		System.out.println("Sum: " + linkedList.sum(true));
		
		System.out.println("Max: " + linkedList.max());
		System.out.println("Max: " + linkedList.max(true));
		
		System.out.println("Min: " + linkedList.min());
		System.out.println("Min: " + linkedList.min(true));
		
		System.out.println("Search: " + linkedList.search(4));
		System.out.println("Search: " + linkedList.search(4, true));
		System.out.println("Search: " + linkedList.search(6));
		System.out.println("Search: " + linkedList.search(45));
		System.out.println("Search: " + linkedList.search(45));
		linkedList.display();
		
		linkedList.insert(0, -1);
		linkedList.display();
		
		linkedList.insert(0, 7);
		linkedList.display();
		
		linkedList.insert(8, 0);
		linkedList.display();
		
		linkedList.insert(9, 2);
		linkedList.display();
		
		linkedList.insert(9, 7);
		linkedList.display();
		
		linkedList.append(10);
		linkedList.display();
		linkedList.append(11);
		linkedList.display();
		linkedList.append(13);
		linkedList.display();
		
		linkedList.clear();
		linkedList.insertSorted(10);
		linkedList.insertSorted(11);
		linkedList.insertSorted(0);
		linkedList.insertSorted(43);
		linkedList.insertSorted(25);
		linkedList.insertSorted(8);
		linkedList.insertSorted(50);
		
		linkedList.display();
		
		System.out.println("Removed: " + linkedList.delete(0));
		linkedList.display();
		System.out.println("Removed: " + linkedList.delete(3));
		linkedList.display();
		System.out.println("Removed: " + linkedList.delete(4));
		linkedList.display();
		System.out.println("Removed: " + linkedList.delete(5));
		linkedList.display();
		
		linkedList.append(100);
		linkedList.display();		
		System.out.println(linkedList.isSorted());
		
		linkedList.insert(5, 3);
		linkedList.display();
		System.out.println(linkedList.isSorted());
		
		linkedList = new LinkedList(new int[] {1,1,2,3,3,4,5,5,5,9,10,10,55,55});
		linkedList.display();
		linkedList.removeDuplicates();
		linkedList.display();
		
		linkedList.reverse();
		linkedList.display();
		
		linkedList.reverseLinks();
		linkedList.display();
		
		linkedList.reverse(true);
		linkedList.display();
		
		linkedList = new LinkedList(new int[] {1,2,3,4});
		var linkedList02 = new LinkedList(new int[] {5,6,7,8});
		
		linkedList.display();
		linkedList02.display();
		linkedList.concat(linkedList02);
		linkedList.display();
		
		linkedList = new LinkedList(new int[] {1,2,5,8});
		linkedList02 = new LinkedList(new int[] {1,6,7,8, 18, 22});
		
		linkedList.display();
		linkedList02.display();
		linkedList.merge(linkedList02);
		linkedList.display();
		
		System.out.println("Is Loop: " + linkedList.isLoop());
		
		linkedList.last.next = linkedList.first.next.next;
		
		System.out.println("Is Loop: " + linkedList.isLoop());
		
		
	}
}
