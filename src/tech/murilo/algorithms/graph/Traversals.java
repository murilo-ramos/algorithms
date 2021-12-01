package tech.murilo.algorithms.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Traversals {
	
	private static void bfsTraversal(int[][] values, int start) {
		boolean[] visited = new boolean[values.length];		
		Queue<Integer> queue = new LinkedList<>();
		
		int i = start;
		System.out.print(String.format("%s ", i));
		visited[i] = true;
		queue.add(i);
		
		while (!queue.isEmpty()) {
			i = queue.poll();
			
			for (int j = 0; j < values.length; j++) {
				if (values[i][j] == 1 && !visited[j]) {
					System.out.print(String.format("%s ", j));
					visited[j] = true;
					queue.add(j);
				}
			}
		}
		
		System.out.println();
	}
	
	private static void dfsTraversal(int[][] values, int start) {
		dfsTraversal(values, start, new boolean[values.length]);
		System.out.println();
	}
	
	private static void dfsTraversal(int[][] values, int start, boolean[] visited) {
		if (!visited[start]) {			
			System.out.print(String.format("%s ", start));
			visited[start] = true;
			
			for (int j = 0; j < values.length; j++) {
				if (values[start][j] == 1 && !visited[j]) {
					dfsTraversal(values, j, visited);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] values = {
			{0,1,1,0,0,0},
			{1,0,0,1,0,0},
			{1,0,0,1,0,0},
			{0,1,1,0,1,1},
			{0,0,0,1,0,0},
			{0,0,0,1,0,0}
		};
		
		bfsTraversal(values, 0);
		dfsTraversal(values, 0);
	}

}
